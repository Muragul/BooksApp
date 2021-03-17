package com.example.booksapp.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.booksapp.R
import com.example.booksapp.data.model.Book

class BooksAdapter(private val onBookClicked: (id: Int) -> Unit) :
    ListAdapter<Book, BooksAdapter.BookViewHolder>(BOOKS_COMPARATOR) {

    class BookViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val bookName: TextView = itemView.findViewById(R.id.book_name)
        private val bookAuthor: TextView = itemView.findViewById(R.id.book_author)
        private val bookImage: ImageView = itemView.findViewById(R.id.book_image)
        private val baseUrl: String = "https://dev-darmedia-uploads.s3.eu-west-1.amazonaws.com/"

        fun bind(book: Book) {
            bookName.text = book.title
            bookAuthor.text = book.author
            if (book.image == null || book.image == "")
                Glide.with(itemView).load(R.drawable.mock_image).into(bookImage)
            else
                Glide.with(itemView).load(baseUrl + book.image).into(bookImage)
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
        holder.itemView.setOnClickListener {
            onBookClicked.invoke(currentItem.id)
        }
    }
}