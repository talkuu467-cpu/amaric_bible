package com.example.util

import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import com.example.data.model.BibleTranslation

enum class ReaderTheme(val displayName: String) {
    PARCHMENT("Warm Parchment"),
    IVORY("Clean Light"),
    SEPIA("Classic Sepia"),
    NIGHT("Deep Dark")
}

enum class ReaderFont(val displayName: String, val fontFamily: FontFamily) {
    SERIF("Editorial Serif", FontFamily.Serif),
    SANS_SERIF("Clean Sans", FontFamily.SansSerif),
    MONOSPACE("Classic Type", FontFamily.Monospace)
}

enum class ReaderFontSize(val displayName: String, val size: TextUnit, val lineHeight: TextUnit) {
    SMALL("Small", 15.sp, 24.sp),
    MEDIUM("Medium", 18.sp, 28.sp),
    LARGE("Large", 22.sp, 34.sp),
    EXTRA_LARGE("X-Large", 26.sp, 40.sp)
}

enum class ReaderLineSpacing(val displayName: String, val multiplier: Float) {
    COMPACT("Compact", 1.25f),
    NORMAL("Normal", 1.5f),
    RELAXED("Spacious", 1.8f)
}

data class ReaderSettings(
    val translation: BibleTranslation = BibleTranslation.AMHARIC,
    val theme: ReaderTheme = ReaderTheme.PARCHMENT,
    val font: ReaderFont = ReaderFont.SERIF,
    val fontSize: ReaderFontSize = ReaderFontSize.MEDIUM,
    val lineSpacing: ReaderLineSpacing = ReaderLineSpacing.NORMAL,
    val showVerseNumbers: Boolean = true,
    val keepScreenOn: Boolean = false
)
