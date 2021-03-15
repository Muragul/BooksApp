package com.example.booksapp.domain.search

import com.example.booksapp.data.model.Book
import kotlinx.coroutines.flow.Flow

interface BookListRepository {
    fun loadData(): Flow<List<Book>>
}