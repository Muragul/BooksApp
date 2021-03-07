package com.example.booksapp.ui.main.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.booksapp.R
import com.example.booksapp.ui.main.adapter.GenreListAdapter
import com.example.booksapp.viewmodel.SharedViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.fragment_bottom_sheet.view.*
import org.koin.android.viewmodel.ext.android.sharedViewModel

class BottomSheetFragment(
    private val onFilter: (genresList: ArrayList<Int>) -> Unit,
    var genres: ArrayList<Int>
) : BottomSheetDialogFragment() {

    private lateinit var recyclerView: RecyclerView
    private val sharedViewModel by sharedViewModel<SharedViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_bottom_sheet, container, false)
        recyclerView = rootView.genres_recycler_view
        initViews()
        observeVm()
        rootView.apply_filter_button.setOnClickListener {
            if (!genres.isNullOrEmpty()) {
                sharedViewModel.selectedLD.postValue(genres)
            }
            onFilter.invoke(genres)
            dismiss()
        }
        return rootView
    }

    private fun initViews() {
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter =
            GenreListAdapter({ genresList -> genres = genresList }, genres)
        sharedViewModel.getGenres()
    }

    private fun observeVm() {
        sharedViewModel.genresLD.observe(viewLifecycleOwner) {
            (recyclerView.adapter as GenreListAdapter).submitList(it)
        }
    }

}