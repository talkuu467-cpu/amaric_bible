package com.example.data.repository

import com.example.data.local.dao.BibleUserDataDao
import com.example.data.local.entity.BookmarkEntity
import com.example.data.local.entity.HighlightEntity
import com.example.data.local.entity.ReadingHistoryEntity
import com.example.data.local.entity.ReadingPlanProgressEntity
import com.example.data.local.entity.VerseNoteEntity
import kotlinx.coroutines.flow.Flow

class UserDataRepository(private val dao: BibleUserDataDao) {

    // Bookmarks
    val bookmarks: Flow<List<BookmarkEntity>> = dao.getAllBookmarks()

    suspend fun isBookmarked(bookId: Int, chapter: Int, verse: Int): Boolean {
        return dao.getBookmark(bookId, chapter, verse) != null
    }

    suspend fun toggleBookmark(bookId: Int, bookName: String, chapter: Int, verse: Int, text: String): Boolean {
        val existing = dao.getBookmark(bookId, chapter, verse)
        return if (existing != null) {
            dao.deleteBookmark(bookId, chapter, verse)
            false
        } else {
            dao.insertBookmark(
                BookmarkEntity(
                    bookId = bookId,
                    bookName = bookName,
                    chapter = chapter,
                    verse = verse,
                    text = text
                )
            )
            true
        }
    }

    suspend fun deleteBookmarkById(id: Long) = dao.deleteBookmarkById(id)

    // Highlights
    val highlights: Flow<List<HighlightEntity>> = dao.getAllHighlights()

    fun getHighlightsForChapter(bookId: Int, chapter: Int): Flow<List<HighlightEntity>> =
        dao.getHighlightsForChapter(bookId, chapter)

    suspend fun saveHighlight(
        bookId: Int,
        bookName: String,
        chapter: Int,
        verse: Int,
        text: String,
        colorHex: String
    ) {
        dao.deleteHighlight(bookId, chapter, verse)
        dao.insertHighlight(
            HighlightEntity(
                bookId = bookId,
                bookName = bookName,
                chapter = chapter,
                verse = verse,
                text = text,
                colorHex = colorHex
            )
        )
    }

    suspend fun removeHighlight(bookId: Int, chapter: Int, verse: Int) =
        dao.deleteHighlight(bookId, chapter, verse)

    suspend fun deleteHighlightById(id: Long) = dao.deleteHighlightById(id)

    // Notes
    val notes: Flow<List<VerseNoteEntity>> = dao.getAllNotes()

    fun getNotesForChapter(bookId: Int, chapter: Int): Flow<List<VerseNoteEntity>> =
        dao.getNotesForChapter(bookId, chapter)

    suspend fun getNoteForVerse(bookId: Int, chapter: Int, verse: Int): VerseNoteEntity? =
        dao.getNoteForVerse(bookId, chapter, verse)

    suspend fun saveNote(
        bookId: Int,
        bookName: String,
        chapter: Int,
        verse: Int,
        verseText: String,
        noteContent: String
    ) {
        val existing = dao.getNoteForVerse(bookId, chapter, verse)
        if (existing != null) {
            dao.updateNote(
                existing.copy(
                    noteContent = noteContent,
                    updatedAt = System.currentTimeMillis()
                )
            )
        } else {
            dao.insertNote(
                VerseNoteEntity(
                    bookId = bookId,
                    bookName = bookName,
                    chapter = chapter,
                    verse = verse,
                    verseText = verseText,
                    noteContent = noteContent
                )
            )
        }
    }

    suspend fun deleteNoteById(id: Long) = dao.deleteNoteById(id)

    // Reading Plan Progress
    fun getPlanProgress(planId: String): Flow<List<ReadingPlanProgressEntity>> =
        dao.getPlanProgress(planId)

    val allPlanProgress: Flow<List<ReadingPlanProgressEntity>> = dao.getAllPlanProgress()

    suspend fun toggleDayProgress(planId: String, dayNumber: Int, isCompleted: Boolean) {
        dao.setDayProgress(
            ReadingPlanProgressEntity(
                planId = planId,
                dayNumber = dayNumber,
                isCompleted = isCompleted,
                completedAt = if (isCompleted) System.currentTimeMillis() else null
            )
        )
    }

    suspend fun resetPlanProgress(planId: String) = dao.resetPlanProgress(planId)

    // History
    val readingHistory: Flow<List<ReadingHistoryEntity>> = dao.getReadingHistory()

    suspend fun recordReadingHistory(bookId: Int, bookName: String, chapter: Int) {
        dao.insertReadingHistory(
            ReadingHistoryEntity(
                bookId = bookId,
                bookName = bookName,
                chapter = chapter
            )
        )
    }
}
