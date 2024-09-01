package com.example.realtimeadmin

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.realtimeadmin.databinding.ActivityMainBinding
import com.example.realtimeadmin.databinding.ActivityUploadBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class UploadActivity : AppCompatActivity() {
    private lateinit var binding: ActivityUploadBinding
    private lateinit var databaseReference: DatabaseReference


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityUploadBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        binding.saveButton.setOnClickListener {
            val ownerName = binding.uploadOwnerName.text.toString()
            val vehicleBrand = binding.uploadVehicleBrand.text.toString()
            val vehicleRTO  = binding.uploadVehicleRTO.text.toString()
            val vehicleNumber = binding.uploadVehicleNumber.text.toString()

            if (ownerName.isNotEmpty() && vehicleBrand.isNotEmpty() &&
                vehicleRTO.isNotEmpty() && vehicleNumber.isNotEmpty()){

            databaseReference = FirebaseDatabase.getInstance().getReference("vehicles Information")
            val vehicleData = VehicleData(ownerName,vehicleBrand,vehicleRTO,vehicleNumber)
            databaseReference.child(vehicleNumber).setValue(vehicleData)
                .addOnSuccessListener {

                    binding.uploadOwnerName.text?.clear()
                    binding.uploadVehicleBrand.text?.clear()
                    binding.uploadVehicleRTO.text?.clear()
                    binding.uploadVehicleNumber.text?.clear()

                    Toast.makeText(this,"Data is stored Successfully",Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this@UploadActivity,MainActivity::class.java))
                }
                .addOnFailureListener {
                    Toast.makeText(this,"Failed to store data",Toast.LENGTH_SHORT).show()
                }
            }else{
                Toast.makeText(this,"Please all the Details",Toast.LENGTH_SHORT).show()
            }

        }
    }
}