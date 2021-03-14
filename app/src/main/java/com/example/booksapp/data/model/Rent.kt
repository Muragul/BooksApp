package com.example.booksapp.data.model

import com.google.gson.annotations.SerializedName

data class Rent(
    @SerializedName("id") val id: Int,
    @SerializedName("user_id") val user_id: String,
    @SerializedName("user_contact") val user_contact: String,
    @SerializedName("user_name") val user_name: String,
    @SerializedName("book_id") val book_id: Int,
    @SerializedName("start_date") val start_date: String,
    @SerializedName("end_date") val end_date: String,
    @SerializedName("createdAt") val createdAt: String,
    @SerializedName("updatedAt") val updatedAt: String,
    @SerializedName("book") val book: Book
)