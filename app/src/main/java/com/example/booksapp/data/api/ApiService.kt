package com.example.booksapp.data.api

import com.example.booksapp.data.db.Book
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET("books")
    fun getBooksListAsync(): Deferred<Response<List<Book>>>
}