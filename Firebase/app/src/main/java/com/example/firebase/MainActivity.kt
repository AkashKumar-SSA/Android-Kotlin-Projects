package com.example.firebase

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.FirebaseApp
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class MainActivity : AppCompatActivity() {
    private lateinit var database: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val name: EditText = findViewById(R.id.name)
        val phoneNumber: EditText = findViewById(R.id.phoneNumber)
        val email: EditText = findViewById(R.id.email)
        val password: EditText = findViewById(R.id.password)
        val submit: Button = findViewById(R.id.submit)
        FirebaseApp.initializeApp(this)

        submit.setOnClickListener {
            val personName = name.text.toString()
            val personPhoneNumber  = phoneNumber.text.toString()
            val personEmail = email.text.toString()
            val personPassword = password.text.toString()
            val userData = User(personName,personPhoneNumber,personEmail,personPassword)
            database = FirebaseDatabase.getInstance().getReference("https://fir-699b8-default-rtdb.asia-southeast1.firebasedatabase.app/")
            database.child(personName).setValue(userData).addOnSuccessListener {
                Toast.makeText(this,"Data Saved",Toast.LENGTH_SHORT).show()
            }.addOnFailureListener{
                Toast.makeText(this,"Failed",Toast.LENGTH_SHORT).show()
                Log.e("firebase", "Error in Writing data", it)
            }

//            name.text.clear()
//            phoneNumber.text.clear()
//            email.text.clear()
//            password.text.clear()
        }
    }
}