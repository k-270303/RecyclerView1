package com.example.painting

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ContextMenu
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageButton
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.recyclerview.widget.AdapterListUpdateCallback
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.painting.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {



    var menu: List<Abc> = listOf(
        Abc(
            "https://surface678.com/wp-content/uploads/elementor/thumbs/edited_wake_tech-ovagsl83z9l43ggkcb0jjyd5vo1na0fp5ta32k5qwg.jpg",
            "Leonardo",
            "popular",
            "he medium is commonly applied to the base with a brush, but other implements, such as knives, sponges, and airbrushes, can be used."
        ),
        Abc(
            "https://surface678.com/wp-content/uploads/elementor/thumbs/edited_wake_tech-ovagsl83z9l43ggkcb0jjyd5vo1na0fp5ta32k5qwg.jpg",
            "Leonardo",
            "popular",
            "writer"
        ),
        Abc(
            "https://surface678.com/wp-content/uploads/elementor/thumbs/edited_wake_tech-ovagsl83z9l43ggkcb0jjyd5vo1na0fp5ta32k5qwg.jpg",
            "Leonardo",
            "popular",
            "writer"
        ),
        Abc(
            "https://surface678.com/wp-content/uploads/elementor/thumbs/edited_wake_tech-ovagsl83z9l43ggkcb0jjyd5vo1na0fp5ta32k5qwg.jpg",
            "Leonardo",
            "popular",
            "writer"
        )
    )
private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        binding.toolbar.setNavigationOnClickListener{
            Toast.makeText(this,"Navigation",Toast.LENGTH_LONG).show()
        }
       binding.up.setOnClickListener{
            binding.recyclerview.smoothScrollToPosition(0)

    }
        initView();
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.mainmenu,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        var itemview = item.itemId
        when(itemview){
            R.id.search -> Toast.makeText(applicationContext,"Search",Toast.LENGTH_LONG).show()
            R.id.menu -> Toast.makeText(applicationContext,"menu",Toast.LENGTH_LONG).show()
        }
        return false
    }


    private fun initView() {


//        val adapter = adapter(context = MainActivity(), emptyList())

        binding.recyclerview.layoutManager = LinearLayoutManager(this)
        binding.recyclerview.adapter = Adapter(this, menu)

       binding.recyclerview.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                val layoutManager = recyclerView.layoutManager as LinearLayoutManager
                binding.up.isVisible=layoutManager.findLastVisibleItemPosition()!=0
            }
        })
        val snapHelper = PagerSnapHelper()
        snapHelper.attachToRecyclerView(binding.recyclerview)
    }
}