package com.example.booksapp.data.repository

import android.content.Context
import com.example.booksapp.data.api.ApiService
import com.example.booksapp.data.db.Genre
import com.example.booksapp.data.db.GenreDao
import com.example.booksapp.data.db.GenreDatabase
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.collect
import retrofit2.Response

abstract class BaseGenreDataStore(
    @PublishedApi internal val service: ApiService,
    var context: Context
) {
    abstract fun loadData(): Flow<List<Genre>>

    var genreDao: GenreDao = GenreDatabase.getDatabase(context).genreDao()

    inline fun fetchData(crossinline call: (ApiService) -> Deferred<Response<List<Genre>>>): Flow<List<Genre>> =
        flow {
            try {
                val request = call(service)
                val response = request.await()
                if (response.isSuccessful) {
                    val result = response.body()!!
                    genreDao.insertList(result)
                    emit(result)
                } else {
                    genreDao.getGenreList().collect { value: List<Genre> -> emit(value) }
                }
            } catch (e: Exception) {
                genreDao.getGenreList().collect { value: List<Genre> -> emit(value) }
            }
        }.flowOn(Dispatchers.IO)
}