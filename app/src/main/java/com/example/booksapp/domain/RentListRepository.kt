package com.example.booksapp.domain

import com.example.booksapp.data.model.Rent
import kotlinx.coroutines.flow.Flow

interface RentListRepository {
    fun loadRentData(): Flow<List<Rent>>
    fun loadReadingData(userId: String): Flow<List<Rent>>
}