package com.example.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.data.local.dao.BibleUserDataDao
import com.example.data.local.entity.BookmarkEntity
import com.example.data.local.entity.HighlightEntity
import com.example.data.local.entity.ReadingHistoryEntity
import com.example.data.local.entity.ReadingPlanProgressEntity
import com.example.data.local.entity.VerseNoteEntity

@Database(
    entities = [
        BookmarkEntity::class,
        HighlightEntity::class,
        VerseNoteEntity::class,
        ReadingPlanProgressEntity::class,
        ReadingHistoryEntity::class
    ],
    version = 1,
    exportSchema = false
)
abstract class BibleDatabase : RoomDatabase() {

    abstract fun bibleUserDataDao(): BibleUserDataDao

    companion object {
        @Volatile
        private var INSTANCE: BibleDatabase? = null

        fun getDatabase(context: Context): BibleDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    BibleDatabase::class.java,
                    "holy_bible_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}
