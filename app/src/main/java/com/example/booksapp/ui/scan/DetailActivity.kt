package com.example.booksapp.ui.scan

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.LiveData
import com.bumptech.glide.Glide
import com.example.booksapp.R
import com.example.booksapp.data.model.Book
import com.google.gson.JsonObject
import kotlinx.android.synthetic.main.activity_detail.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.lang.Exception
import java.time.LocalDateTime

class DetailActivity : AppCompatActivity(R.layout.activity_detail) {
    private val viewModel: DetailViewModel by viewModel()
    private val baseUrl: String = "https://dev-darmedia-uploads.s3.eu-west-1.amazonaws.com/"
    private var id: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        id = intent.getIntExtra("book_id", 0)
        initView()
        observeVm()
    }

    private fun initView() {
        back.setOnClickListener { onBackPressed() }

        borrow_book.setOnClickListener {
            val body = JsonObject().apply {
                addProperty("user_id", "123")
                addProperty("user_name", "Miras")
                addProperty("user_contact", "+7 777 777 77 77 mail@gmail.com")
                addProperty("book_id", id)
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    addProperty("start_date", LocalDateTime.now().toString())
                }
            }
            viewModel.createRent(body).observe(this) {
                Log.e("[result: ]", it.toString())
            }
        }

        swipe_refresh_layout.setOnRefreshListener {
            observeVm()
            swipe_refresh_layout.isRefreshing = false
        }
    }

    private fun observeVm() {
        observeLd(viewModel.getBookById(id))
    }

    private fun observeByIsbn() {
        val isbn = intent.getStringExtra("book_isbn").toString()
        observeLd(viewModel.getBookByIsbn(isbn))
    }

    private fun observeLd(liveData: LiveData<Book>) {
        liveData.observe(this) {
            if (it.image != null)
                Glide.with(this).load(baseUrl + it.image).into(book_image)
            else
                Glide.with(this).load(R.drawable.mock_image).into(book_image)
            book_name.text = it.title
            book_author.text = it.author
            publish_date.text = it.publish_date
        }
    }

}