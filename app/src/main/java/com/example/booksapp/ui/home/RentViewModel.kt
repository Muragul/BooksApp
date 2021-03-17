package com.example.booksapp.ui.home

import androidx.lifecycle.*
import com.example.booksapp.data.model.Rent
import com.example.booksapp.domain.home.GetRentsUseCase

class RentViewModel(private val rentsUseCase: GetRentsUseCase) : ViewModel() {
    fun getFullRentList(): LiveData<List<Rent>> {
        return rentsUseCase.getRentList().asLiveData()
    }

    fun getNewAdded(userId: String): LiveData<List<Rent>> {
        return rentsUseCase.getReadingList(userId).asLiveData()
    }
}
