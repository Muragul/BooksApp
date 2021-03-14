package com.example.booksapp.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.booksapp.data.model.Book
import com.example.booksapp.data.model.Genre

@Database(entities = [Book::class, Genre::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun appDao(): AppDao
}