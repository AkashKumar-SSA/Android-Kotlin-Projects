package com.example.toastsnackbar

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.toastsnackbar.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar

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
        val personNameData = binding.personName
        val intent = Intent(this,SecondActivity::class.java)


        binding.save.setOnClickListener {
            Toast.makeText(this,personNameData.text.toString(),Toast.LENGTH_SHORT).show()
            startActivity(intent)
        }
        binding.showToast.setOnClickListener {
            Toast.makeText(this,"Successfully displaying the Toast",Toast.LENGTH_SHORT).show()
        }
        binding.showSnackBar.setOnClickListener {
            Snackbar.make(it,"To See your details",Snackbar.LENGTH_SHORT)
                .setAction("click Here"){
                    Toast.makeText(this,"Going to next Activity",Toast.LENGTH_SHORT).show()
                    startActivity(intent)
                }
                .show()
        }
    }
}