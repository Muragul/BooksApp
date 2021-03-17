package com.example.booksapp.domain.home

import com.example.booksapp.data.model.Rent
import kotlinx.coroutines.flow.Flow

class GetRentsUseCase(private val rentsRepositoryInterface: RentsRepositoryInterface) {
    fun getRentList(): Flow<List<Rent>> = rentsRepositoryInterface.loadRentData()
    fun getReadingList(userId: String): Flow<List<Rent>> = rentsRepositoryInterface.loadReadingData(userId)
}