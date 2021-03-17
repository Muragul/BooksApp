package com.example.booksapp.domain.scan

import com.example.booksapp.data.model.Book
import com.example.booksapp.data.model.Rent
import com.google.gson.JsonObject
import kotlinx.coroutines.flow.Flow

interface BookDetailRepositoryInterface {
    fun loadBookById(id: Int): Flow<Book>
    fun createRent(body: JsonObject): Flow<Rent>
}