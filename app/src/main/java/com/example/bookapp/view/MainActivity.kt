package com.example.bookapp.view

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.WindowInsets
import android.widget.Toast
import androidx.core.view.WindowInsetsCompat
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import me.everything.android.ui.overscroll.OverScrollDecoratorHelper
import com.example.bookapp.view.Adapters.CategoriesRVAdapter
import com.example.bookapp.view.Adapters.TrendingBooksRVAdapter
import com.example.bookapp.Models.Model
import com.example.bookapp.Models.myclass.AllBook.MyBook
import com.example.bookapp.Models.myclass.CategoryClass.MyCategory
import com.example.bookapp.Models.myclass.bookListByCategory.Book
import com.example.bookapp.Models.myclass.bookListByCategory.BookListByCategory
import com.example.bookapp.Models.viewmodel.MyResource
import com.example.bookapp.Models.viewmodel.MyStatus
import uz.ilhomjon.bookapp.databinding.ActivityMainBinding
import com.example.bookapp.presenter.Contacts
import com.example.bookapp.presenter.Presenter
import com.example.bookapp.view.Adapters.CategoryBooksAdapter

class MainActivity : AppCompatActivity(), Contacts.View {

    private lateinit var binding: ActivityMainBinding
    private val TAG = "MainActivity"
    private var presenter: Presenter? = null

    private lateinit var categoriesRVAdapter: CategoriesRVAdapter
    private lateinit var categoryBooksRVAdapter: CategoryBooksAdapter
    private lateinit var trendingBooksRVAdapter: TrendingBooksRVAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            WindowInsetsCompat.toWindowInsetsCompat(WindowInsets.CONSUMED)
        }
        OverScrollDecoratorHelper.setUpOverScroll(binding.categoriesRV, OverScrollDecoratorHelper.ORIENTATION_HORIZONTAL);
        OverScrollDecoratorHelper.setUpOverScroll(binding.topAuthorsRV, OverScrollDecoratorHelper.ORIENTATION_HORIZONTAL);
        OverScrollDecoratorHelper.setUpOverScroll(binding.trendingBooksRV, OverScrollDecoratorHelper.ORIENTATION_HORIZONTAL);
        OverScrollDecoratorHelper.setUpOverScroll(binding.scrollView)
//        loadData()
        presenter = Presenter(this, Model(this, this))
        presenter?.onCreateStart()

        binding.apply {
            searchView.addTextChangedListener {
                if (it.toString() == ""){
                    categoriesRV.visibility = View.VISIBLE
                    categoriesTv.visibility = View.VISIBLE
                    topAuthorsRV.visibility = View.VISIBLE
                    topAuthorsTv.visibility = View.VISIBLE
                    trendingBooksTv.visibility = View.VISIBLE
                    presenter?.onCreateStart()
                }else{
                    categoriesRV.visibility = View.GONE
                    topAuthorsRV.visibility = View.GONE
                    categoriesTv.visibility = View.GONE
                    topAuthorsTv.visibility = View.GONE
                    trendingBooksTv.visibility = View.GONE
                    presenter?.setSearch(it.toString())
                }
            }
        }
    }

    /*
    private fun loadData() {

        val myBookViewModel: MyBookViewModel =
            ViewModelProvider(this).get(MyBookViewModel::class.java)
        myBookViewModel.getMyBook().observe(this) {
            when (it.status) {
                MyStatus.LOADING -> {
                    Log.d(TAG, "loadData: loading")
                    binding.progressAuthor.visibility = View.VISIBLE
                }
                MyStatus.ERROR -> {
                    Log.d(TAG, "loadData: Error \n ${it.message}")
                    binding.progressAuthor.visibility = View.GONE
                    Toast.makeText(
                        this,
                        "Error. Internetga ulanishni tekshiring...",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                MyStatus.SUCCESS -> {
                    Log.d(TAG, "loadData: ${it.data}")
                    binding.progressAuthor.visibility = View.GONE
                    trendingBooksRVAdapter =
                        TrendingBooksRVAdapter(it.data?.results?.lists?.get(0)?.books!!)
                    binding.topAuthorsRV.adapter = trendingBooksRVAdapter
                }
            }
        }

        myBookViewModel.getCategoryNames().observe(this) {
            when (it.status) {
                MyStatus.LOADING -> {
                    Log.d(TAG, "loadData: loading")
                    binding.progressCategoryNames.visibility = View.VISIBLE
                }
                MyStatus.ERROR -> {
                    Log.d(TAG, "loadData: Error \n ${it.message}")
                    binding.progressCategoryNames.visibility = View.GONE
                    Toast.makeText(
                        this,
                        "Error. Internetga ulanishni tekshiring...",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                MyStatus.SUCCESS -> {
                    Log.d(TAG, "loadData: ${it.data}")
                    binding.progressCategoryNames.visibility = View.GONE

                    val l = it.data?.results
                    categoriesRVAdapter =
                        CategoriesRVAdapter(l!!, object : CategoriesRVAdapter.RvClick {
                            override fun onClick(title: String) {
                                myBookViewModel.getBookByCategoryList(title)
                                    .observe(this@MainActivity) {
                                        when (it.status) {
                                            MyStatus.LOADING -> {
                                                binding.progressTrendingBooks.visibility =
                                                    View.VISIBLE
                                            }
                                            MyStatus.SUCCESS -> {
                                                binding.progressTrendingBooks.visibility = View.GONE
                                                Log.d(TAG, "onClick: ${it.data}")
                                                val categoryBooksAdapter =
                                                    CategoryBooksAdapter(it.data?.results?.books!!)
                                                binding.trendingBooksRV.adapter =
                                                    categoryBooksAdapter
                                            }
                                            MyStatus.ERROR -> {
                                                binding.progressTrendingBooks.visibility = View.GONE
                                                Toast.makeText(
                                                    this@MainActivity,
                                                    "Error category",
                                                    Toast.LENGTH_SHORT
                                                )
                                                    .show()
                                            }
                                        }
                                    }
                            }
                        })
                    binding.categoriesRV.adapter = categoriesRVAdapter
                }
            }
        }
    }
     */

    override fun showTrendingBooks(res: MyResource<MyBook>) {
        when (res.status) {
            MyStatus.SUCCESS -> {
                val l = res.data?.results!!.lists[0].books
                trendingBooksRVAdapter = TrendingBooksRVAdapter(l, object : TrendingBooksRVAdapter.RvClick{
                    override fun onClick(book: com.example.bookapp.Models.myclass.AllBook.Book) {
                        val intent = Intent(this@MainActivity, MainActivity2::class.java)
                        intent.putExtra("isCategory", 0)
                        intent.putExtra("keyBook", book)
                        startActivity(intent)
                    }
                })
                val manager = LinearLayoutManager(this)
                manager.orientation = LinearLayoutManager.HORIZONTAL
                binding.trendingBooksRV.layoutManager = manager
                binding.trendingBooksRV.adapter = trendingBooksRVAdapter
                binding.progressTrendingBooks.visibility = View.GONE
            }
            MyStatus.ERROR -> {
                Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show()
                binding.progressTrendingBooks.visibility = View.GONE
            }
            MyStatus.LOADING->{
                binding.progressTrendingBooks.visibility = View.VISIBLE
            }
        }
    }

    override fun showSearch(res: ArrayList<com.example.bookapp.Models.myclass.AllBook.Book>) {
        trendingBooksRVAdapter = TrendingBooksRVAdapter(res, object : TrendingBooksRVAdapter.RvClick{
            override fun onClick(book: com.example.bookapp.Models.myclass.AllBook.Book) {
                val intent = Intent(this@MainActivity, MainActivity2::class.java)
                intent.putExtra("isCategory", 0)
                intent.putExtra("keyBook", book)
                startActivity(intent)
            }
        })
        Log.d(TAG, "showSearch: $res")
        val manager = LinearLayoutManager(this)
        manager.orientation = LinearLayoutManager.HORIZONTAL
        binding.trendingBooksRV.layoutManager = manager
        binding.trendingBooksRV.adapter = trendingBooksRVAdapter
    }

    override fun showCategoriesName(res: MyResource<MyCategory>) {
        when (res.status) {
            MyStatus.LOADING -> {
                binding.progressCategoryNames.visibility = View.VISIBLE
            }
            MyStatus.ERROR -> {
                binding.progressCategoryNames.visibility = View.GONE
                Toast.makeText(
                    this,
                    "Error. Internetga ulanishni tekshiring...",
                    Toast.LENGTH_SHORT
                ).show()
            }
            MyStatus.SUCCESS -> {
                binding.progressCategoryNames.visibility = View.GONE
                categoriesRVAdapter = CategoriesRVAdapter(res.data?.results!!, object : CategoriesRVAdapter.RvClick{
                    override fun onClick(title: String) {
                        i=0
                        presenter?.setCategory(title)
                    }
                })
                val manager = StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.HORIZONTAL)
                binding.categoriesRV.layoutManager = manager
                binding.categoriesRV.adapter = categoriesRVAdapter
                binding.progressCategoryNames.visibility = View.GONE

                presenter?.setCategory(res.data.results[0].list_name)
            }
        }
    }

    var i = 0
    override fun showCategoriesBooks(res: MyResource<BookListByCategory>) {
        when(res.status){
            MyStatus.LOADING->{
                binding.progressAuthor.visibility = View.VISIBLE
            }
            MyStatus.ERROR->{
                Toast.makeText(this, "Error category books", Toast.LENGTH_SHORT).show()
                binding.progressAuthor.visibility = View.GONE
            }
            MyStatus.SUCCESS->{
                val l = res.data?.results?.books!!
                Log.d(TAG, "showCategoriesBooks success ${i++}: $l")
                categoryBooksRVAdapter = CategoryBooksAdapter(l, object : CategoryBooksAdapter.RvClick{
                    override fun onCLick(book: Book) {
                        val intent = Intent(this@MainActivity, MainActivity2::class.java)
                        intent.putExtra("isCategory", 1)
                        intent.putExtra("keyBook", book)
                        startActivity(intent)
                    }
                })
                val manager = LinearLayoutManager(this)
                manager.orientation = LinearLayoutManager.HORIZONTAL
                binding.topAuthorsRV.layoutManager = manager
                binding.topAuthorsRV.adapter = categoryBooksRVAdapter
                binding.progressAuthor.visibility = View.GONE
            }
        }
    }
}