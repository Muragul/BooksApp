package com.example.booksapp.data.repository.home

import com.example.booksapp.data.api.ApiService
import com.example.booksapp.data.model.Rent
import com.example.booksapp.domain.home.RentsRepositoryInterface
import kotlinx.coroutines.flow.Flow

class RentsRepositoryImpl(apiService: ApiService): RentsRepositoryInterface, RentDataStore(apiService) {
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