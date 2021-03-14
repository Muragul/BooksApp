package com.example.booksapp.data.repository

import com.example.booksapp.data.api.ApiService
import com.example.booksapp.data.db.AppDao
import com.example.booksapp.data.model.Book
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Response
import java.lang.Exception

abstract class BooksDataStore(@PublishedApi internal val service: ApiService, val appDao: AppDao) {
    abstract fun loadData(): Flow<List<Book>>

    inline fun fetchData(crossinline call: (ApiService) -> Deferred<Response<List<Book>>>): Flow<List<Book>> =
        flow {
            try {
                val request = call(service)
                val response = request.await()
                if (response.isSuccessful) {
                    val result = response.body()!!
                    appDao.insertBookList(result)
                    emit(result)
                } else
                    appDao.getBookList()
            } catch (e: Exception) {
                appDao.getBookList()
            }
        }.flowOn(Dispatchers.IO)
}