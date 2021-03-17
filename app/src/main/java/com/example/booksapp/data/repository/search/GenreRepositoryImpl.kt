package com.example.booksapp.data.repository.search

import com.example.booksapp.data.api.ApiService
import com.example.booksapp.data.db.AppDao
import com.example.booksapp.data.model.Genre
import com.example.booksapp.domain.search.GenresRepositoryInterface
import kotlinx.coroutines.flow.Flow

class GenreRepositoryImpl(apiService: ApiService, appDao: AppDao) : GenresRepositoryInterface,
    GenresDataStore(apiService, appDao) {
    override fun loadData(): Flow<List<Genre>> {
        return fetchData {
            service.getGenresAsync()
        }
    }
}