package com.example.booksapp.ui.scan

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.budiyev.android.codescanner.CodeScanner
import com.budiyev.android.codescanner.DecodeCallback
import com.example.booksapp.databinding.FragmentScanBinding
import com.example.booksapp.utils.BindingFragment

class ScanFragment : BindingFragment<FragmentScanBinding>(FragmentScanBinding::inflate) {
    private lateinit var codeScanner: CodeScanner

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.run {
            val activity = requireActivity()
            codeScanner = CodeScanner(activity, scannerView)
            codeScanner.decodeCallback = DecodeCallback {
                activity.runOnUiThread {
                    onCodeScanned(it.text)
                }
            }
            scannerView.setOnClickListener {
                codeScanner.startPreview()
            }
        }
    }

    private fun onCodeScanned(isbn: String) {
        val intent = Intent(context, DetailActivity::class.java)
        intent.putExtra("book_isbn", isbn)
        startActivity(intent)
    }

    override fun onResume() {
        super.onResume()
        codeScanner.startPreview()
    }

    override fun onPause() {
        codeScanner.releaseResources()
        super.onPause()
    }

}

