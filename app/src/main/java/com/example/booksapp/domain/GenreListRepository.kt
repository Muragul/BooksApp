package com.example.booksapp.domain

import com.example.booksapp.data.model.Genre
import kotlinx.coroutines.flow.Flow

interface GenreListRepository {
    fun loadData(): Flow<List<Genre>>
}