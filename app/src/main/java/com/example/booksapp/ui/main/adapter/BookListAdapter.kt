package com.example.booksapp.ui.main.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.booksapp.R
import com.example.booksapp.data.db.Book

class BookListAdapter : ListAdapter<Book, BookListAdapter.BookViewHolder>(BOOKS_COMPARATOR) {

    class BookViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val bookName: TextView = itemView.findViewById(R.id.book_name)
        private val bookAuthor: TextView = itemView.findViewById(R.id.book_author)

        fun bind(book: Book) {
            bookName.text = book.title
            bookAuthor.text = book.author
        }

        companion object {
            fun create(parent: ViewGroup): BookViewHolder {
                val view: View =
                    LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false)
                return BookViewHolder(view)
            }
        }
    }

    companion object {
        private val BOOKS_COMPARATOR = object : DiffUtil.ItemCallback<Book>() {
            override fun areItemsTheSame(oldItem: Book, newItem: Book): Boolean {
                return oldItem === newItem
            }

            override fun areContentsTheSame(oldItem: Book, newItem: Book): Boolean {
                return oldItem.title == newItem.title
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        return BookViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        val currentItem = getItem(position)
        holder.bind(currentItem)
    }
}