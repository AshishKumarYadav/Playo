package com.ashish.playo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ashish.playo.adapter.NewsAdapter
import com.ashish.playo.api.RetrofitServices
import com.ashish.playo.repository.MainRepository
import com.ashish.playo.repository.MainViewModel
import com.ashish.playo.repository.MyViewModelFactory

class MainActivity : AppCompatActivity() {


    lateinit var recyclerView: RecyclerView
    var mLayoutManager: LinearLayoutManager?=null
    private val TAG = "MainActivity"
    lateinit var viewModel: MainViewModel
    private val retrofitService = RetrofitServices.getInstance()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initView()
    }

    fun initView() {

        recyclerView = findViewById(R.id.recycler_view)

        mLayoutManager = LinearLayoutManager(this)
        recyclerView.setLayoutManager(mLayoutManager)
        viewModel = ViewModelProvider(this, MyViewModelFactory(MainRepository(retrofitService))).get(MainViewModel::class.java)
        viewModel.getAllNews()

        val adapter = NewsAdapter(this)
        viewModel.newsList.observe(this, Observer {
            Log.d(TAG, "onCreate: $it")
            recyclerView.adapter =adapter
            adapter.setNewsList(it.articles)
        })
    }
}