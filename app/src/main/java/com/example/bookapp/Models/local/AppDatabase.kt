package com.example.bookapp.Models.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.bookapp.Models.local.convertor.CustomTypeConverters
import com.example.bookapp.Models.local.dao.BookDao
import com.example.bookapp.Models.myclass.bookListByCategory.Book

@Database(entities = [Book::class], version = 1)
@TypeConverters(CustomTypeConverters::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun wordDao(): BookDao

}