package com.example.booksapp.domain.search

import com.example.booksapp.data.model.Genre
import kotlinx.coroutines.flow.Flow

interface GenresRepositoryInterface {
    fun loadData(): Flow<List<Genre>>
}