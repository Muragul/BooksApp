package com.example.booksapp.domain.search

import com.example.booksapp.data.model.Book
import kotlinx.coroutines.flow.Flow

interface BooksRepositoryInterface {
    fun loadData(): Flow<List<Book>>
}