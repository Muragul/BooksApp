package com.example.booksapp.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.lifecycle.LiveData
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.booksapp.R
import com.example.booksapp.data.model.Rent
import com.example.booksapp.databinding.FragmentReadingBinding
import com.example.booksapp.ui.adapter.RentsAdapter
import com.example.booksapp.ui.scan.DetailActivity
import com.example.booksapp.utils.BindingFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class ReadingFragment : BindingFragment<FragmentReadingBinding>(FragmentReadingBinding::inflate) {
    private val viewModel: RentViewModel by viewModel()
    private val args: ReadingFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.run {
            when (args.mode) {
                1 -> toolbar.text = getString(R.string.now_reading_title)
                2 -> toolbar.text = getString(R.string.newly_added_title)
            }
            initViews()
            observeVm()
        }
    }

    private fun initViews() {
        binding.run {
            recyclerView.adapter = RentsAdapter { id: Int -> onBookClicked(id) }
            back.setOnClickListener {
                val action = ReadingFragmentDirections.actionBackToHome()
                findNavController().navigate(action)
            }
            swipeRefreshLayout.setOnRefreshListener {
                observeVm()
                swipeRefreshLayout.isRefreshing = false
            }
        }
    }

    private fun observeVm() {
        when (args.mode) {
            1 -> observeLd(viewModel.getFullRentList())
            2 -> observeLd(viewModel.getNewAdded("123"))
        }
    }

    private fun observeLd(liveData: LiveData<List<Rent>>) {
        binding.run {
            liveData.observe(viewLifecycleOwner) {
                (recyclerView.adapter as RentsAdapter).submitList(it)
            }
        }
    }

    private fun onBookClicked(id: Int) {
        val intent = Intent(context, DetailActivity::class.java)
        intent.putExtra("book_id", id)
        startActivity(intent)
    }

}