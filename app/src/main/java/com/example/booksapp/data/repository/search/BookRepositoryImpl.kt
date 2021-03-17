package com.example.booksapp.data.repository.search

import com.example.booksapp.domain.search.BooksRepositoryInterface
import com.example.booksapp.data.api.ApiService
import com.example.booksapp.data.db.AppDao
import com.example.booksapp.data.model.Book
import kotlinx.coroutines.flow.Flow

class BookRepositoryImpl(apiService: ApiService, appDao: AppDao) : BooksRepositoryInterface,
    BooksDataStore(apiService, appDao) {
    override fun loadData(): Flow<List<Book>> {
        return fetchData {
            service.getBooksListAsync()
        }
    }
}