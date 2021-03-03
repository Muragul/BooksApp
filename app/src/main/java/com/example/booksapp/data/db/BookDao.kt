package com.example.booksapp.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface BookDao {

    @Query("SELECT * FROM books_table")
    fun getBookList(): Flow<List<Book>>

    @Query("SELECT * FROM books_table WHERE genre_id = :genreId")
    fun getBooksByGenre(genreId: Int): Flow<List<Book>>

    @Query("SELECT * FROM books_table WHERE title = :title")
    fun getBooksByTitle(title: String): Flow<List<Book>>

    @Query("SELECT * FROM books_table WHERE isbn = :isbn")
    fun getBooksByISBN(isbn: String): Flow<List<Book>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(book: Book)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertList(books: List<Book>)

}