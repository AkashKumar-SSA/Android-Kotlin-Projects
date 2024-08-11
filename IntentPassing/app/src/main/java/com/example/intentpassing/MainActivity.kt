package com.example.intentpassing

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        val name : EditText = findViewById(R.id.name)
        val email: EditText = findViewById(R.id.email)
        val phone : EditText = findViewById(R.id.phone)
        val showDetail : Button = findViewById(R.id.showDetail)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        showDetail.setOnClickListener {
//            Toast.makeText(this,"${name.text }"+"\n" + "${email.text}" + "${phone.text}" , Toast.LENGTH_LONG).show()
            val intent = Intent(this,ShowDetail::class.java)
            intent.putExtra("key",name.text.toString())
            startActivity(intent)
//            intent = Intent(this,ShowDetail::class.java).also {
//                it.putExtra("key",name.text.toString())
//                startActivity(it)
//            }
        }

    }
}