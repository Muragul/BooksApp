package com.example.booksapp.ui.search

import androidx.lifecycle.*
import com.example.booksapp.data.db.*
import com.example.booksapp.data.model.Book
import com.example.booksapp.data.model.Genre
import com.example.booksapp.domain.search.GetBooksUseCase
import com.example.booksapp.domain.search.GetGenresUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.collect
import java.lang.Exception

class SharedViewModel(
    private val booksUseCase: GetBooksUseCase,
    private val genresUseCase: GetGenresUseCase,
    private val appDao: AppDao
) : ViewModel() {

    var booksLD = MutableLiveData<List<Book>>()
    var genresLD = MutableLiveData<List<Genre>>()
    var selectedLD = MutableLiveData<ArrayList<Int>>()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                booksUseCase.getBooks().collect {
                    appDao.insertBookList(it)
                }
                genresUseCase.getGenres().collect {
                    appDao.insertGenresList(it)
                }
            } catch (e: Exception) { }
        }
    }

    fun getBooksList() {
        booksLD.postValue(booksUseCase.getBooksFromDb())
    }

    fun getFilteredBooks(genres: ArrayList<Int>) {
        booksLD.postValue(booksUseCase.getFilteredBooksList(genres))
    }

    fun getBooksByTitle(title: String) {
        booksLD.postValue(booksUseCase.getBooksByTitle(title))
    }

    fun getGenres() {
        genresLD.postValue(genresUseCase.getGenresFromDb())
    }

}