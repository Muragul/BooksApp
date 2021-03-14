package com.example.booksapp.domain

import com.example.booksapp.data.model.Rent
import kotlinx.coroutines.flow.Flow

class GetRentListUseCase(private val rentListRepository: RentListRepository) {
    fun getRentList(): Flow<List<Rent>> = rentListRepository.loadRentData()
    fun getReadingList(userId: String): Flow<List<Rent>> = rentListRepository.loadReadingData(userId)
}