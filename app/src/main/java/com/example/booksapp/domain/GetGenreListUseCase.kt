package com.example.booksapp.domain

import com.example.booksapp.data.db.Genre
import kotlinx.coroutines.flow.Flow

class GetGenreListUseCase(private val genreListRepository: GenreListRepository) {
    fun getGenres(): Flow<List<Genre>> {
        return genreListRepository.loadData()
    }
}