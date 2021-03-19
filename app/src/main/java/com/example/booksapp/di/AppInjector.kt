package com.example.booksapp.di

import android.app.Application
import androidx.room.Room
import com.example.booksapp.domain.search.GetBooksUseCase
import com.example.booksapp.data.api.ApiClient.getApiService
import com.example.booksapp.data.db.AppDao
import com.example.booksapp.data.db.AppDatabase
import com.example.booksapp.data.repository.search.BookRepositoryImpl
import com.example.booksapp.data.repository.search.GenreRepositoryImpl
import com.example.booksapp.data.repository.home.RentsRepositoryImpl
import com.example.booksapp.domain.search.GetGenresUseCase
import com.example.booksapp.domain.home.GetRentsUseCase
import com.example.booksapp.data.repository.scan.BookDetailRepositoryImpl
import com.example.booksapp.domain.scan.GetBookDetailUseCase
import com.example.booksapp.ui.home.RentViewModel
import com.example.booksapp.ui.scan.DetailViewModel
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
    viewModel { DetailViewModel(get()) }
}

val repositoryModule = module {
    single { BookRepositoryImpl(get(), get()) }
    single { GenreRepositoryImpl(get(), get()) }
    single { RentsRepositoryImpl(get()) }
    single { BookDetailRepositoryImpl(get()) }
}

val useCaseModule = module {
    single { GetBooksUseCase(get<BookRepositoryImpl>(), get()) }
    single { GetGenresUseCase(get<GenreRepositoryImpl>(), get()) }
    single { GetRentsUseCase(get<RentsRepositoryImpl>()) }
    single { GetBookDetailUseCase(get<BookDetailRepositoryImpl>(), get()) }
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