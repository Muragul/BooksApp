package com.example.booksapp.domain.scan

import androidx.lifecycle.LiveData
import com.example.booksapp.data.db.AppDao
import com.example.booksapp.data.model.Book
import com.example.booksapp.data.model.Rent
import com.google.gson.JsonObject
import kotlinx.coroutines.flow.Flow

class GetBookDetailUseCase(
    private val bookDetailRepositoryInterface: BookDetailRepositoryInterface,
    private val appDao: AppDao
) {
    fun getBookById(id: Int): Flow<Book> = bookDetailRepositoryInterface.loadBookById(id)
    fun createRent(body: JsonObject): Flow<Rent> = bookDetailRepositoryInterface.createRent(body)
    fun getBookByIsbn(isbn: String): Flow<Book> = appDao.getBooksByISBN(isbn)
}