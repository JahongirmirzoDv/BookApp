package com.example.bookapp.Models.viewmodel

data class MyResource<out T> (val status: MyStatus, val data:T?, val message:String?) {
    companion object{
        fun <T> success(data: T): MyResource<T> =
            MyResource(MyStatus.SUCCESS, data, null)
        fun <T> error(message:String?, data: T?) : MyResource<T> =
            MyResource(MyStatus.ERROR, data, message)
        fun <T> loading(data:T?) : MyResource<T> =
            MyResource(MyStatus.LOADING, data, null)
    }
}

enum class MyStatus{
    SUCCESS,
    ERROR,
    LOADING
}