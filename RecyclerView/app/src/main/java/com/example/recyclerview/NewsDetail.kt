package com.example.recyclerview

import android.os.Bundle
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class NewsDetail : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_news_detail)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.newsMain)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val newsPic = intent.getIntExtra("imageId", 0)
        val newsHead = intent.getStringExtra("heading")
        val newsContents = intent.getStringExtra("news")


        val newsImage: ImageView = findViewById(R.id.newsImage)
        val newsHeading: TextView = findViewById(R.id.newsHeading)
        val newsContent: TextView = findViewById(R.id.newsContent)
        val newsMain: LinearLayout = findViewById(R.id.newsMain)


        newsImage.setImageResource(newsPic)
        newsHeading.text = newsHead
        newsContent.text = newsContents
        newsImage.setOnClickListener {
            Toast.makeText(applicationContext,"Clicked on ${newsHeading.text}'s image",Toast.LENGTH_SHORT).show()
        }
    }
}