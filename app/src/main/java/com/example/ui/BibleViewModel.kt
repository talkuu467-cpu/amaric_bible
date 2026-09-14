package com.example.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.BibleDataProvider
import com.example.data.local.BibleDatabase
import com.example.data.local.entity.BookmarkEntity
import com.example.data.local.entity.HighlightEntity
import com.example.data.local.entity.ReadingPlanProgressEntity
import com.example.data.local.entity.VerseNoteEntity
import com.example.data.model.BibleBook
import com.example.data.model.BibleTranslation
import com.example.data.model.BibleVerse
import com.example.data.model.DailyVerse
import com.example.data.model.ReadingPlan
import com.example.data.model.Testament
import com.example.data.model.TopicalVerse
import com.example.data.repository.BibleRepository
import com.example.data.repository.UserDataRepository
import com.example.util.ReaderFont
import com.example.util.ReaderFontSize
import com.example.util.ReaderLineSpacing
import com.example.util.ReaderSettings
import com.example.util.ReaderTheme
import com.example.util.TextToSpeechHelper
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

data class ReaderUiState(
    val currentBook: BibleBook = BibleDataProvider.books[0], // Genesis
    val currentChapter: Int = 1,
    val verses: List<BibleVerse> = emptyList(),
    val highlights: Map<Int, HighlightEntity> = emptyMap(),
    val notes: Map<Int, VerseNoteEntity> = emptyMap(),
    val bookmarkedVerses: Set<Int> = emptySet(),
    val selectedVerse: BibleVerse? = null,
    val isSpeakingChapter: Boolean = false,
    val targetScrollVerse: Int? = null
)

data class SearchUiState(
    val query: String = "",
    val testamentFilter: Testament? = null,
    val results: List<BibleVerse> = emptyList(),
    val isSearching: Boolean = false
)

private data class ChapterLocationState(
    val book: BibleBook,
    val chapter: Int,
    val selectedVerse: BibleVerse?,
    val targetScrollVerse: Int?
)

private data class UserChapterAnnotations(
    val highlights: List<HighlightEntity>,
    val notes: List<VerseNoteEntity>,
    val bookmarks: List<BookmarkEntity>
)

class BibleViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = BibleRepository()
    private val database = BibleDatabase.getDatabase(application)
    private val userDataRepo = UserDataRepository(database.bibleUserDataDao())
    val ttsHelper = TextToSpeechHelper(application)

    // Reader State
    private val _currentBook = MutableStateFlow(BibleDataProvider.books[0]) // Defaults to Genesis
    val currentBook: StateFlow<BibleBook> = _currentBook.asStateFlow()

    private val _currentChapter = MutableStateFlow(1) // Defaults to Chapter 1
    val currentChapter: StateFlow<Int> = _currentChapter.asStateFlow()

    private val _selectedVerse = MutableStateFlow<BibleVerse?>(null)
    val selectedVerse: StateFlow<BibleVerse?> = _selectedVerse.asStateFlow()

    private val _targetScrollVerse = MutableStateFlow<Int?>(null)
    val targetScrollVerse: StateFlow<Int?> = _targetScrollVerse.asStateFlow()

    // Settings
    private val _settings = MutableStateFlow(ReaderSettings())
    val settings: StateFlow<ReaderSettings> = _settings.asStateFlow()

    private val locationFlow = combine(
        _currentBook,
        _currentChapter,
        _selectedVerse,
        _targetScrollVerse
    ) { book, chapter, selVerse, targetVerse ->
        ChapterLocationState(book, chapter, selVerse, targetVerse)
    }

    private val userAnnotationsFlow = combine(
        userDataRepo.highlights,
        userDataRepo.notes,
        userDataRepo.bookmarks
    ) { allHighlights, allNotes, allBookmarks ->
        UserChapterAnnotations(allHighlights, allNotes, allBookmarks)
    }

    // Reader UI State combining location, annotations & settings
    val readerUiState: StateFlow<ReaderUiState> = combine(
        locationFlow,
        userAnnotationsFlow,
        _settings
    ) { location, annotations, currentSettings ->
        val verses = repository.getVersesForChapter(location.book.id, location.chapter, currentSettings.translation)
        val chapterHighlights = annotations.highlights
            .filter { it.bookId == location.book.id && it.chapter == location.chapter }
            .associateBy { it.verse }
        val chapterNotes = annotations.notes
            .filter { it.bookId == location.book.id && it.chapter == location.chapter }
            .associateBy { it.verse }
        val chapterBookmarks = annotations.bookmarks
            .filter { it.bookId == location.book.id && it.chapter == location.chapter }
            .map { it.verse }
            .toSet()

        ReaderUiState(
            currentBook = location.book,
            currentChapter = location.chapter,
            verses = verses,
            highlights = chapterHighlights,
            notes = chapterNotes,
            bookmarkedVerses = chapterBookmarks,
            selectedVerse = location.selectedVerse,
            targetScrollVerse = location.targetScrollVerse
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = ReaderUiState()
    )

    // Daily Verse
    private val _dailyVerse = MutableStateFlow(repository.getDailyVerseForToday())
    val dailyVerse: StateFlow<DailyVerse> = _dailyVerse.asStateFlow()

    // Reading Plans
    val readingPlans: List<ReadingPlan> = repository.getAllReadingPlans()
    private val _selectedPlan = MutableStateFlow<ReadingPlan?>(readingPlans[0])
    val selectedPlan: StateFlow<ReadingPlan?> = _selectedPlan.asStateFlow()

    val planProgress: StateFlow<List<ReadingPlanProgressEntity>> = userDataRepo.allPlanProgress
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    // Topical Verses
    val topicalCollections: List<TopicalVerse> = repository.getTopicalCollections()
    private val _selectedTopic = MutableStateFlow<TopicalVerse?>(topicalCollections[0])
    val selectedTopic: StateFlow<TopicalVerse?> = _selectedTopic.asStateFlow()

    // Search State
    private val _searchState = MutableStateFlow(SearchUiState())
    val searchState: StateFlow<SearchUiState> = _searchState.asStateFlow()

    // User Data collections
    val allBookmarks: StateFlow<List<BookmarkEntity>> = userDataRepo.bookmarks
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    val allHighlights: StateFlow<List<HighlightEntity>> = userDataRepo.highlights
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    val allNotes: StateFlow<List<VerseNoteEntity>> = userDataRepo.notes
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    // Note Dialog State
    private val _editingNoteVerse = MutableStateFlow<BibleVerse?>(null)
    val editingNoteVerse: StateFlow<BibleVerse?> = _editingNoteVerse.asStateFlow()

    private val _shareVerse = MutableStateFlow<BibleVerse?>(null)
    val shareVerse: StateFlow<BibleVerse?> = _shareVerse.asStateFlow()

    init {
        repository.initAssets(application)
        recordCurrentChapterRead()
    }

    fun selectBookAndChapter(bookId: Int, chapter: Int, targetVerse: Int? = null) {
        val book = repository.getBookById(bookId) ?: return
        val validChapter = chapter.coerceIn(1, book.chaptersCount)
        _currentBook.value = book
        _currentChapter.value = validChapter
        _targetScrollVerse.value = targetVerse
        _selectedVerse.value = null
        recordCurrentChapterRead()
    }

    fun nextChapter() {
        val current = _currentChapter.value
        val book = _currentBook.value
        if (current < book.chaptersCount) {
            selectBookAndChapter(book.id, current + 1)
        } else {
            val nextBook = BibleDataProvider.books.find { it.id == book.id + 1 }
            if (nextBook != null) {
                selectBookAndChapter(nextBook.id, 1)
            }
        }
    }

    fun previousChapter() {
        val current = _currentChapter.value
        val book = _currentBook.value
        if (current > 1) {
            selectBookAndChapter(book.id, current - 1)
        } else {
            val prevBook = BibleDataProvider.books.find { it.id == book.id - 1 }
            if (prevBook != null) {
                selectBookAndChapter(prevBook.id, prevBook.chaptersCount)
            }
        }
    }

    fun setSelectedVerse(verse: BibleVerse?) {
        _selectedVerse.value = verse
    }

    fun clearTargetScrollVerse() {
        _targetScrollVerse.value = null
    }

    fun toggleBookmark(verse: BibleVerse) {
        viewModelScope.launch {
            userDataRepo.toggleBookmark(
                bookId = verse.bookId,
                bookName = verse.bookName,
                chapter = verse.chapter,
                verse = verse.verse,
                text = verse.text
            )
        }
    }

    fun deleteBookmark(id: Long) {
        viewModelScope.launch {
            userDataRepo.deleteBookmarkById(id)
        }
    }

    fun setHighlight(verse: BibleVerse, colorHex: String) {
        viewModelScope.launch {
            userDataRepo.saveHighlight(
                bookId = verse.bookId,
                bookName = verse.bookName,
                chapter = verse.chapter,
                verse = verse.verse,
                text = verse.text,
                colorHex = colorHex
            )
        }
    }

    fun removeHighlight(verse: BibleVerse) {
        viewModelScope.launch {
            userDataRepo.removeHighlight(verse.bookId, verse.chapter, verse.verse)
        }
    }

    fun deleteHighlight(id: Long) {
        viewModelScope.launch {
            userDataRepo.deleteHighlightById(id)
        }
    }

    fun openNoteEditor(verse: BibleVerse) {
        _editingNoteVerse.value = verse
    }

    fun closeNoteEditor() {
        _editingNoteVerse.value = null
    }

    fun saveNote(verse: BibleVerse, content: String) {
        viewModelScope.launch {
            if (content.isNotBlank()) {
                userDataRepo.saveNote(
                    bookId = verse.bookId,
                    bookName = verse.bookName,
                    chapter = verse.chapter,
                    verse = verse.verse,
                    verseText = verse.text,
                    noteContent = content
                )
            }
            _editingNoteVerse.value = null
        }
    }

    fun deleteNote(id: Long) {
        viewModelScope.launch {
            userDataRepo.deleteNoteById(id)
        }
    }

    fun openShareDialog(verse: BibleVerse) {
        _shareVerse.value = verse
    }

    fun closeShareDialog() {
        _shareVerse.value = null
    }

    fun speakVerse(verse: BibleVerse) {
        ttsHelper.speak("${verse.bookName} chapter ${verse.chapter} verse ${verse.verse}. ${verse.text}", utteranceId = verse.verse.toString())
    }

    fun speakCurrentChapter() {
        val state = readerUiState.value
        val fullText = buildString {
            append("${state.currentBook.name}, Chapter ${state.currentChapter}. ")
            state.verses.forEach { v ->
                append("Verse ${v.verse}. ${v.text} ")
            }
        }
        ttsHelper.speak(fullText, utteranceId = "chapter_${state.currentChapter}")
    }

    fun stopSpeaking() {
        ttsHelper.stop()
    }

    // Plan functions
    fun selectReadingPlan(plan: ReadingPlan) {
        _selectedPlan.value = plan
    }

    fun togglePlanDay(planId: String, dayNumber: Int, isCompleted: Boolean) {
        viewModelScope.launch {
            userDataRepo.toggleDayProgress(planId, dayNumber, isCompleted)
        }
    }

    fun resetPlan(planId: String) {
        viewModelScope.launch {
            userDataRepo.resetPlanProgress(planId)
        }
    }

    fun selectTopic(topic: TopicalVerse) {
        _selectedTopic.value = topic
    }

    // Search functions
    fun updateSearchQuery(query: String) {
        _searchState.value = _searchState.value.copy(query = query)
        performSearch(query, _searchState.value.testamentFilter)
    }

    fun setSearchTestamentFilter(filter: Testament?) {
        _searchState.value = _searchState.value.copy(testamentFilter = filter)
        performSearch(_searchState.value.query, filter)
    }

    private fun performSearch(query: String, filter: Testament?) {
        val trimmed = query.trim()
        if (trimmed.isEmpty()) {
            _searchState.value = _searchState.value.copy(results = emptyList(), isSearching = false)
            return
        }
        val results = repository.search(trimmed, filter, _settings.value.translation)
        _searchState.value = _searchState.value.copy(results = results, isSearching = false)
    }

    // Settings adjustments
    fun updateTranslation(translation: BibleTranslation) {
        _settings.value = _settings.value.copy(translation = translation)
        performSearch(_searchState.value.query, _searchState.value.testamentFilter)
    }

    fun updateTheme(theme: ReaderTheme) {
        _settings.value = _settings.value.copy(theme = theme)
    }

    fun updateFont(font: ReaderFont) {
        _settings.value = _settings.value.copy(font = font)
    }

    fun updateFontSize(fontSize: ReaderFontSize) {
        _settings.value = _settings.value.copy(fontSize = fontSize)
    }

    fun updateLineSpacing(spacing: ReaderLineSpacing) {
        _settings.value = _settings.value.copy(lineSpacing = spacing)
    }

    fun toggleVerseNumbers(show: Boolean) {
        _settings.value = _settings.value.copy(showVerseNumbers = show)
    }

    private fun recordCurrentChapterRead() {
        viewModelScope.launch {
            val book = _currentBook.value
            val chapter = _currentChapter.value
            userDataRepo.recordReadingHistory(book.id, book.name, chapter)
        }
    }

    override fun onCleared() {
        super.onCleared()
        ttsHelper.shutdown()
    }
}
