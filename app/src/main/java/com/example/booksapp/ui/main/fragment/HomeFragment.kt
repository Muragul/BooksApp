package com.example.booksapp.ui.main.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.booksapp.R
import com.example.booksapp.ui.main.adapter.BookListAdapter
import com.example.booksapp.viewmodel.BookViewModel
import kotlinx.android.synthetic.main.fragment_home.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment(R.layout.fragment_home) {
    private val viewModel: BookViewModel by viewModel()

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
        viewModel.getBooks()
    }

    private fun observeVm() {
        viewModel.getBooks().observe(viewLifecycleOwner) {
            (recyclerView.adapter as BookListAdapter).submitList(it)
        }
    }
}