package com.example.booksapp.ui.scan

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.booksapp.data.model.Book
import com.example.booksapp.data.model.Rent
import com.example.booksapp.domain.scan.GetBookDetailUseCase
import com.google.gson.JsonObject

class DetailViewModel(private val bookDetailUseCase: GetBookDetailUseCase) : ViewModel() {
    fun getBookById(id: Int): LiveData<Book> {
        return bookDetailUseCase.getBookById(id).asLiveData()
    }

    fun createRent(body: JsonObject): LiveData<Rent> {
        return bookDetailUseCase.createRent(body).asLiveData()
    }
}