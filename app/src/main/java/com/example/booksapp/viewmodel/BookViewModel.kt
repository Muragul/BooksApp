package com.example.booksapp.viewmodel

import androidx.lifecycle.*
import com.example.booksapp.domain.GetBookListUseCase
import com.example.booksapp.data.db.Book

class BookViewModel(private val repoUseCase: GetBookListUseCase) : ViewModel() {
    fun getBooks(): LiveData<List<Book>> {
        return repoUseCase.getBooks().asLiveData()
    }
}
