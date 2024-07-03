package com.example.recyclerview

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var myRecyclerView : RecyclerView
    private lateinit var newsArrayList : ArrayList<News>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        myRecyclerView = findViewById(R.id.recyclerView)

        val newsImageArray = arrayOf(R.drawable.modi,
            R.drawable.lata,
            R.drawable.indira,
            R.drawable.ramdev,
            R.drawable.sachin,
            R.drawable.kalam,
            R.drawable.kapil,
            R.drawable.narayan_murti,
            R.drawable.niraj,
            R.drawable.rekha,
            R.drawable.sudha_murti,
            R.drawable.vivekanand,
            R.drawable.shastri,
            R.drawable.shahrukh,
            R.drawable.sania_nehwal)

        val newsHeadingArray = arrayOf(
            "Narendra Modi",
            "Lata Mangeshkar",
            "Indira Gandhi",
            "Ramdev Baba",
            "Sachin Tendulkar",
            "Abdul Kalam",
            "Kapil Dev",
            "Narayan Murti",
            "Niraj Chopra",
            "Rakha",
            "sudha Murti",
            "Vivekanand",
            "Lal Bahadur Shastri",
            "Shahrukh Khan",
            "Sania Nehwal")

        val newsDescriptionArray = arrayOf(getString(R.string.news_content),getString(R.string.news_content),
            getString(R.string.news_content),getString(R.string.news_content),getString(R.string.news_content),
            getString(R.string.news_content),getString(R.string.news_content),getString(R.string.news_content),
            getString(R.string.news_content),getString(R.string.news_content),getString(R.string.news_content),
            getString(R.string.news_content),getString(R.string.news_content),getString(R.string.news_content),
            getString(R.string.news_content)
            )


        myRecyclerView.layoutManager = LinearLayoutManager(this)
        newsArrayList = arrayListOf()

        for (index in newsImageArray.indices){
            val news  = News(newsImageArray[index],newsHeadingArray[index], newsDescriptionArray[index])
            newsArrayList.add(news)
        }
        val myAdapter = MyAdapter(this,newsArrayList)
        myRecyclerView.adapter = myAdapter
//        myRecyclerView.adapter = MyAdapter(this,newsArrayList)
        myAdapter.setOnItemClickListener(object : MyAdapter.OnItemClickListener{
            override fun onItemClick(position: Int) {
                val intent = Intent(this@MainActivity, NewsDetail::class.java)
                intent.putExtra("imageId", newsArrayList[position].newsImage)
                intent.putExtra("heading", newsArrayList[position].newsHeading)
                intent.putExtra("news", newsArrayList[position].newsDescription)
                startActivity(intent)
            }

        })
    }


}