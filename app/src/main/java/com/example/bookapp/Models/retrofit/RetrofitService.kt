package com.example.bookapp.Models.retrofit

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import com.example.bookapp.Models.myclass.AllBook.MyBook
import com.example.bookapp.Models.myclass.CategoryClass.MyCategory
import com.example.bookapp.Models.myclass.bookListByCategory.BookListByCategory
import com.example.bookapp.Models.retrofit.Common.API_KEY

interface RetrofitService {

    @GET("lists/full-overview.json")
    suspend fun getAllBook(
        @Query("api-key") api_key: String = API_KEY
    ): MyBook

    /*
//    https://api.nytimes.com/svc/books/v3/reviews.json?author=Stephen+King&api-key=beKOJAq1sjYHYp2raykgNMvjzHt4npjr
//    https://api.nytimes.com/svc/books/v3/lists/best-sellers/history.json?api-key=beKOJAq1sjYHYp2raykgNMvjzHt4npjr

//    https://api.nytimes.com/svc/books/v3/reviews.json?title=outliers&api-key=beKOJAq1sjYHYp2raykgNMvjzHt4npjr
//    @GET("reviews.json?title={title}&api-key=$API_KEY")
//    fun searchWithTitle(@Path("title") title:String)
     */

    //    category name link: https://api.nytimes.com/svc/books/v3/lists/names?api-key=beKOJAq1sjYHYp2raykgNMvjzHt4npjr
    @GET("lists/names")
    suspend fun getCategoryNameList(
        @Query("api-key") api_key:String = API_KEY
    ): MyCategory

    //    berilgan kategoriyadagi kitoblar ro'yhatini olish
//    https://api.nytimes.com/svc/books/v3/lists/current/Combined%20Print%20and%20E-Book%20Fiction.json?api-key=beKOJAq1sjYHYp2raykgNMvjzHt4npjr
    @GET("lists/current/{path}.json")
    suspend fun getBookListByCategory(
        @Path("path") path: String,
        @Query("api-key") api_key: String = API_KEY
    ): BookListByCategory

//    bestsellerslar ro'yhatini olish
//    https://api.nytimes.com/svc/books/v3/lists/best-sellers/history.json?api-key=beKOJAq1sjYHYp2raykgNMvjzHt4npjr

}