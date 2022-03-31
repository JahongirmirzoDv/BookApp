package com.example.bookapp.Models.myclass.bookListByCategory

import com.example.bookapp.Models.myclass.bookListByCategory.Results

data class BookListByCategory(
    val copyright: String,
    val last_modified: String,
    val num_results: Int,
    val results: Results,
    val status: String
)