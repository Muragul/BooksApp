package com.example.booksapp.domain

import com.example.booksapp.data.db.AppDao
import com.example.booksapp.data.model.Book
import kotlinx.coroutines.flow.Flow

class GetBookListUseCase(
    private val bookListRepository: BookListRepository,
    private val appDao: AppDao
) {
    fun getBooks(): Flow<List<Book>> = bookListRepository.loadData()

    fun getBooksFromDb(): List<Book> = appDao.getBookList()

    fun getFilteredBooksList(genres: List<Int>): List<Book> = appDao.getFilteredBooks(genres)

    fun getBooksByTitle(title: String): List<Book> = appDao.getBooksByTitle(title)
}