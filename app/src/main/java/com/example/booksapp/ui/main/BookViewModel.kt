package com.example.booksapp.ui.main

import androidx.lifecycle.*
import com.example.booksapp.data.BookRepository
import com.example.booksapp.data.db.Book
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class BookViewModel(private val repo: BookRepository) : ViewModel() {

    var allBooks = MutableLiveData<List<Book>>()

    fun getBooks(): LiveData<List<Book>> {
        viewModelScope.launch(Dispatchers.Default) {
            repo.allBooks.collect {
                allBooks.postValue(it)
            }
        }
        return allBooks
    }

    fun insert(book: Book) {
        repo.insert(book)
    }

}

class BookViewModelFactory(private val repository: BookRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(BookViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return BookViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
