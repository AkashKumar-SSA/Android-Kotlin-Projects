package com.example.realtimeclient

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.realtimeclient.databinding.ActivityMainBinding
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
        binding.searchVehicleButton.setOnClickListener {
            val vehicleNumber = binding.searchVehicleNumber.text.toString()
            if(vehicleNumber.isNotEmpty()){
                readData(vehicleNumber)
            }else
                Toast.makeText(this,"Please enter the vehicle number",Toast.LENGTH_SHORT).show()

        }

    }
    private fun readData(vehicleNumber: String){
        databaseReference = FirebaseDatabase.getInstance().getReference("vehicles Information")
        databaseReference.child(vehicleNumber).get()
            .addOnSuccessListener {
            if(it.exists()){
                val ownerName = it.child("ownerName").value
                val vehicleBrand = it.child("vehicleBrand").value
                val vehicleRTO = it.child("vehicleRTO").value

                Toast.makeText(this,"Successfully Read the data",Toast.LENGTH_SHORT).show()
                binding.readOwnerName.text = ownerName.toString()
                binding.readVehicleBrand.text = vehicleBrand.toString()
                binding.readRTONumber.text = vehicleRTO.toString()

            }
            else
                Toast.makeText(this,"Vehicle Number does not exist",Toast.LENGTH_SHORT).show()
            }

            .addOnFailureListener{
                Toast.makeText(this,"Failed to read the data",Toast.LENGTH_SHORT).show()
            }
    }
}