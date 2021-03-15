package com.example.booksapp.data.repository.search

import com.example.booksapp.data.api.ApiService
import com.example.booksapp.data.db.AppDao
import com.example.booksapp.data.model.Genre
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Response
import java.lang.Exception

abstract class GenresDataStore(@PublishedApi internal val service: ApiService, val appDao: AppDao) {
    abstract fun loadData(): Flow<List<Genre>>

    inline fun fetchData(crossinline call: (ApiService) -> Deferred<Response<List<Genre>>>): Flow<List<Genre>> =
        flow {
            try {
                val request = call(service)
                val response = request.await()
                if (response.isSuccessful) {
                    val result = response.body()!!
                    appDao.insertGenresList(result)
                    emit(result)
                } else
                    appDao.getGenreList()
            } catch (e: Exception) {
                appDao.getGenreList()
            }
        }.flowOn(Dispatchers.IO)
}