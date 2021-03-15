package com.example.booksapp.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckedTextView
import androidx.recyclerview.widget.RecyclerView
import com.example.booksapp.R
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.booksapp.data.model.Genre

class GenreListAdapter(
    private val onItemClick: (genres: ArrayList<Int>) -> Unit,
    var genres: ArrayList<Int>
) : ListAdapter<Genre, GenreListAdapter.GenreViewHolder>(GENRES_COMPARATOR) {

    class GenreViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val genreTitle: CheckedTextView = itemView.findViewById(R.id.genre_title)

        fun bind(genre: Genre, isChecked: Boolean) {
            genreTitle.text = genre.title
            if (isChecked) {
                genreTitle.setCheckMarkDrawable(R.drawable.ic_selected)
            }
        }

        companion object {
            fun create(parent: ViewGroup): GenreViewHolder {
                val view: View =
                    LayoutInflater.from(parent.context).inflate(R.layout.item_genre, parent, false)
                return GenreViewHolder(view)
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
        return GenreViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: GenreViewHolder, position: Int) {
        val currentItem = getItem(position)
        holder.bind(currentItem, genres.contains(currentItem.id))
        val item = holder.itemView.findViewById<CheckedTextView>(R.id.genre_title)
        item.setOnClickListener {
            if (genres.contains(currentItem.id)) {
                item.checkMarkDrawable = null
                genres.remove(currentItem.id)
            } else {
                item.setCheckMarkDrawable(R.drawable.ic_selected)
                genres.add(currentItem.id)
            }
            onItemClick.invoke(genres)
        }
    }

}
