package com.example.booksapp.ui.main.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.booksapp.R
import com.example.booksapp.data.model.Rent

class RentListAdapter : ListAdapter<Rent, RentListAdapter.RentViewHolder>(RENTS_COMPARATOR) {
    class RentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val bookName: TextView = itemView.findViewById(R.id.book_name)
        private val bookAuthor: TextView = itemView.findViewById(R.id.book_author)

        fun bind(rent: Rent?) {
            bookName.text = rent?.book?.title
            bookAuthor.text = rent?.book?.author
        }

        companion object {
            fun create(parent: ViewGroup): RentViewHolder {
                val view: View =
                    LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false)
                return RentViewHolder(view)
            }
        }
    }

    companion object {
        private val RENTS_COMPARATOR = object : DiffUtil.ItemCallback<Rent>() {
            override fun areItemsTheSame(oldItem: Rent, newItem: Rent): Boolean {
                return oldItem === newItem
            }

            override fun areContentsTheSame(oldItem: Rent, newItem: Rent): Boolean {
                return oldItem.id == newItem.id
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RentViewHolder {
        return RentViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: RentViewHolder, position: Int) {
        val currentItem = getItem(position)
        holder.bind(currentItem)
    }

}