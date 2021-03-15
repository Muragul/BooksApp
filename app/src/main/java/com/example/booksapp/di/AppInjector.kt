package com.example.booksapp.di

import android.app.Application
import androidx.room.Room
import com.example.booksapp.domain.search.GetBookListUseCase
import com.example.booksapp.data.api.ApiClient.getApiService
import com.example.booksapp.data.db.AppDao
import com.example.booksapp.data.db.AppDatabase
import com.example.booksapp.data.repository.search.BookRepository
import com.example.booksapp.data.repository.search.GenreRepository
import com.example.booksapp.data.repository.home.RentRepository
import com.example.booksapp.domain.search.GetGenreListUseCase
import com.example.booksapp.domain.home.GetRentListUseCase
import com.example.booksapp.ui.home.RentViewModel
import com.example.booksapp.ui.search.SharedViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val networkModule = module {
    single { getApiService() }
}

val viewModelModule = module {
    viewModel { SharedViewModel(get(), get(), get()) }
    viewModel { RentViewModel(get()) }
}

val repositoryModule = module {
    single { BookRepository(get(), get()) }
    single { GenreRepository(get(), get()) }
    single { RentRepository(get()) }
}

val useCaseModule = module {
    single { GetBookListUseCase(get<BookRepository>(), get()) }
    single { GetGenreListUseCase(get<GenreRepository>(), get()) }
    single { GetRentListUseCase(get<RentRepository>()) }
}

val dataBaseModule = module {
    fun getDatabase(application: Application): AppDatabase {
        return Room.databaseBuilder(
            application,
            AppDatabase::class.java,
            "app_database"
        ).allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .build()

    }

    fun getAppDao(database: AppDatabase): AppDao {
        return database.appDao()
    }

    single { getDatabase(androidApplication()) }
    single { getAppDao(get()) }
}