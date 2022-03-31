package com.example.bookapp.Models.myclass.AllBook

import com.example.bookapp.Models.myclass.AllBook.Results

data class MyBook(
    val copyright: String,
    val num_results: Int,
    val results: Results,
    val status: String
)