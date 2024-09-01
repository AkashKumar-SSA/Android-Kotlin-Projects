package com.example.e_shop_app

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.e_shop_app.databinding.ActivityMainBinding
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
        binding.shopItemDataUpload.setOnClickListener {
            val shopName = binding.shopName.text.toString()
            val shopItemName = binding.shopItemName.text.toString()
            val shopItemPrice = binding.shopItemPrice.text.toString()
            databaseReference = FirebaseDatabase.getInstance().getReference("Shop")
            val shopId = databaseReference.push().key!!
            val shop = Shop(shopId, shopName, shopItemName,R.drawable.piza ,shopItemPrice)
            databaseReference.child(shopId).setValue(shop)
        }

    }
}