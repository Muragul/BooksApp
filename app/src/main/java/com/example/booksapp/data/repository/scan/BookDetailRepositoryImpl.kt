package com.example.booksapp.data.repository.scan

import com.example.booksapp.data.api.ApiService
import com.example.booksapp.data.model.Book
import com.example.booksapp.data.model.Rent
import com.example.booksapp.domain.scan.BookDetailRepositoryInterface
import com.google.gson.JsonObject
import kotlinx.coroutines.flow.Flow

class BookDetailRepositoryImpl(apiService: ApiService) : BookDetailRepositoryInterface,
    DetailDataStore(apiService) {
    override fun loadBookById(id: Int): Flow<Book> {
        return fetchData {
            service.getBookByIdAsync(id)
        }
    }

    override fun createRent(body: JsonObject): Flow<Rent> {
        return postData {
            service.createRentAsync(body)
        }
    }
}