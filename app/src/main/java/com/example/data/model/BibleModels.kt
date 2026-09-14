package com.example.data.model

data class BibleBook(
    val id: Int,
    val name: String,
    val amharicName: String,
    val abbreviation: String,
    val amharicAbbreviation: String,
    val testament: Testament,
    val category: BookCategory,
    val chaptersCount: Int,
    val summary: String,
    val amharicSummary: String = ""
) {
    fun getDisplayName(translation: BibleTranslation): String {
        return if (translation == BibleTranslation.AMHARIC && amharicName.isNotBlank()) amharicName else name
    }

    fun localizedName(translation: BibleTranslation): String = getDisplayName(translation)

    fun getDisplayAbbr(translation: BibleTranslation): String {
        return if (translation == BibleTranslation.AMHARIC && amharicAbbreviation.isNotBlank()) amharicAbbreviation else abbreviation
    }
}

enum class BibleTranslation(
    val id: String,
    val displayName: String,
    val language: String,
    val shortCode: String
) {
    AMHARIC("amh", "መጽሐፍ ቅዱስ (አማርኛ)", "አማርኛ", "አማርኛ"),
    KJV("kjv", "King James Version", "English", "KJV"),
    WEB("web", "World English Bible", "English", "WEB")
}

enum class Testament(val displayName: String, val amharicName: String) {
    OLD("Old Testament", "ብሉይ ኪዳን"),
    NEW("New Testament", "ሐዲስ ኪዳን")
}

enum class BookCategory(val displayName: String, val amharicName: String) {
    LAW("Law & Pentateuch", "ሕግና ኦሪት"),
    HISTORY("Historical", "ታሪክ"),
    POETRY("Poetry & Wisdom", "ቅኔና ጥበብ"),
    MAJOR_PROPHETS("Major Prophets", "ታላላቅ ነቢያት"),
    MINOR_PROPHETS("Minor Prophets", "ደቂቀ ነቢያት"),
    GOSPELS("Gospels", "ወንጌላት"),
    CHURCH_HISTORY("Church History", "የቤተ ክርስቲያን ታሪክ"),
    EPISTLES("Epistles & Letters", "መልእክታት"),
    PROPHECY("Prophecy", "ራእይና ትንቢት")
}

data class BibleVerse(
    val bookId: Int,
    val bookName: String,
    val chapter: Int,
    val verse: Int,
    val text: String,
    val translation: BibleTranslation = BibleTranslation.KJV
) {
    val reference: String
        get() = "$bookName $chapter:$verse"
}

data class DailyVerse(
    val id: Int,
    val bookName: String,
    val chapter: Int,
    val verse: Int,
    val text: String,
    val theme: String,
    val reflection: String,
    val prayer: String
) {
    val reference: String
        get() = "$bookName $chapter:$verse"
}

data class TopicalVerse(
    val topic: String,
    val topicDescription: String,
    val iconName: String,
    val verses: List<BibleVerse>
)

data class ReadingPlan(
    val id: String,
    val title: String,
    val subtitle: String,
    val description: String,
    val durationDays: Int,
    val category: String,
    val days: List<ReadingPlanDay>
)

data class ReadingPlanDay(
    val dayNumber: Int,
    val title: String,
    val passageReference: String,
    val bookId: Int,
    val chapter: Int,
    val startVerse: Int = 1,
    val endVerse: Int = 100,
    val devotionalNote: String,
    val verses: List<BibleVerse> = emptyList()
)
