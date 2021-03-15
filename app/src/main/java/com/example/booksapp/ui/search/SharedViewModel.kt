package com.example.booksapp.ui.search

import androidx.lifecycle.*
import com.example.booksapp.data.db.*
import com.example.booksapp.data.model.Book
import com.example.booksapp.data.model.Genre
import com.example.booksapp.domain.search.GetBookListUseCase
import com.example.booksapp.domain.search.GetGenreListUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.collect
import java.lang.Exception

class SharedViewModel(
    private val bookListUseCase: GetBookListUseCase,
    private val genreListUseCase: GetGenreListUseCase,
    private val appDao: AppDao
) : ViewModel() {

    var booksLD = MutableLiveData<List<Book>>()
    var genresLD = MutableLiveData<List<Genre>>()
    var selectedLD = MutableLiveData<ArrayList<Int>>()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                bookListUseCase.getBooks().collect {
                    appDao.insertBookList(it)
                }
                genreListUseCase.getGenres().collect {
                    appDao.insertGenresList(it)
                }
            } catch (e: Exception) { }
        }
    }

    fun getBooksList() {
        booksLD.postValue(bookListUseCase.getBooksFromDb())
    }

    fun getFilteredBooks(genres: ArrayList<Int>) {
        booksLD.postValue(bookListUseCase.getFilteredBooksList(genres))
    }

    fun getBooksByTitle(title: String) {
        booksLD.postValue(bookListUseCase.getBooksByTitle(title))
    }

    fun getGenres() {
        genresLD.postValue(genreListUseCase.getGenresFromDb())
    }

}