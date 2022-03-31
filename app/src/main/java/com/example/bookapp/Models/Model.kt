package com.example.bookapp.Models

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import com.example.bookapp.Models.myclass.AllBook.Book
import com.example.bookapp.Models.viewmodel.MyBookViewModel
import com.example.bookapp.Models.viewmodel.MyStatus
import com.example.bookapp.presenter.Contacts
import java.lang.Exception

class Model(var viewModelStoreOwner: ViewModelStoreOwner, var lifecycleOwner: LifecycleOwner) :
    Contacts.Model {
    val myBookViewModel: MyBookViewModel =
        ViewModelProvider(viewModelStoreOwner).get(MyBookViewModel::class.java)
    var list = ArrayList<Book>()

    override fun updateUiFirst(apiGetBooks: Contacts.Model.ApiGetBooks) {
        myBookViewModel.getMyBook().observe(lifecycleOwner) {
            when(it.status){
                MyStatus.SUCCESS->{
                    list.clear()
                    list.addAll(it.data?.results!!.lists[0].books)
                }
            }
            apiGetBooks.getTrendingBooks(it)
        }
        myBookViewModel.getCategoryNames().observe(lifecycleOwner) {
            apiGetBooks.getCategoriesName(it)
        }
    }

    override fun setCategoryBooks(apiGetBooks: Contacts.Model.ApiGetBooks, title: String) {
        myBookViewModel.getBookByCategoryList(title).observe(lifecycleOwner){
            apiGetBooks.getCategoriesBooks(it)
        }
    }

    override fun setSearch(apiGetBooks: Contacts.Model.ApiGetBooks, title: String) {
        var l = ArrayList<Book>()
        try {
            list.forEach {
                if (it.title.lowercase().substring(0, title.length) == title.lowercase()) {
                    l.add(it)
                }
            }
        }catch (e:Exception){
            l.clear()
        }
        apiGetBooks.getSerach(l)
    }
}