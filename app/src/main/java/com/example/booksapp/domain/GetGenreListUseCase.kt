package com.example.booksapp.domain

import com.example.booksapp.data.db.AppDao
import com.example.booksapp.data.model.Genre
import kotlinx.coroutines.flow.Flow

class GetGenreListUseCase(
    private val genreListRepository: GenreListRepository,
    private val appDao: AppDao
) {
    fun getGenres(): Flow<List<Genre>> = genreListRepository.loadData()

    fun getGenresFromDb(): List<Genre> = appDao.getGenreList()
}