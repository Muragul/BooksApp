package com.example.booksapp.data.repository.scan

import com.example.booksapp.data.api.ApiService
import com.example.booksapp.data.model.Book
import com.example.booksapp.data.model.Rent
import com.google.gson.JsonObject
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import retrofit2.Response
import retrofit2.http.Body

abstract class DetailDataStore(@PublishedApi internal val service: ApiService) {
    abstract fun loadBookById(id: Int): Flow<Book>
    abstract fun createRent(body: JsonObject): Flow<Rent>

    inline fun fetchData(crossinline call: (ApiService) -> Deferred<Response<Book>>): Flow<Book> =
        flow {
            try {
                val request = call(service)
                val response = request.await()
                if (response.isSuccessful) {
                    val result = response.body()!!
                    emit(result)
                }
            } catch (e: Exception) {
            }
        }.flowOn(Dispatchers.IO)

    inline fun postData(crossinline call: (ApiService) -> Deferred<Response<Rent>>): Flow<Rent> =
        flow {
            try {
                val request = call(service)
                val response = request.await()
                if (response.isSuccessful) {
                    val result = response.body()!!
                    emit(result)
                }
            } catch (e: Exception) {
            }
        }.flowOn(Dispatchers.IO)
}