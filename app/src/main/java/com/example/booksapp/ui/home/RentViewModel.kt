package com.example.booksapp.ui.home

import androidx.lifecycle.*
import com.example.booksapp.data.model.Rent
import com.example.booksapp.domain.home.GetRentListUseCase

class RentViewModel(private val rentListUseCase: GetRentListUseCase) : ViewModel() {
    fun getFullRentList(): LiveData<List<Rent>> {
        return rentListUseCase.getRentList().asLiveData()
    }

    fun getNewAdded(userId: String): LiveData<List<Rent>> {
        return rentListUseCase.getReadingList(userId).asLiveData()
    }
}
