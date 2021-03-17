package com.example.booksapp.domain.search

import com.example.booksapp.data.db.AppDao
import com.example.booksapp.data.model.Book
import kotlinx.coroutines.flow.Flow

class GetBooksUseCase(
    private val bookListRepository: BooksRepositoryInterface,
    private val appDao: AppDao
) {
    fun getBooks(): Flow<List<Book>> = bookListRepository.loadData()

    fun getBooksFromDb(): List<Book> = appDao.getBookList()

    fun getFilteredBooksList(genres: List<Int>): List<Book> = appDao.getFilteredBooks(genres)

    fun getBooksByTitle(title: String): List<Book> = appDao.getBooksByTitle("$title%")
}