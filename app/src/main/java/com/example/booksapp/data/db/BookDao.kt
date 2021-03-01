package com.example.booksapp.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface BookDao {

    @Query("SELECT * FROM books_table")
    fun getBookList(): List<Book>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(book: Book)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertList(books: List<Book>)

}