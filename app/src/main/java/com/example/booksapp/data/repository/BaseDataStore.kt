package com.example.booksapp.data.repository

import android.content.Context
import com.example.booksapp.data.api.ApiService
import com.example.booksapp.data.db.BookDao
import com.example.booksapp.data.db.Book
import com.example.booksapp.data.db.BookRoomDatabase
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Response

abstract class BaseDataStore(@PublishedApi internal val service: ApiService, var context: Context) {
    abstract fun loadData(): Flow<List<Book>>
    abstract fun loadData(genreId: Int): Flow<List<Book>>
    abstract fun loadData(title: String): Flow<List<Book>>

    var bookDao: BookDao = BookRoomDatabase.getDatabase(context).bookDao()

    inline fun fetchData(crossinline call: (ApiService) -> Deferred<Response<List<Book>>>): Flow<List<Book>> =
        flow {
            try {
                val request = call(service)
                val response = request.await()
                if (response.isSuccessful) {
                    val result = response.body()!!
                    bookDao.insertList(result)
                    emit(result)
                } else {
                    bookDao.getBookList().collect { value: List<Book> -> emit(value) }
                }
            } catch (e: Exception) {
                bookDao.getBookList().collect { value: List<Book> -> emit(value) }
            }
        }.flowOn(Dispatchers.IO)

    fun fetchDataByGenre(genreId: Int): Flow<List<Book>> =
        flow {
            bookDao.getBooksByGenre(genreId).collect { value: List<Book> -> emit(value) }
        }.flowOn(Dispatchers.IO)

    fun fetchDataByTitle(title: String): Flow<List<Book>> =
        flow {
            bookDao.getBooksByTitle(title).collect { value: List<Book> -> emit(value) }
        }.flowOn(Dispatchers.IO)

}
