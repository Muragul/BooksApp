package com.example.booksapp.data.repository

import com.example.booksapp.data.api.ApiService
import com.example.booksapp.data.db.AppDao
import com.example.booksapp.data.model.Genre
import com.example.booksapp.domain.GenreListRepository
import kotlinx.coroutines.flow.Flow

class GenreRepository(apiService: ApiService, appDao: AppDao) : GenreListRepository,
    GenresDataStore(apiService, appDao) {
    override fun loadData(): Flow<List<Genre>> {
        return fetchData {
            service.getGenresAsync()
        }
    }
}