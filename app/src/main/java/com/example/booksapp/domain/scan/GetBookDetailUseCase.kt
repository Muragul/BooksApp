package com.example.booksapp.domain.scan

import com.example.booksapp.data.model.Book
import com.example.booksapp.data.model.Rent
import com.google.gson.JsonObject
import kotlinx.coroutines.flow.Flow

class GetBookDetailUseCase(private val bookDetailRepositoryInterface: BookDetailRepositoryInterface) {
    fun getBookById(id: Int): Flow<Book> = bookDetailRepositoryInterface.loadBookById(id)
    fun createRent(body: JsonObject): Flow<Rent> = bookDetailRepositoryInterface.createRent(body)
}