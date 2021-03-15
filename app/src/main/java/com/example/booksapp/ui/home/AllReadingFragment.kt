package com.example.booksapp.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.lifecycle.LiveData
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.booksapp.R
import com.example.booksapp.data.model.Rent
import com.example.booksapp.ui.adapter.RentListAdapter
import kotlinx.android.synthetic.main.fragment_all_reading.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class AllReadingFragment : Fragment(R.layout.fragment_all_reading) {
    private val viewModel: RentViewModel by viewModel()
    private val args: AllReadingFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        when (args.mode) {
            1 -> toolbar.text = getString(R.string.now_reading_title)
            2 -> toolbar.text = getString(R.string.newly_added_title)
        }
        initViews()
        observeVm()
    }

    private fun initViews() {
        recycler_view.adapter = RentListAdapter()
        back.setOnClickListener {
            val action = AllReadingFragmentDirections.actionBackToHome()
            findNavController().navigate(action)
        }
        swipe_refresh_layout.setOnRefreshListener {
            observeVm()
            swipe_refresh_layout.isRefreshing = false
        }
    }

    private fun observeVm() {
        when (args.mode) {
            1 -> observeLd(viewModel.getFullRentList())
            2 -> observeLd(viewModel.getNewAdded("2b68bd4a-9346-4621-8e05-4acd795a274c"))
        }
    }

    private fun observeLd(liveData: LiveData<List<Rent>>) {
        liveData.observe(viewLifecycleOwner) {
            (recycler_view.adapter as RentListAdapter).submitList(it)
        }
    }
}