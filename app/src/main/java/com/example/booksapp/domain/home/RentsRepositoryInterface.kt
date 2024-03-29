package com.example.booksapp.domain.home

import com.example.booksapp.data.model.Rent
import kotlinx.coroutines.flow.Flow

interface RentsRepositoryInterface {
    fun loadRentData(): Flow<List<Rent>>
    fun loadReadingData(userId: String): Flow<List<Rent>>
}