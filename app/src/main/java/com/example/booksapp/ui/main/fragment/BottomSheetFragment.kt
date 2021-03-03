package com.example.booksapp.ui.main.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.booksapp.R
import com.example.booksapp.ui.main.adapter.GenreListAdapter
import com.example.booksapp.viewmodel.GenreViewModel
import kotlinx.android.synthetic.main.fragment_bottom_sheet.view.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class BottomSheetFragment : Fragment() {
    private val viewModel: GenreViewModel by viewModel()
    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_bottom_sheet, container, false)
        recyclerView = rootView.genres_recycler_view
        initViews()
        observeVm()
        return rootView
    }

    private fun initViews() {
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = GenreListAdapter { itemId: Int ->
            val action = BottomSheetFragmentDirections.actionBackToSearch(itemId)
            findNavController().navigate(action)
        }
    }

    private fun observeVm() {
        viewModel.getGenres().observe(viewLifecycleOwner) {
            (recyclerView.adapter as GenreListAdapter).submitList(it)
        }
    }
}