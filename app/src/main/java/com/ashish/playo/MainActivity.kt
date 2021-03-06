package com.ashish.playo

import android.content.Context
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
import android.net.NetworkInfo

import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.view.View
import android.widget.TextView
import androidx.annotation.RequiresApi

/*
Created by : Ashish Kumar Yadav
 */

class MainActivity : AppCompatActivity() {


    lateinit var recyclerView: RecyclerView
    lateinit var textView: TextView
    var mLayoutManager: LinearLayoutManager?=null
    private val TAG = "MainActivity"
    lateinit var viewModel: MainViewModel
    private val retrofitService = RetrofitServices.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initView()

        if (isOnline(this)){

            setAdapter()

        }else{

            recyclerView.visibility=View.GONE
            textView.visibility=View.VISIBLE

        }
    }



    private fun initView() {

        recyclerView = findViewById(R.id.recycler_view)
        textView=findViewById(R.id.no_internet_text)

        mLayoutManager = LinearLayoutManager(this)
        recyclerView.setLayoutManager(mLayoutManager)


    }

    private fun setAdapter(){

        viewModel = ViewModelProvider(this, MyViewModelFactory(MainRepository(retrofitService))).get(MainViewModel::class.java)
        viewModel.getAllNews()

        val adapter = NewsAdapter(this)
        viewModel.newsList.observe(this, Observer {
            Log.d(TAG, "onCreate: $it")
            recyclerView.adapter =adapter
            adapter.setNewsList(it.articles)
        })
    }

    private fun isOnline(context: Context): Boolean {

        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        val activeNetwork: NetworkInfo? = connectivityManager.activeNetworkInfo

        if(activeNetwork?.isConnected!=null){
            return activeNetwork.isConnected
        }
        else{
            return false
        }
    }



}