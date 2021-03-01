package com.example.booksapp.data.repository

import android.content.Context
import com.example.booksapp.domain.BookListRepository
import com.example.booksapp.data.api.ApiService
import com.example.booksapp.data.db.Book
import kotlinx.coroutines.flow.Flow

class BookRepository(apiService: ApiService, context: Context) : BookListRepository,
    BaseDataStore(apiService, context) {
    override fun loadData(): Flow<List<Book>> {
        return fetchData {
            service.getBooksListAsync()
        }
    }
}