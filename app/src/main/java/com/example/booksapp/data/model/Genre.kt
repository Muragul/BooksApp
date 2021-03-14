package com.example.booksapp.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "genres_table")
data class Genre(
    @PrimaryKey
    @ColumnInfo(name = "id") val id: Int,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "sort") val sort: Int,
    @ColumnInfo(name = "enabled") val enabled: Boolean,
    @ColumnInfo(name = "createdAt") val createdAt: String,
    @ColumnInfo(name = "updatedAt") val updatedAt: String
)