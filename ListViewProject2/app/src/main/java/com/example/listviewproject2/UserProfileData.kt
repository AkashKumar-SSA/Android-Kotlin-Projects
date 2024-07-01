package com.example.listviewproject2

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class UserProfileData : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_user_profile_data)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val name = intent.getStringExtra("name")
        val phoneNumber = intent.getStringExtra("phoneNumber")
        val profileImage = intent.getIntExtra("imageId", 0)

        val personProfileImage = findViewById<ImageView>(R.id.personProfileImage)
        val personName = findViewById<TextView>(R.id.personName)
        val personPhoneNumber = findViewById<TextView>(R.id.personPhoneNumber)

        personProfileImage.setImageResource(profileImage)
        personName.text = name
        personPhoneNumber.text = phoneNumber
    }
}