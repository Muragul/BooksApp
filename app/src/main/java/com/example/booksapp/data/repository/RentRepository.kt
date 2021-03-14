package com.example.booksapp.data.repository

import com.example.booksapp.data.api.ApiService
import com.example.booksapp.data.model.Book
import com.example.booksapp.data.model.Rent
import com.example.booksapp.domain.RentListRepository
import kotlinx.coroutines.flow.Flow

class RentRepository(apiService: ApiService): RentListRepository, RentDataStore(apiService) {
    override fun loadRentData(): Flow<List<Rent>> {
        return fetchData {
            service.getRentListAsync()
        }
    }

    override fun loadReadingData(userId: String): Flow<List<Rent>> {
        return fetchData {
            service.getRentByUserIdAsync(userId)
        }
    }
}