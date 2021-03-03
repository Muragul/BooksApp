package com.example.booksapp.di

import com.example.booksapp.domain.GetBookListUseCase
import com.example.booksapp.data.api.ApiClient.getApiService
import com.example.booksapp.data.repository.BookRepository
import com.example.booksapp.data.repository.GenreRepository
import com.example.booksapp.domain.GetGenreListUseCase
import com.example.booksapp.viewmodel.BookViewModel
import com.example.booksapp.viewmodel.GenreViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val networkModule = module {
    single { getApiService() }
}

val viewModelModule = module {
    viewModel { BookViewModel(get()) }
    viewModel { GenreViewModel(get()) }
}

val repositoryModule = module {
    single { BookRepository(get(), androidApplication()) }
    single { GenreRepository(get(), androidApplication()) }
}

val useCaseModule = module {
    single { GetBookListUseCase(get<BookRepository>()) }
    single { GetGenreListUseCase(get<GenreRepository>()) }
}