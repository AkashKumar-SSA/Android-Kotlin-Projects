package com.example.photophrameapp

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.photophrameapp.databinding.ActivityMainBinding
import kotlin.math.log

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val imagesArray: Array<Int> = arrayOf(R.drawable.barfi, R.drawable.chikki,R.drawable.dabeli,R.drawable.dhokla,R.drawable.basundi,R.drawable.alootikki,R.drawable.aloo_chaat,R.drawable.balushahi,R.drawable.bananachips)
        val imagesNameArray: Array<String> = arrayOf("Barfi","Chikki","Dabeli","Dhokla","Basundi","Aloo Tikki","Aloo Chaat","Balsushahi","Banana Chips")
        val imagesArraySize = imagesArray.size
//        var imagesNameArraySize = imagesNameArray.size
        var currentImage = 0
        var currentImageName = 0
       binding.imageDescription.setText(imagesNameArray[0])
        binding.images.setImageDrawable(ContextCompat.getDrawable(this, imagesArray[0]))
        binding.prevButton.setOnClickListener {
            currentImage--

            if (currentImage < 0) {
                currentImage = imagesArraySize - 1
            }
            binding.imageDescription.setText(imagesNameArray[currentImage])
            binding.images.setImageDrawable(ContextCompat.getDrawable(this, imagesArray[currentImage]))
        }
        binding.nextButton.setOnClickListener {
            currentImage++
            currentImageName++

            if (currentImage == imagesArraySize) {
                currentImage = 0
            }
            binding.imageDescription.setText(imagesNameArray[currentImage])
            binding.images.setImageDrawable(ContextCompat.getDrawable(this, imagesArray[currentImage]))
        }
    }
}