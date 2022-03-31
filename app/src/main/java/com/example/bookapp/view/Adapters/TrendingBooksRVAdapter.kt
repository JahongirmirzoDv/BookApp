package com.example.bookapp.view.Adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.example.bookapp.Models.myclass.AllBook.Book
import uz.ilhomjon.bookapp.databinding.TrendingItemBinding

class TrendingBooksRVAdapter(var trendingBookList: List<Book>, val rvClick: RvClick) :
    RecyclerView.Adapter<TrendingBooksRVAdapter.TrendingVh>() {

    inner class TrendingVh(private val binding: TrendingItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(trendingBookModel: Book, position: Int) {
            binding.trendingBooksText.text = trendingBookModel.title
            Picasso.get().load(trendingBookModel.book_image).into(binding.imageView)
            binding.root.setOnClickListener {
                rvClick.onClick(trendingBookModel)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrendingVh {
        return TrendingVh(
            TrendingItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: TrendingVh, position: Int) {
        holder.onBind(trendingBookList[position], position)
    }

    override fun getItemCount(): Int {
        return trendingBookList.size
    }

    interface RvClick{
        fun onClick(book: Book)
    }
}