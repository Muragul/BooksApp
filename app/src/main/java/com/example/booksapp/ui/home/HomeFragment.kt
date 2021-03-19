package com.example.booksapp.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.example.booksapp.databinding.FragmentHomeBinding
import com.example.booksapp.ui.adapter.RentsAdapter
import com.example.booksapp.ui.scan.DetailActivity
import com.example.booksapp.utils.BindingFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : BindingFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate) {
    private val viewModel: RentViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setListeners()
        initViews()
        observeVm()
    }

    private fun setListeners() {
        binding.run {
            swipeRefreshLayout.setOnRefreshListener {
                observeVm()
                swipeRefreshLayout.isRefreshing = false
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
    }

    private fun initViews() {
        binding.run {
            recyclerViewReading.adapter = RentsAdapter { id: Int -> onBookClicked(id) }
            recyclerViewAdded.adapter = RentsAdapter { id: Int -> onBookClicked(id) }
        }
    }

    private fun onBookClicked(id: Int) {
        val intent = Intent(context, DetailActivity::class.java)
        intent.putExtra("book_id", id)
        startActivity(intent)
    }

    private fun observeVm() {
        binding.run {
            viewModel.getFullRentList().observe(viewLifecycleOwner) {
                if (it.size > 5)
                    (recyclerViewReading.adapter as RentsAdapter).submitList(it.subList(0, 5))
                else
                    (recyclerViewReading.adapter as RentsAdapter).submitList(it)
            }
            viewModel.getNewAdded("123").observe(viewLifecycleOwner) {
                if (it.size > 5)
                    (recyclerViewAdded.adapter as RentsAdapter).submitList(
                        it.subList(0, 5).sortedByDescending { item -> item.updatedAt })
                else
                    (recyclerViewAdded.adapter as RentsAdapter).submitList(it.sortedByDescending { item -> item.updatedAt })
            }
        }
    }

}