package com.example.booksapp.ui.main.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.booksapp.R
import androidx.recyclerview.widget.ListAdapter
import com.example.booksapp.data.db.Genre

class GenreListAdapter(private val onItemClick: (itemId: Int) -> Unit) :
    ListAdapter<Genre, GenreListAdapter.GenreViewHolder>(GENRES_COMPARATOR) {

    class GenreViewHolder(itemView: View, private val onItemClick: (itemId: Int) -> Unit) :
        RecyclerView.ViewHolder(itemView) {
        private val genreTitle: TextView = itemView.findViewById(R.id.genre_title)

        fun bind(genre: Genre) {
            genreTitle.text = genre.title
            itemView.setOnClickListener {
                onItemClick.invoke(genre.id)
            }
        }

        companion object {
            fun create(parent: ViewGroup, onItemClick: (itemId: Int) -> Unit): GenreViewHolder {
                val view: View =
                    LayoutInflater.from(parent.context).inflate(R.layout.item_genre, parent, false)
                return GenreViewHolder(view, onItemClick)
            }
        }
    }

    companion object {
        private val GENRES_COMPARATOR =
            object : DiffUtil.ItemCallback<Genre>() {
                override fun areItemsTheSame(
                    oldItem: Genre,
                    newItem: Genre
                ): Boolean {
                    return oldItem === newItem
                }

                override fun areContentsTheSame(
                    oldItem: Genre,
                    newItem: Genre
                ): Boolean {
                    return oldItem.title == newItem.title
                }

            }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenreViewHolder {
        return GenreViewHolder.create(parent, onItemClick)
    }

    override fun onBindViewHolder(holder: GenreViewHolder, position: Int) {
        val currentItem = getItem(position)
        holder.bind(currentItem)
    }

}