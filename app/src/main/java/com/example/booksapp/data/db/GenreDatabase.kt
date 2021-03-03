package com.example.booksapp.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Genre::class], version = 1)
abstract class GenreDatabase : RoomDatabase() {

    abstract fun genreDao(): GenreDao

    companion object {
        @Volatile
        private var INSTANCE: GenreDatabase? = null

        fun getDatabase(context: Context): GenreDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    GenreDatabase::class.java,
                    "genres_database"
                ).fallbackToDestructiveMigration().build()

                INSTANCE = instance
                instance
            }
        }
    }
}