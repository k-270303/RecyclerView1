package com.example.painting

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.painting.Datanetwork.DatasetItem
import com.example.painting.databinding.ActivityMainBinding
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {



private lateinit var binding: ActivityMainBinding
private  var adapter= Adapter()
    private var currentPage: Int = 1
    private val imageList = mutableListOf<DatasetItem>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater) // same as
        setContentView(binding.root)
        binding.recyclerview.adapter=adapter
        var layoutManager=LinearLayoutManager(this)
        binding.recyclerview.layoutManager= layoutManager


        setSupportActionBar(binding.toolbar)

       binding.up.setOnClickListener{
            binding.recyclerview.smoothScrollToPosition(0)

    }
        initView()
        getImages()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.mainmenu,menu)
        return true
    }
    private fun initView() {


       binding.recyclerview.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                val layoutManager = recyclerView.layoutManager as LinearLayoutManager
                binding.up.isVisible=layoutManager.findLastVisibleItemPosition()!=0
            }
       }
       )
        val pagerSnapHelper = PagerSnapHelper()
        pagerSnapHelper.attachToRecyclerView(binding.recyclerview)
    }
    private fun getImages() {


        var RetroFit:Retro = Retro.create()

        //lifecyclescope is for background threading

        lifecycleScope.launch{
            try{
                val response = RetroFit.loadimages(const.clientID, currentPage)
                response.body()?.let {
                    if (response.body() != null) {
                        imageList.addAll(response.body()!!)
                    }
                    adapter.submitList(imageList.toMutableList())
                }
            }catch (e:Exception){e.printStackTrace()}
        }
}}