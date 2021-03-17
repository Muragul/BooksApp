package com.example.booksapp.ui.search

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import com.example.booksapp.R
import com.example.booksapp.data.model.Book
import com.example.booksapp.ui.adapter.BooksAdapter
import com.example.booksapp.ui.scan.DetailActivity
import kotlinx.android.synthetic.main.fragment_search.*
import kotlinx.android.synthetic.main.fragment_search.swipe_refresh_layout
import org.koin.android.viewmodel.ext.android.sharedViewModel

class SearchFragment : Fragment(R.layout.fragment_search) {
    private val sharedViewModel by sharedViewModel<SharedViewModel>()
    private var genres = ArrayList<Int>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        swipe_refresh_layout.setOnRefreshListener {
            getListUsingGenres(genres)
            swipe_refresh_layout.isRefreshing = false
        }
        initSearch()
        initFilter()
        initViews()
        observeVm()
    }

    private fun initSearch() {
        search_view.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                if (!p0.isNullOrBlank())
                    sharedViewModel.getBooksByTitle(p0)
                else
                    getListUsingGenres(genres)
                return true
            }
        })
    }

    private fun initFilter() {
        filter_button.setOnClickListener {
            showDialog()
        }
    }

    private fun showDialog() {
        val bottomSheetFragment = BottomSheetFragment({
            getListUsingGenres(genres)
        }, genres)
        bottomSheetFragment.show(activity?.supportFragmentManager!!, bottomSheetFragment.tag)
    }

    private fun initViews() {
        recycler_view.adapter = BooksAdapter { id: Int -> onBookClicked(id) }
        getListUsingGenres(genres)
    }

    private fun observeVm() {
        observeLD(sharedViewModel.booksLD)
        sharedViewModel.selectedLD.observe(viewLifecycleOwner) {
            genres = it
        }
    }

    private fun observeLD(data: LiveData<List<Book>>) {
        data.observe(viewLifecycleOwner) {
            (recycler_view.adapter as BooksAdapter).submitList(it)
        }
    }

    private fun getListUsingGenres(genres: ArrayList<Int>) {
        if (genres.isNullOrEmpty())
            sharedViewModel.getBooksList()
        else
            sharedViewModel.getFilteredBooks(genres)
    }

    private fun onBookClicked(id: Int) {
        val intent = Intent(context, DetailActivity::class.java)
        intent.putExtra("book_id", id)
        startActivity(intent)
    }

}