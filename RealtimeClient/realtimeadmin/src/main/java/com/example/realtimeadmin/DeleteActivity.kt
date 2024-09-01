package com.example.realtimeadmin

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.realtimeadmin.databinding.ActivityDeleteBinding
import com.example.realtimeadmin.databinding.ActivityUpdateBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class DeleteActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDeleteBinding
    private lateinit var databaseReference: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityDeleteBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        binding.vehicleDataDeleteButton.setOnClickListener {
            if (binding.vehicleNumber.text.isNotEmpty()){
                val vehicleNumber = binding.vehicleNumber.text.toString()
                deleteData(vehicleNumber = vehicleNumber)
            }else{
                Toast.makeText(this,"Vehicle Number can not be empty",Toast.LENGTH_SHORT).show()

            }
        }
    }

    private fun deleteData(vehicleNumber: String) {
        databaseReference = FirebaseDatabase.getInstance().getReference("vehicles Information")
        databaseReference.child(vehicleNumber).removeValue()
            .addOnSuccessListener {
                Toast.makeText(this,"Vehicle data successfully Removed",Toast.LENGTH_SHORT).show()
                startActivity(Intent(this@DeleteActivity,MainActivity::class.java))
                finish()
            }
            .addOnFailureListener{
                Toast.makeText(this,"Vehicle Number do not Exist",Toast.LENGTH_SHORT).show()
            }


    }
}