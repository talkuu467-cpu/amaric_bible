package com.example.ui.screens

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.widget.Toast
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AutoStories
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.ContentCopy
import androidx.compose.material.icons.filled.EditNote
import androidx.compose.material.icons.filled.Language
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.Stop
import androidx.compose.material.icons.filled.VolumeUp
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledTonalIconButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.data.model.BibleVerse
import com.example.ui.BibleViewModel
import com.example.ui.components.AboutDeveloperDialog
import com.example.ui.components.BibleDrawerContent
import com.example.ui.components.ChapterPickerDialog
import com.example.ui.components.NoteEditorDialog
import com.example.ui.components.TranslationPickerDialog
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
import com.example.util.GeezUtils
import com.example.util.ReaderSettings
import com.example.util.ReaderTheme
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReaderScreen(
    viewModel: BibleViewModel,
    onNavigateToSearch: () -> Unit = {},
    onNavigateToPlans: () -> Unit = {},
    onNavigateToJournal: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    val readerState by viewModel.readerUiState.collectAsStateWithLifecycle()
    val settings by viewModel.settings.collectAsStateWithLifecycle()
    val editingNoteVerse by viewModel.editingNoteVerse.collectAsStateWithLifecycle()
    val shareVerse by viewModel.shareVerse.collectAsStateWithLifecycle()

    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    val context = LocalContext.current

    var showChapterPicker by remember { mutableStateOf(false) }
    var showTranslationPicker by remember { mutableStateOf(false) }
    var showTypographySettings by remember { mutableStateOf(false) }
    var showAudioControls by remember { mutableStateOf(false) }
    var showOverflowMenu by remember { mutableStateOf(false) }
    var showAboutDialog by remember { mutableStateOf(false) }

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

    // Dynamic reader theme colors (Deep pure dark by default to match screenshot)
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
            Color(0xFF111111),
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
            Color(0xFF0F0F0F),
            Color(0xFFFFFFFF),
            Color(0xFFAAAAAA),
            Color(0xFF1A1A1A),
            Color(0xFF2B2B2B)
        )
    }

    val goldColor = Color(0xFFE5A93C)

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            BibleDrawerContent(
                currentBook = readerState.currentBook,
                onSelectBook = { book ->
                    viewModel.selectBookAndChapter(book.id, 1)
                    scope.launch { drawerState.close() }
                },
                onCloseDrawer = {
                    scope.launch { drawerState.close() }
                },
                onShowAbout = {
                    showAboutDialog = true
                }
            )
        },
        modifier = modifier.testTag("bible_reader_drawer_container")
    ) {
        Scaffold(
            topBar = {
                TopAppBar(
                    navigationIcon = {
                        IconButton(
                            onClick = {
                                scope.launch {
                                    if (drawerState.isClosed) drawerState.open() else drawerState.close()
                                }
                            },
                            modifier = Modifier.testTag("drawer_menu_button")
                        ) {
                            Icon(
                                imageVector = Icons.Default.Menu,
                                contentDescription = "Open Book Drawer",
                                tint = readerText
                            )
                        }
                    },
                    title = {
                        Text(
                            text = readerState.currentBook.amharicName,
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.Bold,
                            color = readerText,
                            modifier = Modifier.testTag("top_book_title")
                        )
                    },
                    actions = {
                        // 1. Search Icon
                        IconButton(
                            onClick = onNavigateToSearch,
                            modifier = Modifier.testTag("top_search_button")
                        ) {
                            Icon(
                                imageVector = Icons.Default.Search,
                                contentDescription = "Search Scripture",
                                tint = readerText
                            )
                        }

                        // 2. Language / Globe Icon
                        IconButton(
                            onClick = { showTranslationPicker = true },
                            modifier = Modifier.testTag("top_language_button")
                        ) {
                            Icon(
                                imageVector = Icons.Default.Language,
                                contentDescription = "Change Language/Translation",
                                tint = readerText
                            )
                        }

                        // 3. Overflow Menu (3 vertical dots)
                        Box {
                            IconButton(
                                onClick = { showOverflowMenu = !showOverflowMenu },
                                modifier = Modifier.testTag("top_overflow_menu_button")
                            ) {
                                Icon(
                                    imageVector = Icons.Default.MoreVert,
                                    contentDescription = "More Options",
                                    tint = readerText
                                )
                            }

                            DropdownMenu(
                                expanded = showOverflowMenu,
                                onDismissRequest = { showOverflowMenu = false },
                                modifier = Modifier.background(readerCardBg)
                            ) {
                                DropdownMenuItem(
                                    text = { Text("🔊 ድምፅ (Audio Reader)", color = readerText) },
                                    leadingIcon = { Icon(Icons.Default.VolumeUp, contentDescription = null, tint = goldColor) },
                                    onClick = {
                                        showOverflowMenu = false
                                        showAudioControls = !showAudioControls
                                        if (showAudioControls && !viewModel.ttsHelper.isSpeaking) {
                                            viewModel.speakCurrentChapter()
                                        }
                                    }
                                )
                                DropdownMenuItem(
                                    text = { Text("🔍 ፈልግ (Search)", color = readerText) },
                                    leadingIcon = { Icon(Icons.Default.Search, contentDescription = null, tint = goldColor) },
                                    onClick = {
                                        showOverflowMenu = false
                                        onNavigateToSearch()
                                    }
                                )
                                DropdownMenuItem(
                                    text = { Text("🔖 የተቀመጡ (Saved / Notes)", color = readerText) },
                                    leadingIcon = { Icon(Icons.Default.Bookmark, contentDescription = null, tint = goldColor) },
                                    onClick = {
                                        showOverflowMenu = false
                                        onNavigateToJournal()
                                    }
                                )
                                DropdownMenuItem(
                                    text = { Text("📅 የንባብ ዕቅድ (Daily Plans)", color = readerText) },
                                    leadingIcon = { Icon(Icons.Default.CalendarMonth, contentDescription = null, tint = goldColor) },
                                    onClick = {
                                        showOverflowMenu = false
                                        onNavigateToPlans()
                                    }
                                )
                                DropdownMenuItem(
                                    text = { Text("👤 ስለ አዘጋጁ (About Developer)", color = readerText) },
                                    leadingIcon = { Icon(Icons.Default.Person, contentDescription = null, tint = goldColor) },
                                    onClick = {
                                         showOverflowMenu = false
                                         showAboutDialog = true
                                    }
                                )
                                HorizontalDivider(color = readerBorder)
                                DropdownMenuItem(
                                    text = { Text("📋 ምዕራፉን ቅዳ (Copy Chapter)", color = readerText) },
                                    leadingIcon = { Icon(Icons.Default.ContentCopy, contentDescription = null, tint = goldColor) },
                                    onClick = {
                                        showOverflowMenu = false
                                        val fullText = readerState.verses.joinToString("\n") { "${it.verse}. ${it.text}" }
                                        val clipboard = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
                                        val clip = ClipData.newPlainText("Chapter Text", fullText)
                                        clipboard.setPrimaryClip(clip)
                                        Toast.makeText(context, "ምዕራፉ ተቀድቷል (Chapter Copied)", Toast.LENGTH_SHORT).show()
                                    }
                                )
                                DropdownMenuItem(
                                    text = { Text("📤 ምዕራፉን አጋራ (Share Chapter)", color = readerText) },
                                    leadingIcon = { Icon(Icons.Default.Share, contentDescription = null, tint = goldColor) },
                                    onClick = {
                                        showOverflowMenu = false
                                        val fullText = "${readerState.currentBook.amharicName} ምዕራፍ ${readerState.currentChapter}\n\n" +
                                                readerState.verses.joinToString("\n") { "${it.verse}. ${it.text}" }
                                        val sendIntent = Intent().apply {
                                            action = Intent.ACTION_SEND
                                            putExtra(Intent.EXTRA_TEXT, fullText)
                                            type = "text/plain"
                                        }
                                        context.startActivity(Intent.createChooser(sendIntent, "Share Chapter"))
                                    }
                                )
                            }
                        }
                    },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = readerBg,
                        titleContentColor = readerText
                    )
                )
            },
            bottomBar = {
                // Bottom Bar matching Screenshot 1 exactly
                Surface(
                    color = readerBg,
                    border = androidx.compose.foundation.BorderStroke(1.dp, readerBorder.copy(alpha = 0.5f)),
                    modifier = Modifier
                        .fillMaxWidth()
                        .testTag("reader_bottom_bar")
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 20.dp, vertical = 14.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        // 1. "ያለፈ" (Previous Chapter)
                        Box(
                            modifier = Modifier
                                .clickable { viewModel.previousChapter() }
                                .padding(horizontal = 8.dp, vertical = 6.dp)
                                .testTag("btn_previous_chapter")
                        ) {
                            Text(
                                text = "ያለፈ",
                                color = readerText,
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }

                        // 2. "ምዕራፍ ፩(1)" (Current Chapter & Picker)
                        Box(
                            modifier = Modifier
                                .clickable { showChapterPicker = true }
                                .padding(horizontal = 12.dp, vertical = 6.dp)
                                .testTag("btn_chapter_indicator")
                        ) {
                            Text(
                                text = GeezUtils.formatChapterHeader(readerState.currentChapter),
                                color = readerText,
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }

                        // 3. Settings Gear Icon ⚙
                        IconButton(
                            onClick = { showTypographySettings = true },
                            modifier = Modifier.testTag("btn_reader_settings")
                        ) {
                            Icon(
                                imageVector = Icons.Default.Settings,
                                contentDescription = "Settings",
                                tint = readerText,
                                modifier = Modifier.size(24.dp)
                            )
                        }

                        // 4. "ቀጣይ" (Next Chapter)
                        Box(
                            modifier = Modifier
                                .clickable { viewModel.nextChapter() }
                                .padding(horizontal = 8.dp, vertical = 6.dp)
                                .testTag("btn_next_chapter")
                        ) {
                            Text(
                                text = "ቀጣይ",
                                color = readerText,
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }
            },
            containerColor = readerBg,
            modifier = Modifier.fillMaxSize()
        ) { innerPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
            ) {
                // Collapsible Audio Bar if active
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

                // Scripture Verses List
                LazyColumn(
                    state = listState,
                    contentPadding = PaddingValues(horizontal = 16.dp, vertical = 12.dp),
                    verticalArrangement = Arrangement.spacedBy(10.dp),
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth()
                ) {
                    // Centered Header: ምዕራፍ ፩(1)
                    item {
                        Box(
                            contentAlignment = Alignment.Center,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 8.dp, bottom = 16.dp)
                        ) {
                            Text(
                                text = GeezUtils.formatChapterHeader(readerState.currentChapter),
                                color = readerText,
                                fontSize = 21.sp,
                                fontWeight = FontWeight.Bold,
                                textAlign = TextAlign.Center
                            )
                        }
                    }

                    // Verses with stacked Geez numeral over Arabic numeral
                    items(readerState.verses, key = { it.verse }) { verse ->
                        val highlight = readerState.highlights[verse.verse]
                        val isBookmarked = readerState.bookmarkedVerses.contains(verse.verse)
                        val note = readerState.notes[verse.verse]
                        val isSpeaking = viewModel.ttsHelper.currentSpeakingVerse == verse.verse

                        ExactVerseRow(
                            verse = verse,
                            settings = settings,
                            highlightHex = highlight?.colorHex,
                            isBookmarked = isBookmarked,
                            hasNote = note != null,
                            isSpeaking = isSpeaking,
                            textColor = readerText,
                            goldColor = goldColor,
                            onClick = { viewModel.setSelectedVerse(verse) }
                        )
                    }

                    item {
                        Spacer(modifier = Modifier.height(24.dp))
                    }
                }
            }
        }
    }

    // Modal Dialogs & Sheets
    if (showChapterPicker) {
        ChapterPickerDialog(
            book = readerState.currentBook,
            currentChapter = readerState.currentChapter,
            onSelectChapter = { ch ->
                viewModel.selectBookAndChapter(readerState.currentBook.id, ch)
            },
            onDismiss = { showChapterPicker = false }
        )
    }

    if (showTranslationPicker) {
        TranslationPickerDialog(
            currentTranslation = settings.translation,
            onSelect = { tr ->
                viewModel.updateTranslation(tr)
            },
            onDismiss = { showTranslationPicker = false }
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

    if (showAboutDialog) {
        AboutDeveloperDialog(
            onDismissRequest = { showAboutDialog = false }
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
private fun ExactVerseRow(
    verse: BibleVerse,
    settings: ReaderSettings,
    highlightHex: String?,
    isBookmarked: Boolean,
    hasNote: Boolean,
    isSpeaking: Boolean,
    textColor: Color,
    goldColor: Color,
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
        isSpeaking -> Color(0xFFE5A93C).copy(alpha = 0.25f)
        highlightColor != null -> highlightColor
        else -> Color.Transparent
    }

    val geezNum = remember(verse.verse) { GeezUtils.toGeez(verse.verse) }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(6.dp))
            .background(backgroundColor)
            .clickable(onClick = onClick)
            .padding(vertical = 4.dp, horizontal = 4.dp)
            .testTag("verse_row_${verse.verse}"),
        verticalAlignment = Alignment.Top
    ) {
        // Left Column: Stacked Geez Number & Arabic Number
        if (settings.showVerseNumbers) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .width(30.dp)
                    .padding(top = 2.dp)
            ) {
                Text(
                    text = geezNum,
                    color = goldColor,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )
                Text(
                    text = "${verse.verse}",
                    color = Color(0xFFAAAAAA),
                    fontSize = 11.5.sp,
                    fontWeight = FontWeight.Medium,
                    textAlign = TextAlign.Center
                )
            }

            Spacer(modifier = Modifier.width(10.dp))
        }

        // Verse Body Text
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

        // Indicators
        if (isBookmarked || hasNote) {
            Row(
                modifier = Modifier.padding(start = 6.dp),
                horizontalArrangement = Arrangement.spacedBy(2.dp)
            ) {
                if (isBookmarked) {
                    Icon(
                        imageVector = Icons.Default.Bookmark,
                        contentDescription = "Bookmarked",
                        tint = goldColor,
                        modifier = Modifier.size(16.dp)
                    )
                }
                if (hasNote) {
                    Icon(
                        imageVector = Icons.Default.EditNote,
                        contentDescription = "Has Note",
                        tint = Color(0xFF64B5F6),
                        modifier = Modifier.size(16.dp)
                    )
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
                        text = if (viewModel.ttsHelper.isSpeaking) "በድምፅ እያነበበ ነው..." else "የድምፅ ንባብ ዝግጁ",
                        style = MaterialTheme.typography.labelMedium,
                        fontWeight = FontWeight.Bold,
                        color = readerText
                    )
                    Text(
                        text = "ፍጥነት: ${"%.1f".format(viewModel.ttsHelper.speechRate)}x",
                        style = MaterialTheme.typography.bodySmall,
                        color = Color(0xFFAAAAAA)
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
