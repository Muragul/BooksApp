package com.example.booksapp.ui.search

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.SearchView
import androidx.lifecycle.LiveData
import com.example.booksapp.data.model.Book
import com.example.booksapp.databinding.FragmentSearchBinding
import com.example.booksapp.ui.adapter.BooksAdapter
import com.example.booksapp.ui.scan.DetailActivity
import com.example.booksapp.utils.BindingFragment
import org.koin.android.viewmodel.ext.android.sharedViewModel

class SearchFragment : BindingFragment<FragmentSearchBinding>(FragmentSearchBinding::inflate) {
    private val sharedViewModel by sharedViewModel<SharedViewModel>()
    private var genres = ArrayList<Int>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.run {
            swipeRefreshLayout.setOnRefreshListener {
                getListUsingGenres(genres)
                swipeRefreshLayout.isRefreshing = false
            }
            initSearch()
            initFilter()
            initViews()
            observeVm()
        }
    }

    private fun initSearch() {
        binding.run {
            searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
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
    }

    private fun initFilter() {
        binding.run {
            filterButton.setOnClickListener { showDialog() }
        }
    }

    private fun showDialog() {
        val bottomSheetFragment = BottomSheetFragment({
            getListUsingGenres(genres)
        }, genres)
        bottomSheetFragment.show(activity?.supportFragmentManager!!, bottomSheetFragment.tag)
    }

    private fun initViews() {
        binding.run {
            recyclerView.adapter = BooksAdapter { id: Int -> onBookClicked(id) }
            getListUsingGenres(genres)
        }
    }

    private fun observeVm() {
        observeLD(sharedViewModel.booksLD)
        sharedViewModel.selectedLD.observe(viewLifecycleOwner) {
            genres = it
        }
    }

    private fun observeLD(data: LiveData<List<Book>>) {
        binding.run {
            data.observe(viewLifecycleOwner) {
                (recyclerView.adapter as BooksAdapter).submitList(it)
            }
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