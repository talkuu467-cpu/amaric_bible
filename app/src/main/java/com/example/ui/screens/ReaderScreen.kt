package com.example.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.EditNote
import androidx.compose.material.icons.filled.FormatSize
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Stop
import androidx.compose.material.icons.filled.VolumeUp
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledTonalIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.data.model.BibleVerse
import com.example.ui.BibleViewModel
import com.example.ui.components.BookChapterPickerSheet
import com.example.ui.components.NoteEditorDialog
import com.example.ui.components.TypographySettingsSheet
import com.example.ui.components.VerseActionSheet
import com.example.ui.components.VerseShareDialog
import com.example.ui.theme.NightBackground
import com.example.ui.theme.NightBorder
import com.example.ui.theme.NightSurface
import com.example.ui.theme.NightTextPrimary
import com.example.ui.theme.NightTextSecondary
import com.example.ui.theme.ParchmentBackground
import com.example.ui.theme.ParchmentBorder
import com.example.ui.theme.ParchmentSurface
import com.example.ui.theme.ParchmentTextPrimary
import com.example.ui.theme.ParchmentTextSecondary
import com.example.ui.theme.SepiaBackground
import com.example.ui.theme.SepiaTextPrimary
import com.example.ui.theme.SepiaTextSecondary
import com.example.util.ReaderTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReaderScreen(
    viewModel: BibleViewModel,
    modifier: Modifier = Modifier
) {
    val readerState by viewModel.readerUiState.collectAsStateWithLifecycle()
    val settings by viewModel.settings.collectAsStateWithLifecycle()
    val editingNoteVerse by viewModel.editingNoteVerse.collectAsStateWithLifecycle()
    val shareVerse by viewModel.shareVerse.collectAsStateWithLifecycle()

    var showBookPicker by remember { mutableStateOf(false) }
    var showTypographySettings by remember { mutableStateOf(false) }
    var showAudioControls by remember { mutableStateOf(false) }

    val listState = rememberLazyListState()

    // Scroll to targeted verse if specified
    LaunchedEffect(readerState.targetScrollVerse, readerState.currentChapter) {
        val target = readerState.targetScrollVerse
        if (target != null && readerState.verses.isNotEmpty()) {
            val targetIndex = (target - 1).coerceIn(0, readerState.verses.size - 1)
            listState.animateScrollToItem(targetIndex)
            viewModel.clearTargetScrollVerse()
        } else {
            listState.scrollToItem(0)
        }
    }

    // Dynamic reader theme colors
    val (readerBg, readerText, readerMuted, readerCardBg, readerBorder) = when (settings.theme) {
        ReaderTheme.PARCHMENT -> ThemeColors(
            ParchmentBackground,
            ParchmentTextPrimary,
            ParchmentTextSecondary,
            ParchmentSurface,
            ParchmentBorder
        )
        ReaderTheme.IVORY -> ThemeColors(
            Color(0xFFFFFFFF),
            Color(0xFF1A1A1A),
            Color(0xFF64748B),
            Color(0xFFF8FAFC),
            Color(0xFFE2E8F0)
        )
        ReaderTheme.SEPIA -> ThemeColors(
            SepiaBackground,
            SepiaTextPrimary,
            SepiaTextSecondary,
            Color(0xFFEADBCE),
            Color(0xFFD6C5B0)
        )
        ReaderTheme.NIGHT -> ThemeColors(
            NightBackground,
            NightTextPrimary,
            NightTextSecondary,
            NightSurface,
            NightBorder
        )
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Surface(
                        onClick = { showBookPicker = true },
                        shape = RoundedCornerShape(20.dp),
                        color = readerCardBg,
                        border = androidx.compose.foundation.BorderStroke(1.dp, readerBorder),
                        modifier = Modifier.testTag("book_picker_header_button")
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.padding(horizontal = 14.dp, vertical = 6.dp)
                        ) {
                            Text(
                                text = "${readerState.currentBook.localizedName(settings.translation)} ${readerState.currentChapter}",
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.Bold,
                                color = readerText
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                            Icon(
                                imageVector = Icons.Default.ArrowDropDown,
                                contentDescription = "Select Book or Chapter",
                                tint = readerText
                            )
                        }
                    }
                },
                actions = {
                    // Translation badge
                    Surface(
                        onClick = {
                            val nextTranslation = if (settings.translation == com.example.data.model.BibleTranslation.AMHARIC) {
                                com.example.data.model.BibleTranslation.KJV
                            } else {
                                com.example.data.model.BibleTranslation.AMHARIC
                            }
                            viewModel.updateTranslation(nextTranslation)
                        },
                        shape = RoundedCornerShape(8.dp),
                        color = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.7f),
                        modifier = Modifier
                            .padding(end = 4.dp)
                            .testTag("translation_toggle_badge")
                    ) {
                        Text(
                            text = settings.translation.shortCode,
                            style = MaterialTheme.typography.labelSmall,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                        )
                    }

                    // Audio Button
                    IconButton(
                        onClick = {
                            showAudioControls = !showAudioControls
                            if (showAudioControls && !viewModel.ttsHelper.isSpeaking) {
                                viewModel.speakCurrentChapter()
                            }
                        },
                        modifier = Modifier.testTag("audio_tts_button")
                    ) {
                        Icon(
                            imageVector = Icons.Default.VolumeUp,
                            contentDescription = "Read Aloud",
                            tint = if (viewModel.ttsHelper.isSpeaking) MaterialTheme.colorScheme.primary else readerText
                        )
                    }

                    // Display / Font settings
                    IconButton(
                        onClick = { showTypographySettings = true },
                        modifier = Modifier.testTag("display_settings_button")
                    ) {
                        Icon(
                            imageVector = Icons.Default.FormatSize,
                            contentDescription = "Font & Display Settings",
                            tint = readerText
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = readerBg,
                    titleContentColor = readerText
                )
            )
        },
        containerColor = readerBg,
        modifier = modifier.testTag("bible_reader_screen")
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            // Audio Controls Banner (collapsible)
            AnimatedVisibility(
                visible = showAudioControls,
                enter = fadeIn(),
                exit = fadeOut()
            ) {
                AudioPlayerBar(
                    viewModel = viewModel,
                    readerText = readerText,
                    cardBg = readerCardBg,
                    onClose = {
                        viewModel.stopSpeaking()
                        showAudioControls = false
                    }
                )
            }

            // Chapter Scripture Content
            LazyColumn(
                state = listState,
                contentPadding = PaddingValues(horizontal = 20.dp, vertical = 16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
            ) {
                // Chapter Title Header
                item {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 12.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = readerState.currentBook.localizedName(settings.translation).uppercase(),
                            style = MaterialTheme.typography.labelMedium,
                            fontWeight = FontWeight.Bold,
                            color = readerMuted,
                            letterSpacing = 2.sp
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = if (settings.translation == com.example.data.model.BibleTranslation.AMHARIC) "ምዕራፍ ${readerState.currentChapter}" else "Chapter ${readerState.currentChapter}",
                            style = MaterialTheme.typography.headlineMedium.copy(
                                fontFamily = settings.font.fontFamily
                            ),
                            fontWeight = FontWeight.Bold,
                            color = readerText
                        )
                        Box(
                            modifier = Modifier
                                .padding(top = 10.dp, bottom = 6.dp)
                                .width(48.dp)
                                .height(2.dp)
                                .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.5f))
                        )
                    }
                }

                // Verses
                items(readerState.verses, key = { it.verse }) { verse ->
                    val highlight = readerState.highlights[verse.verse]
                    val isBookmarked = readerState.bookmarkedVerses.contains(verse.verse)
                    val note = readerState.notes[verse.verse]
                    val isSpeaking = viewModel.ttsHelper.currentSpeakingVerse == verse.verse

                    VerseItemRow(
                        verse = verse,
                        settings = settings,
                        highlightHex = highlight?.colorHex,
                        isBookmarked = isBookmarked,
                        hasNote = note != null,
                        isSpeaking = isSpeaking,
                        textColor = readerText,
                        mutedColor = readerMuted,
                        onClick = { viewModel.setSelectedVerse(verse) }
                    )
                }

                // Chapter Navigation Footer
                item {
                    Spacer(modifier = Modifier.height(16.dp))
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 16.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Surface(
                            onClick = { viewModel.previousChapter() },
                            shape = RoundedCornerShape(12.dp),
                            color = readerCardBg,
                            border = androidx.compose.foundation.BorderStroke(1.dp, readerBorder),
                            modifier = Modifier.testTag("previous_chapter_button")
                        ) {
                            Row(
                                modifier = Modifier.padding(horizontal = 14.dp, vertical = 10.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Icon(
                                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                    contentDescription = "Previous Chapter",
                                    tint = readerText,
                                    modifier = Modifier.size(18.dp)
                                )
                                Spacer(modifier = Modifier.width(6.dp))
                                Text(
                                    text = "Previous",
                                    style = MaterialTheme.typography.labelLarge,
                                    color = readerText
                                )
                            }
                        }

                        Text(
                            text = "${readerState.currentChapter} of ${readerState.currentBook.chaptersCount}",
                            style = MaterialTheme.typography.bodySmall,
                            color = readerMuted
                        )

                        Surface(
                            onClick = { viewModel.nextChapter() },
                            shape = RoundedCornerShape(12.dp),
                            color = readerCardBg,
                            border = androidx.compose.foundation.BorderStroke(1.dp, readerBorder),
                            modifier = Modifier.testTag("next_chapter_button")
                        ) {
                            Row(
                                modifier = Modifier.padding(horizontal = 14.dp, vertical = 10.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = "Next",
                                    style = MaterialTheme.typography.labelLarge,
                                    color = readerText
                                )
                                Spacer(modifier = Modifier.width(6.dp))
                                Icon(
                                    imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                                    contentDescription = "Next Chapter",
                                    tint = readerText,
                                    modifier = Modifier.size(18.dp)
                                )
                            }
                        }
                    }
                    Spacer(modifier = Modifier.height(24.dp))
                }
            }
        }
    }

    // Modal Sheets & Dialogs
    if (showBookPicker) {
        BookChapterPickerSheet(
            currentBook = readerState.currentBook,
            currentChapter = readerState.currentChapter,
            onSelect = { bookId, chapter ->
                viewModel.selectBookAndChapter(bookId, chapter)
            },
            onDismiss = { showBookPicker = false }
        )
    }

    if (showTypographySettings) {
        TypographySettingsSheet(
            settings = settings,
            onTranslationChange = { viewModel.updateTranslation(it) },
            onThemeChange = { viewModel.updateTheme(it) },
            onFontChange = { viewModel.updateFont(it) },
            onFontSizeChange = { viewModel.updateFontSize(it) },
            onLineSpacingChange = { viewModel.updateLineSpacing(it) },
            onToggleVerseNumbers = { viewModel.toggleVerseNumbers(it) },
            onDismiss = { showTypographySettings = false }
        )
    }

    val selectedVerse = readerState.selectedVerse
    if (selectedVerse != null) {
        val isBookmarked = readerState.bookmarkedVerses.contains(selectedVerse.verse)
        val highlight = readerState.highlights[selectedVerse.verse]

        VerseActionSheet(
            verse = selectedVerse,
            isBookmarked = isBookmarked,
            currentHighlightHex = highlight?.colorHex,
            onHighlight = { colorHex ->
                viewModel.setHighlight(selectedVerse, colorHex)
            },
            onRemoveHighlight = {
                viewModel.removeHighlight(selectedVerse)
            },
            onToggleBookmark = {
                viewModel.toggleBookmark(selectedVerse)
            },
            onAddNote = {
                viewModel.openNoteEditor(selectedVerse)
            },
            onSpeak = {
                viewModel.speakVerse(selectedVerse)
            },
            onShare = {
                viewModel.openShareDialog(selectedVerse)
            },
            onDismiss = {
                viewModel.setSelectedVerse(null)
            }
        )
    }

    if (editingNoteVerse != null) {
        val existingNote = readerState.notes[editingNoteVerse!!.verse]?.noteContent ?: ""
        NoteEditorDialog(
            verse = editingNoteVerse!!,
            initialNote = existingNote,
            onSave = { content ->
                viewModel.saveNote(editingNoteVerse!!, content)
            },
            onDismiss = {
                viewModel.closeNoteEditor()
            }
        )
    }

    if (shareVerse != null) {
        VerseShareDialog(
            verse = shareVerse!!,
            onDismiss = { viewModel.closeShareDialog() }
        )
    }
}

@Composable
private fun VerseItemRow(
    verse: BibleVerse,
    settings: com.example.util.ReaderSettings,
    highlightHex: String?,
    isBookmarked: Boolean,
    hasNote: Boolean,
    isSpeaking: Boolean,
    textColor: Color,
    mutedColor: Color,
    onClick: () -> Unit
) {
    val highlightColor = remember(highlightHex) {
        if (highlightHex != null) {
            try {
                Color(android.graphics.Color.parseColor(highlightHex)).copy(alpha = 0.45f)
            } catch (e: Exception) {
                null
            }
        } else null
    }

    val backgroundColor = when {
        isSpeaking -> MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.4f)
        highlightColor != null -> highlightColor
        else -> Color.Transparent
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp))
            .background(backgroundColor)
            .clickable(onClick = onClick)
            .padding(horizontal = 8.dp, vertical = 6.dp)
            .testTag("verse_row_${verse.verse}")
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.Top
        ) {
            // Verse Number
            if (settings.showVerseNumbers) {
                Text(
                    text = "${verse.verse}",
                    style = MaterialTheme.typography.labelMedium.copy(
                        fontSize = (settings.fontSize.size.value * 0.75f).sp
                    ),
                    fontWeight = FontWeight.Bold,
                    color = if (isSpeaking) MaterialTheme.colorScheme.primary else mutedColor,
                    modifier = Modifier
                        .width(28.dp)
                        .padding(top = 2.dp)
                )
            }

            // Verse Text
            Text(
                text = verse.text,
                style = MaterialTheme.typography.bodyLarge.copy(
                    fontSize = settings.fontSize.size,
                    lineHeight = (settings.fontSize.lineHeight.value * settings.lineSpacing.multiplier).sp,
                    fontFamily = settings.font.fontFamily
                ),
                color = textColor,
                modifier = Modifier.weight(1f)
            )

            // Indicators (Bookmark / Note)
            if (isBookmarked || hasNote) {
                Row(
                    modifier = Modifier.padding(start = 6.dp),
                    horizontalArrangement = Arrangement.spacedBy(2.dp)
                ) {
                    if (isBookmarked) {
                        Icon(
                            imageVector = Icons.Default.Bookmark,
                            contentDescription = "Bookmarked",
                            tint = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.size(14.dp)
                        )
                    }
                    if (hasNote) {
                        Icon(
                            imageVector = Icons.Default.EditNote,
                            contentDescription = "Has Note",
                            tint = MaterialTheme.colorScheme.tertiary,
                            modifier = Modifier.size(16.dp)
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun AudioPlayerBar(
    viewModel: BibleViewModel,
    readerText: Color,
    cardBg: Color,
    onClose: () -> Unit
) {
    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = cardBg),
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 6.dp)
            .testTag("audio_player_bar")
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                FilledTonalIconButton(
                    onClick = {
                        if (viewModel.ttsHelper.isSpeaking) {
                            viewModel.stopSpeaking()
                        } else {
                            viewModel.speakCurrentChapter()
                        }
                    },
                    modifier = Modifier.size(40.dp)
                ) {
                    Icon(
                        imageVector = if (viewModel.ttsHelper.isSpeaking) Icons.Default.Pause else Icons.Default.PlayArrow,
                        contentDescription = if (viewModel.ttsHelper.isSpeaking) "Pause" else "Play"
                    )
                }

                FilledTonalIconButton(
                    onClick = { viewModel.stopSpeaking() },
                    modifier = Modifier.size(40.dp)
                ) {
                    Icon(imageVector = Icons.Default.Stop, contentDescription = "Stop")
                }

                Column {
                    Text(
                        text = if (viewModel.ttsHelper.isSpeaking) "Reading Aloud..." else "Audio Ready",
                        style = MaterialTheme.typography.labelMedium,
                        fontWeight = FontWeight.Bold,
                        color = readerText
                    )
                    Text(
                        text = "Speed: ${"%.1f".format(viewModel.ttsHelper.speechRate)}x",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }

            // Speed step & Close
            Row(verticalAlignment = Alignment.CenterVertically) {
                Surface(
                    onClick = {
                        val nextRate = when (viewModel.ttsHelper.speechRate) {
                            0.75f -> 1.0f
                            1.0f -> 1.25f
                            1.25f -> 1.5f
                            else -> 0.75f
                        }
                        viewModel.ttsHelper.setRate(nextRate)
                    },
                    shape = RoundedCornerShape(8.dp),
                    color = MaterialTheme.colorScheme.surfaceVariant,
                    modifier = Modifier.padding(end = 6.dp)
                ) {
                    Text(
                        text = "${"%.2f".format(viewModel.ttsHelper.speechRate)}x",
                        style = MaterialTheme.typography.labelSmall,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                    )
                }

                IconButton(onClick = onClose, modifier = Modifier.size(32.dp)) {
                    Icon(Icons.Default.Close, contentDescription = "Close Audio")
                }
            }
        }
    }
}

private data class ThemeColors(
    val bg: Color,
    val text: Color,
    val muted: Color,
    val cardBg: Color,
    val border: Color
)
