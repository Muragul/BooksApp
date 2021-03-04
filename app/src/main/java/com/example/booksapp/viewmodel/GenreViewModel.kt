package com.example.booksapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.booksapp.data.db.Genre
import com.example.booksapp.domain.GetGenreListUseCase

class GenreViewModel(private val getGenreListUseCase: GetGenreListUseCase) : ViewModel() {
    fun getGenres(): LiveData<List<Genre>> {
        return getGenreListUseCase.getGenres().asLiveData()
    }
}