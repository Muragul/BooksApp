package com.example.booksapp.domain.search

import com.example.booksapp.data.db.AppDao
import com.example.booksapp.data.model.Genre
import kotlinx.coroutines.flow.Flow

class GetGenresUseCase(
    private val genresRepositoryInterface: GenresRepositoryInterface,
    private val appDao: AppDao
) {
    fun getGenres(): Flow<List<Genre>> = genresRepositoryInterface.loadData()

    fun getGenresFromDb(): List<Genre> = appDao.getGenreList()
}