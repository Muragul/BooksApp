package com.example.booksapp.domain

import com.example.booksapp.data.db.Book
import kotlinx.coroutines.flow.Flow

class GetBookListUseCase(private val bookListRepository: BookListRepository) {

    fun getBooks(): Flow<List<Book>> {
        return bookListRepository.loadData()
    }

    fun loadData(genreId: Int): Flow<List<Book>> {
        return bookListRepository.loadData(genreId)
    }

    fun loadData(title: String): Flow<List<Book>> {
        return bookListRepository.loadData(title)
    }
}