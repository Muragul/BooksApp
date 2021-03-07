package com.example.booksapp.ui.main.fragment

import android.os.Bundle
import android.view.View
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.GridLayoutManager
import com.example.booksapp.R
import com.example.booksapp.data.db.Book
import com.example.booksapp.ui.main.adapter.BookListAdapter
import com.example.booksapp.viewmodel.SharedViewModel
import kotlinx.android.synthetic.main.fragment_search.*
import org.koin.android.viewmodel.ext.android.sharedViewModel

class SearchFragment : Fragment(R.layout.fragment_search) {
    private val sharedViewModel by sharedViewModel<SharedViewModel>()
    private var genres = ArrayList<Int>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
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
                sharedViewModel.getBooksByTitle(p0!!)
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
        val bottomSheetFragment = BottomSheetFragment({ genres ->
            if (genres.isNullOrEmpty())
                sharedViewModel.getBooksList()
            else
                sharedViewModel.getFilteredBooks(genres)
        }, genres)
        bottomSheetFragment.show(activity?.supportFragmentManager!!, bottomSheetFragment.tag)
    }

    private fun initViews() {
        recycler_view.layoutManager = GridLayoutManager(context, 2)
        recycler_view.adapter = BookListAdapter()
        if (genres.isNullOrEmpty())
            sharedViewModel.getBooksList()
        else
            sharedViewModel.getFilteredBooks(genres)
    }

    private fun observeVm() {
        observeLD(sharedViewModel.booksLD)
        sharedViewModel.selectedLD.observe(viewLifecycleOwner) {
            genres = it
        }
    }

    private fun observeLD(data: LiveData<List<Book>>) {
        data.observe(viewLifecycleOwner) {
            (recycler_view.adapter as BookListAdapter).submitList(it)
        }
    }

}