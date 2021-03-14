package com.example.booksapp.data.api

import com.example.booksapp.data.model.Book
import com.example.booksapp.data.model.Genre
import com.example.booksapp.data.model.Rent
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("books")
    fun getBooksListAsync(): Deferred<Response<List<Book>>>

    @GET("genres")
    fun getGenresAsync(): Deferred<Response<List<Genre>>>

    @GET("rent")
    fun getRentListAsync(): Deferred<Response<List<Rent>>>

    @GET("rent/reading/{user_id}")
    fun getRentByUserIdAsync(@Path("user_id") user_id: String): Deferred<Response<List<Rent>>>
}