package com.example.booksapp.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.navigation.fragment.findNavController
import com.example.booksapp.R
import com.example.booksapp.ui.adapter.RentListAdapter
import kotlinx.android.synthetic.main.fragment_home.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment(R.layout.fragment_home) {
    private val viewModel: RentViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setListeners()
        initViews()
        observeVm()
    }

    private fun setListeners() {
        swipe_refresh_layout.setOnRefreshListener {
            observeVm()
            swipe_refresh_layout.isRefreshing = false
        }
        seeAllReading.setOnClickListener {
            val action = HomeFragmentDirections.actionToReading(1)
            findNavController().navigate(action)
        }
        seeAllAdded.setOnClickListener {
            val action = HomeFragmentDirections.actionToReading(2)
            findNavController().navigate(action)
        }
    }

    private fun initViews() {
        recyclerViewReading.adapter = RentListAdapter()
        recyclerViewAdded.adapter = RentListAdapter()
    }

    private fun observeVm() {
        viewModel.getFullRentList().observe(viewLifecycleOwner) {
            if (it.size > 5)
                (recyclerViewReading.adapter as RentListAdapter).submitList(it.subList(0, 5))
            else
                (recyclerViewReading.adapter as RentListAdapter).submitList(it)
        }
        viewModel.getNewAdded("2b68bd4a-9346-4621-8e05-4acd795a274c").observe(viewLifecycleOwner) {
            if (it.size > 5)
                (recyclerViewAdded.adapter as RentListAdapter).submitList(
                    it.subList(0, 5).sortedByDescending { item -> item.updatedAt })
            else
                (recyclerViewAdded.adapter as RentListAdapter).submitList(it.sortedByDescending { item -> item.updatedAt })
        }
    }

}