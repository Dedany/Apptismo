package com.dperez.apptismo.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Emotion::class,Name::class,Questions::class], version = 4, exportSchema = true)
abstract class AppDatabase : RoomDatabase() {
    abstract fun emotionDao(): EmotionDao
    abstract fun nameDao(): NameDao
    abstract fun questionsDao(): QuestionsDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext, AppDatabase::class.java, "apptismo_database"
                ).fallbackToDestructiveMigration().build()
                INSTANCE = instance
                instance
            }
        }
    }
}
