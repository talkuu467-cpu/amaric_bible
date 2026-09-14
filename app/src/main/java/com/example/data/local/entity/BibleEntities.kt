package com.example.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "bookmarks")
data class BookmarkEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val bookId: Int,
    val bookName: String,
    val chapter: Int,
    val verse: Int,
    val text: String,
    val createdAt: Long = System.currentTimeMillis()
)

@Entity(tableName = "highlights")
data class HighlightEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val bookId: Int,
    val bookName: String,
    val chapter: Int,
    val verse: Int,
    val text: String,
    val colorHex: String, // "#FFE082", "#FFCDD2", "#C8E6C9", "#BBDEFB", "#E1BEE7"
    val createdAt: Long = System.currentTimeMillis()
)

@Entity(tableName = "verse_notes")
data class VerseNoteEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val bookId: Int,
    val bookName: String,
    val chapter: Int,
    val verse: Int,
    val verseText: String,
    val noteContent: String,
    val updatedAt: Long = System.currentTimeMillis()
)

@Entity(tableName = "reading_plan_progress", primaryKeys = ["planId", "dayNumber"])
data class ReadingPlanProgressEntity(
    val planId: String,
    val dayNumber: Int,
    val isCompleted: Boolean = false,
    val completedAt: Long? = null
)

@Entity(tableName = "reading_history")
data class ReadingHistoryEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val bookId: Int,
    val bookName: String,
    val chapter: Int,
    val timestamp: Long = System.currentTimeMillis()
)
