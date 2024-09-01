package com.example.realtimeadmin

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.realtimeadmin.databinding.ActivityMainBinding

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
        binding.mainUpload.setOnClickListener {
            startActivity(Intent(this@MainActivity, UploadActivity::class.java))
            finish()
        }
        binding.mainUpdate.setOnClickListener {
            startActivity(Intent(this@MainActivity,UpdateActivity::class.java))
            finish()
        }
        binding.mainDelete.setOnClickListener {
            startActivity(Intent(this@MainActivity,DeleteActivity::class.java))
            finish()
        }
    }
}