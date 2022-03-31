package com.example.bookapp.Models.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.bookapp.Models.myclass.bookListByCategory.Book

@Dao
interface BookDao {
    @Insert
    fun addBook(book: Book)

    @Query("select * from book")
    suspend fun getSavedList():List<Book>
}