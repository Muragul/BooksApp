package com.example.booksapp.data

import com.example.booksapp.data.db.Book
import com.example.booksapp.data.db.BookDao
import kotlinx.coroutines.flow.Flow
import java.io.IOException
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class BookRepository(private val bookDao: BookDao) {

    val allBooks: Flow<List<Book>> = bookDao.getBookList()

    fun insert(book: Book) {
        runOnBackground {
            it.submit { bookDao.insert(book) }
        }
    }
}

private fun runOnBackground(submit: (ExecutorService) -> Unit) {
    val executor = Executors.newCachedThreadPool()
    try {
        submit.invoke(executor)
    } catch (e: IOException) {
        e.printStackTrace()
    } finally {
        executor.shutdown()
    }

}