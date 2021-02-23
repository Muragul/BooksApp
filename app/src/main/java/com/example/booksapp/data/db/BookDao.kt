package com.example.booksapp.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface BookDao {

    @Query("SELECT * FROM book_table")
    fun getBookList(): Flow<List<Book>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(book: Book)

}