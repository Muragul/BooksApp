package com.example.booksapp.ui.main.fragment

import android.os.Bundle
import android.view.View
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import com.example.booksapp.R
import com.example.booksapp.ui.main.adapter.BookListAdapter
import com.example.booksapp.viewmodel.BookViewModel
import kotlinx.android.synthetic.main.fragment_search.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.lang.Exception

class SearchFragment : Fragment(R.layout.fragment_search) {
    private val viewModel: BookViewModel by viewModel()
    private val args: SearchFragmentArgs by navArgs()

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
                search_view.clearFocus()
                observeLD(viewModel.loadData(p0!!))
                return false
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                return true
            }
        })
    }

    private fun initFilter() {
        filter_button.setOnClickListener {
            val action = SearchFragmentDirections.actionToFilter()
            findNavController().navigate(action)
        }
    }

    private fun initViews() {
        recycler_view.layoutManager = GridLayoutManager(context, 2)
        recycler_view.adapter = BookListAdapter()
    }

    private fun observeVm() {
        try {
            observeLD(viewModel.loadData(args.genres))
        } catch (e: Exception) {
            observeLD(viewModel.getBooks())
        }
    }

    private fun observeLD(data: LiveData<List<com.example.booksapp.data.db.Book>>) {
        data.observe(viewLifecycleOwner) {
            (recycler_view.adapter as BookListAdapter).submitList(it)
        }
    }

}