package com.example.booksapp.data.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "books_table")
data class Book(
    @PrimaryKey
    @ColumnInfo(name = "id") val id: Int,
    @ColumnInfo(name = "isbn") val isbn: String,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "publish_date") val publish_date: String,
    @ColumnInfo(name = "createdAt") val createdAt: String,
    @ColumnInfo(name = "updatedAt") val updatedAt: String
)