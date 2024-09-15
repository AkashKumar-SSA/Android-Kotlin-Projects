package com.example.contacts

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.contacts.databinding.ActivityCreateContactBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.coroutines.joinAll

class CreateContact : AppCompatActivity() {
    private lateinit var binding: ActivityCreateContactBinding
    private lateinit var databaseReference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityCreateContactBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.addPersonContactButton.setOnClickListener {
            val personName = binding.addPersonContactName.text.toString()
            val personNumber = binding.addPersonContactNumber.text.toString()

            if(personName.isNotEmpty() && personNumber.isNotEmpty()){
                databaseReference = FirebaseDatabase.getInstance().getReference("Contacts")
                val person = Contact(personName,personNumber)
                databaseReference.child(personName).setValue(person)
                    .addOnSuccessListener {
                        Toast.makeText(this,"Data saved Successfully",Toast.LENGTH_SHORT).show()
                        binding.addPersonContactName.text.clear()
                        binding.addPersonContactNumber.text.clear()
                    }
                    .addOnFailureListener {
                        Toast.makeText(this,"Failed to save data",Toast.LENGTH_SHORT).show()
                    }
            }else
                Toast.makeText(this,"Fill all the details",Toast.LENGTH_SHORT).show()

        }


    }
}

data class Contact(val personName: String, val personNumber: String) {

}
