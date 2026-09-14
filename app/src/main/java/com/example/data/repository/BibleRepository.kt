package com.example.data.repository

import android.content.Context
import com.example.data.BibleDataProvider
import com.example.data.BibleScriptureStore
import com.example.data.model.BibleBook
import com.example.data.model.BibleTranslation
import com.example.data.model.BibleVerse
import com.example.data.model.DailyVerse
import com.example.data.model.ReadingPlan
import com.example.data.model.Testament
import com.example.data.model.TopicalVerse
import java.util.Calendar

class BibleRepository {

    fun initAssets(context: Context) {
        BibleScriptureStore.loadAmharicAssets(context)
    }

    fun getAllBooks(): List<BibleBook> = BibleDataProvider.books

    fun getOldTestamentBooks(): List<BibleBook> =
        BibleDataProvider.books.filter { it.testament == Testament.OLD }

    fun getNewTestamentBooks(): List<BibleBook> =
        BibleDataProvider.books.filter { it.testament == Testament.NEW }

    fun getBookById(id: Int): BibleBook? = BibleDataProvider.getBookById(id)

    fun getVersesForChapter(
        bookId: Int,
        chapter: Int,
        translation: BibleTranslation = BibleTranslation.AMHARIC
    ): List<BibleVerse> =
        BibleScriptureStore.getVersesForChapter(bookId, chapter, translation)

    fun getDailyVerseForToday(): DailyVerse {
        val dayOfYear = Calendar.getInstance().get(Calendar.DAY_OF_YEAR)
        val dailyList = BibleScriptureStore.dailyVerses
        return dailyList[(dayOfYear - 1) % dailyList.size]
    }

    fun getAllDailyVerses(): List<DailyVerse> = BibleScriptureStore.dailyVerses

    fun getTopicalCollections(): List<TopicalVerse> = BibleScriptureStore.topicalCollections

    fun getAllReadingPlans(): List<ReadingPlan> = BibleScriptureStore.readingPlans

    fun getReadingPlanById(id: String): ReadingPlan? =
        BibleScriptureStore.readingPlans.find { it.id == id }

    fun search(
        query: String,
        testamentFilter: Testament? = null,
        translation: BibleTranslation = BibleTranslation.AMHARIC
    ): List<BibleVerse> =
        BibleScriptureStore.searchVerses(query, testamentFilter, translation)
}
