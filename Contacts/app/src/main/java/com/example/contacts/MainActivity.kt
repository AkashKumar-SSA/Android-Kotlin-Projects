package com.example.contacts

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.contacts.databinding.ActivityMainBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var databaseReference: DatabaseReference

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
        databaseReference = FirebaseDatabase.getInstance().getReference("Contacts")
        binding.addContactButton.setOnClickListener {
            startActivity(Intent(this@MainActivity, CreateContact::class.java))
        }
        binding.searchButton.setOnClickListener {
            if (binding.searchText.text.isNotEmpty()) {
                val searchText = binding.searchText.text.toString()
                databaseReference.child(searchText).get()
                    .addOnSuccessListener {
                    if (it.exists()) {
                        val personName = it.child("personName").value
                        val personNumberText = it.child("personNumber").value
                        binding.personName.text = personName.toString()
                        binding.personNumber.text = personNumberText.toString()
                    }else
                        Toast.makeText(this,"Contact not found",Toast.LENGTH_SHORT).show()
                }
                    .addOnFailureListener{
                        Toast.makeText(this,"Failed to fetch data",Toast.LENGTH_SHORT).show()
                    }
            }
        }


    }
}