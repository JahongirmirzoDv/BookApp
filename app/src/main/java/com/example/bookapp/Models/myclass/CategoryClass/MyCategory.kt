package com.example.bookapp.Models.myclass.CategoryClass

data class MyCategory(
    val copyright: String,
    val num_results: Int,
    val results: List<Result>,
    val status: String
)