package com.example.booksapp.viewmodel

import androidx.lifecycle.*
import com.example.booksapp.data.db.Book
import com.example.booksapp.data.db.Genre
import com.example.booksapp.domain.GetBookListUseCase

class BookViewModel(private val repoUseCase: GetBookListUseCase) : ViewModel() {
    fun getBooks(): LiveData<List<Book>> {
        return repoUseCase.getBooks().asLiveData()
    }
}
