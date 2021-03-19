package com.example.booksapp.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.booksapp.data.model.Book
import com.example.booksapp.data.model.Genre
import kotlinx.coroutines.flow.Flow

@Dao
interface AppDao {

    @Query("SELECT * FROM books_table")
    fun getBookList(): List<Book>

    @Query("SELECT * FROM books_table WHERE genre_id = :genreId")
    fun getBooksByGenre(genreId: Int): List<Book>

    @Query("SELECT * FROM books_table WHERE title LIKE :title")
    fun getBooksByTitle(title: String): List<Book>

    @Query("SELECT * FROM books_table WHERE isbn = :isbn")
    fun getBooksByISBN(isbn: String): Flow<Book>

    @Query("SELECT * FROM books_table where genre_id IN (:genres)")
    fun getFilteredBooks(genres: List<Int>): List<Book>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertBookList(books: List<Book>)

    //genres
    @Query("SELECT * FROM genres_table")
    fun getGenreList(): List<Genre>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertGenresList(genres: List<Genre>)

}