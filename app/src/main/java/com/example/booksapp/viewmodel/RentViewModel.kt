package com.example.booksapp.viewmodel

import androidx.lifecycle.*
import com.example.booksapp.data.model.Rent
import com.example.booksapp.domain.GetRentListUseCase

class RentViewModel(private val rentListUseCase: GetRentListUseCase) : ViewModel() {
    fun getRentList(): LiveData<List<Rent>> {
        return rentListUseCase.getRentList().asLiveData()
    }
    fun getReadingList(userId: String): LiveData<List<Rent>> {
        return rentListUseCase.getReadingList(userId).asLiveData()
    }
}
