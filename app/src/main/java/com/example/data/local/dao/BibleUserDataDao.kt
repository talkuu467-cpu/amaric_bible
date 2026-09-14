package com.example.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.data.local.entity.BookmarkEntity
import com.example.data.local.entity.HighlightEntity
import com.example.data.local.entity.ReadingHistoryEntity
import com.example.data.local.entity.ReadingPlanProgressEntity
import com.example.data.local.entity.VerseNoteEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface BibleUserDataDao {

    // Bookmarks
    @Query("SELECT * FROM bookmarks ORDER BY createdAt DESC")
    fun getAllBookmarks(): Flow<List<BookmarkEntity>>

    @Query("SELECT * FROM bookmarks WHERE bookId = :bookId AND chapter = :chapter AND verse = :verse LIMIT 1")
    suspend fun getBookmark(bookId: Int, chapter: Int, verse: Int): BookmarkEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBookmark(bookmark: BookmarkEntity): Long

    @Query("DELETE FROM bookmarks WHERE bookId = :bookId AND chapter = :chapter AND verse = :verse")
    suspend fun deleteBookmark(bookId: Int, chapter: Int, verse: Int)

    @Query("DELETE FROM bookmarks WHERE id = :id")
    suspend fun deleteBookmarkById(id: Long)

    // Highlights
    @Query("SELECT * FROM highlights ORDER BY createdAt DESC")
    fun getAllHighlights(): Flow<List<HighlightEntity>>

    @Query("SELECT * FROM highlights WHERE bookId = :bookId AND chapter = :chapter")
    fun getHighlightsForChapter(bookId: Int, chapter: Int): Flow<List<HighlightEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertHighlight(highlight: HighlightEntity): Long

    @Query("DELETE FROM highlights WHERE bookId = :bookId AND chapter = :chapter AND verse = :verse")
    suspend fun deleteHighlight(bookId: Int, chapter: Int, verse: Int)

    @Query("DELETE FROM highlights WHERE id = :id")
    suspend fun deleteHighlightById(id: Long)

    // Notes
    @Query("SELECT * FROM verse_notes ORDER BY updatedAt DESC")
    fun getAllNotes(): Flow<List<VerseNoteEntity>>

    @Query("SELECT * FROM verse_notes WHERE bookId = :bookId AND chapter = :chapter AND verse = :verse LIMIT 1")
    suspend fun getNoteForVerse(bookId: Int, chapter: Int, verse: Int): VerseNoteEntity?

    @Query("SELECT * FROM verse_notes WHERE bookId = :bookId AND chapter = :chapter")
    fun getNotesForChapter(bookId: Int, chapter: Int): Flow<List<VerseNoteEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNote(note: VerseNoteEntity): Long

    @Update
    suspend fun updateNote(note: VerseNoteEntity)

    @Query("DELETE FROM verse_notes WHERE id = :id")
    suspend fun deleteNoteById(id: Long)

    // Reading Plan Progress
    @Query("SELECT * FROM reading_plan_progress WHERE planId = :planId")
    fun getPlanProgress(planId: String): Flow<List<ReadingPlanProgressEntity>>

    @Query("SELECT * FROM reading_plan_progress")
    fun getAllPlanProgress(): Flow<List<ReadingPlanProgressEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun setDayProgress(progress: ReadingPlanProgressEntity)

    @Query("DELETE FROM reading_plan_progress WHERE planId = :planId")
    suspend fun resetPlanProgress(planId: String)

    // Reading History
    @Query("SELECT * FROM reading_history ORDER BY timestamp DESC LIMIT 20")
    fun getReadingHistory(): Flow<List<ReadingHistoryEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertReadingHistory(history: ReadingHistoryEntity)
}
