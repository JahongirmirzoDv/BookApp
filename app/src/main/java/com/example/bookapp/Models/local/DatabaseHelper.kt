package com.example.bookapp.Models.local

import com.example.bookapp.Models.myclass.bookListByCategory.Book

interface DatabaseHelper {

     fun addBook(book: Book)

    suspend fun getAllSaved(): List<Book>

}