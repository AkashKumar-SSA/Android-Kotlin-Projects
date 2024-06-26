package com.example.lighdarkmode

import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        val lightMode : Button = findViewById(R.id.lightMode)
        val darkMode : Button = findViewById(R.id.darkMode)
        val readMode : Button = findViewById(R.id.readMode)
        val layout : LinearLayout = findViewById(R.id.main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        darkMode.setOnClickListener {
            layout.setBackgroundResource(R.color.black)
        }
        lightMode.setOnClickListener {
            layout.setBackgroundResource(R.color.white)
        }
        readMode.setOnClickListener {
            layout.setBackgroundResource(R.color.gray)
        }
    }
}