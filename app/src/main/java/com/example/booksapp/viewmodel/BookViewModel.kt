package com.example.booksapp.viewmodel

import androidx.lifecycle.*
import com.example.booksapp.data.db.Book
import com.example.booksapp.domain.GetBookListUseCase

class BookViewModel(private val repoUseCase: GetBookListUseCase) : ViewModel() {
    fun getBooks(): LiveData<List<Book>> {
        return repoUseCase.getBooks().asLiveData()
    }

    fun loadData(genreId: Int): LiveData<List<Book>> {
        return repoUseCase.loadData(genreId).asLiveData()
    }

    fun loadData(title: String): LiveData<List<Book>> {
        return repoUseCase.loadData(title).asLiveData()
    }
}
