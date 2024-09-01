package com.example.realtimeadmin

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.realtimeadmin.databinding.ActivityUpdateBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class UpdateActivity : AppCompatActivity() {
    private lateinit var binding: ActivityUpdateBinding
    private lateinit var databaseReference: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityUpdateBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        binding.vehicleDataUpdateButton.setOnClickListener {
            val vehicleNumber = binding.vehicleNumber.text.toString()
            val ownerName = binding.vehicleOwnerName.text.toString()
            val brandName = binding.vehicleBrandName.text.toString()
            val vehicleRTO = binding.vehicleRTO.text.toString()

            if (vehicleNumber.isNotEmpty() && ownerName.isNotEmpty() && brandName.isNotEmpty() && vehicleRTO.isNotEmpty()){
                updateData(vehicleNumber = vehicleNumber,ownerName = ownerName,
                    vehicleBrand= brandName,vehicleRTO = vehicleRTO)
            }else
                Toast.makeText(this,"Fill all the Details",Toast.LENGTH_LONG).show()

        }
    }
    private fun updateData(vehicleNumber: String,ownerName: String,vehicleBrand: String,vehicleRTO: String){
        databaseReference = FirebaseDatabase.getInstance().getReference("vehicles Information")
        val vehicleData = mapOf<String,String>("ownerName" to ownerName, "vehicleBrand" to vehicleBrand,"vehicleRTO" to vehicleRTO)
        databaseReference.child(vehicleNumber).updateChildren(vehicleData)
            .addOnSuccessListener {
                binding.vehicleNumber.text.clear()
                binding.vehicleOwnerName.text.clear()
                binding.vehicleBrandName.text.clear()
                binding.vehicleRTO.text.clear()
                Toast.makeText(this,"Successfully Updated",Toast.LENGTH_SHORT).show()
                startActivity(Intent(this@UpdateActivity,MainActivity::class.java))

            }
            .addOnFailureListener{
                Toast.makeText(this,"Failed to Update",Toast.LENGTH_SHORT).show()
            }
    }
}