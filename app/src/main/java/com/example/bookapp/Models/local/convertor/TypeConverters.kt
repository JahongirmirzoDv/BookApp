package com.example.bookapp.Models.local.convertor

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.example.bookapp.Models.myclass.bookListByCategory.BuyLink
import com.example.bookapp.Models.myclass.bookListByCategory.Isbn

object CustomTypeConverters {
    var gson = Gson()

    @TypeConverter
    fun foodItemToString(foodItems: List<BuyLink>): String {
        return gson.toJson(foodItems)
    }

    @TypeConverter
    fun stringToFoodItem(data: String): List<BuyLink> {
        val listType = object : TypeToken<List<BuyLink>>() {
        }.type
        return gson.fromJson(data, listType)
    }


    @TypeConverter
    fun pItemToString(pItems: List<Isbn>): String {
        return gson.toJson(pItems)
    }

    @TypeConverter
    fun stringToP(data: String): List<Isbn> {
        val listType = object : TypeToken<List<Isbn>>() {
        }.type
        return gson.fromJson(data, listType)
    }

}

