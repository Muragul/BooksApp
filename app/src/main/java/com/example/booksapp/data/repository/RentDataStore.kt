package com.example.booksapp.data.repository

import com.example.booksapp.data.api.ApiService
import com.example.booksapp.data.model.Book
import com.example.booksapp.data.model.Rent
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Response

abstract class RentDataStore(@PublishedApi internal val service: ApiService) {
    abstract fun loadRentData(): Flow<List<Rent>>
    abstract fun loadReadingData(userId: String): Flow<List<Rent>>

    inline fun fetchData(crossinline call: (ApiService) -> Deferred<Response<List<Rent>>>): Flow<List<Rent>> =
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