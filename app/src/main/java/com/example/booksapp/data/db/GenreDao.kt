package com.example.booksapp.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface GenreDao {

    @Query("SELECT * FROM genres_table")
    fun getGenreList(): Flow<List<Genre>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(genre: Genre)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertList(genres: List<Genre>)

}