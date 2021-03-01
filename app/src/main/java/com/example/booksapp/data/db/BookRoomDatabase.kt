package com.example.booksapp.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import android.content.Context
import androidx.room.Room

@Database(entities = [Book::class], version = 1)
abstract class BookRoomDatabase : RoomDatabase() {

    abstract fun bookDao(): BookDao

    companion object {
        @Volatile
        private var INSTANCE: BookRoomDatabase? = null

        fun getDatabase(context: Context): BookRoomDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    BookRoomDatabase::class.java,
                    "books_database"
                ).fallbackToDestructiveMigration().build()

                INSTANCE = instance
                instance
            }
        }
    }
}