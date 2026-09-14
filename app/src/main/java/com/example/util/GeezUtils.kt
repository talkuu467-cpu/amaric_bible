package com.example.util

object GeezUtils {
    private val geezOnes = arrayOf("", "፩", "፪", "፫", "፬", "፭", "፮", "፯", "፰", "፱")
    private val geezTens = arrayOf("", "፲", "፳", "፴", "፵", "፶", "፷", "፸", "፹", "፺")

    fun toGeez(num: Int): String {
        if (num <= 0) return num.toString()
        if (num < 10) return geezOnes[num]
        if (num < 100) {
            val tens = num / 10
            val ones = num % 10
            return geezTens[tens] + geezOnes[ones]
        }
        if (num < 10000) {
            val hundreds = num / 100
            val rem = num % 100
            val hStr = if (hundreds == 1) "፻" else toGeez(hundreds) + "፻"
            val rStr = if (rem > 0) toGeez(rem) else ""
            return hStr + rStr
        }
        return num.toString()
    }

    /**
     * Formats chapter for header, e.g. "ምዕራፍ ፩(1)"
     */
    fun formatChapterHeader(chapter: Int): String {
        return "ምዕራፍ ${toGeez(chapter)}($chapter)"
    }

    /**
     * Formats chapter short, e.g. "፩(1)"
     */
    fun formatChapterShort(chapter: Int): String {
        return "${toGeez(chapter)}($chapter)"
    }
}
