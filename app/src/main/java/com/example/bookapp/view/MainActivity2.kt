package com.example.bookapp.view

import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import com.example.bookapp.Models.local.DatabaseBuilder
import com.squareup.picasso.Picasso
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import com.example.bookapp.Models.local.DatabaseHelperImpl
import com.example.bookapp.Models.myclass.bookListByCategory.Book
import uz.ilhomjon.bookapp.R
import uz.ilhomjon.bookapp.databinding.ActivityMain2Binding

class MainActivity2 : AppCompatActivity() {
    lateinit var binding: ActivityMain2Binding
    var isCategory = -1
    var book: Book? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            WindowCompat.setDecorFitsSystemWindows(window, false)
        }

        var db = DatabaseHelperImpl(
            DatabaseBuilder.getInstance(this)
        )

        isCategory = intent.getIntExtra("isCategory", -1)

        if (isCategory == 1) {
            book = intent.getSerializableExtra("keyBook") as Book
            binding.apply {
                Picasso.get().load(book!!.book_image).into(image2)
                tvDisplayName1.text = book!!.title
                tvDisplayName2.text = book!!.author
                tvRating.text = book!!.rank.toString()
                tvPrice.text = book!!.price
                tvDesc.text = book!!.description
            }
        } else if (isCategory == 0) {
            val book =
                intent.getSerializableExtra("keyBook") as com.example.bookapp.Models.myclass.AllBook.Book
            binding.apply {
                Picasso.get().load(book.book_image).into(image2)
                tvDisplayName1.text = book.title
                tvDisplayName2.text = book.author
                tvRating.text = book.rank.toString()
                tvPrice.text = book.price
                tvDesc.text = book.description
            }
        }
        binding.imageBack.setOnClickListener { finish() }

        var isClick = false
        binding.save.setOnClickListener {
            isClick = !isClick
            if (isClick) {
                binding.save.setImageResource(R.drawable.ic_baseline_bookmark_24)
            } else binding.save.setImageResource(R.drawable.save)
            book?.let { it1 -> db.addBook(it1) }
            GlobalScope.launch {
                db.getAllSaved().map {
                    Log.e("TAG", "onCreate: ${it.author}")
                }
            }
        }
    }
}