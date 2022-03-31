package com.example.bookapp.presenter

import com.example.bookapp.Models.myclass.AllBook.Book
import com.example.bookapp.Models.myclass.AllBook.MyBook
import com.example.bookapp.Models.myclass.CategoryClass.MyCategory
import com.example.bookapp.Models.myclass.bookListByCategory.BookListByCategory
import com.example.bookapp.Models.viewmodel.MyResource

class Presenter(
    private val mainView: Contacts.View?,
    private val model: Contacts.Model
) : Contacts.Presenter, Contacts.Model.ApiGetBooks {
    override fun getTrendingBooks(res: MyResource<MyBook>) {
        mainView?.showTrendingBooks(res)
    }

    override fun getCategoriesName(res: MyResource<MyCategory>) {
        mainView?.showCategoriesName(res)
    }

    override fun getCategoriesBooks(res: MyResource<BookListByCategory>) {
        mainView?.showCategoriesBooks(res)
    }

    override fun getSerach(res: ArrayList<Book>) {
        mainView?.showSearch(res)
    }

    override fun onCreateStart() {
        model.updateUiFirst(this)
    }

    override fun setCategory(title: String) {
        model.setCategoryBooks(this, title)
    }

    override fun setSearch(text: String) {
        model.setSearch(this, text)
    }

}