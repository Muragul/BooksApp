package com.example.booksapp.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.ListAdapter
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.booksapp.R
import com.example.booksapp.data.model.Rent

class RentsAdapter(private val onBookClicked: (id: Int) -> Unit) :
    ListAdapter<Rent, RentsAdapter.RentViewHolder>(RENTS_COMPARATOR) {
    class RentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val bookName: TextView = itemView.findViewById(R.id.book_name)
        private val bookAuthor: TextView = itemView.findViewById(R.id.book_author)
        private val bookImage: ImageView = itemView.findViewById(R.id.book_image)
        private val baseUrl: String = "https://dev-darmedia-uploads.s3.eu-west-1.amazonaws.com/"

        fun bind(rent: Rent?) {
            bookName.text = rent?.book?.title
            bookAuthor.text = rent?.book?.author
            if (rent?.book?.image !== null)
                Glide.with(itemView).load(R.drawable.mock_image).into(bookImage)
            else
                Glide.with(itemView).load(baseUrl + rent?.book?.image).into(bookImage)
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
        holder.itemView.setOnClickListener {
            onBookClicked.invoke(currentItem.book_id)
        }
    }

}