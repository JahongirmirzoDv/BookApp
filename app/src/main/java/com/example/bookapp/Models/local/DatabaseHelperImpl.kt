package com.example.bookapp.Models.local

import com.example.bookapp.Models.myclass.bookListByCategory.Book

class DatabaseHelperImpl(private val appDatabase: AppDatabase) : DatabaseHelper {
    override  fun addBook(book: Book) = appDatabase.wordDao().addBook(book)

    override suspend fun getAllSaved(): List<Book>  = appDatabase.wordDao().getSavedList()

}