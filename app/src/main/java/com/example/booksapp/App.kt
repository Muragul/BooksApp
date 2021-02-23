package com.example.booksapp

import android.app.Application
import com.example.booksapp.data.BookRepository
import com.example.booksapp.data.db.BookRoomDatabase

class App : Application() {
    private val database by lazy { BookRoomDatabase.getDatabase(this) }
    val repository by lazy { BookRepository(database.bookDao()) }
}