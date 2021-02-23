package com.example.booksapp.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.booksapp.App
import com.example.booksapp.R
import com.example.booksapp.data.db.Book
import com.example.booksapp.ui.main.adapter.BookListAdapter
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment(R.layout.fragment_home) {

    private lateinit var viewModel: BookViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        initVm()
        observeVm()
    }

    private fun initViews() {
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = BookListAdapter()
    }

    private fun initVm() {
        val app = requireActivity().application as App
        val repo = app.repository
        val factory = BookViewModelFactory(repo)
        viewModel = ViewModelProvider(this, factory).get(BookViewModel::class.java)
        for (i in 1..10) {
            viewModel.insert(Book(i, "Name$i", "URL$i"))
        }
    }

    private fun observeVm() {
        viewModel.getBooks().observe(viewLifecycleOwner) {
            (recyclerView.adapter as BookListAdapter).submitList(it)
        }
    }
}