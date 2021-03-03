package com.example.booksapp.data.repository

import android.content.Context
import com.example.booksapp.data.api.ApiService
import com.example.booksapp.data.db.Genre
import com.example.booksapp.domain.GenreListRepository
import kotlinx.coroutines.flow.Flow

class GenreRepository(apiService: ApiService, context: Context) : GenreListRepository,
    BaseGenreDataStore(apiService, context) {
    override fun loadData(): Flow<List<Genre>> {
        return fetchData {
            service.getGenresAsync()
        }
    }
}