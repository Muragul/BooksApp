package com.example.booksapp.domain

import com.example.booksapp.data.db.Book
import kotlinx.coroutines.flow.Flow

interface BookListRepository {
    fun loadData(): Flow<List<Book>>
    fun loadData(genreId: Int): Flow<List<Book>>
    fun loadData(title: String): Flow<List<Book>>
}