package com.example.dialogbox

import android.content.DialogInterface
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.dialogbox.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
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

        binding.dialog1.setOnClickListener {
            val builder1 = AlertDialog.Builder(this)
            builder1.setTitle("Are you sure")
            builder1.setMessage("Do you want to close the app")
            builder1.setIcon(R.drawable.back)
            builder1.setPositiveButton("yes",DialogInterface.OnClickListener{
                dialog: DialogInterface?, which: Int ->
//                finish()
            })
                .setNegativeButton("No",DialogInterface.OnClickListener { dialog, which:Int ->
//                    Code to be executed on clicking on the no button

                })
            builder1.show()
            Toast.makeText(this,"It is dialog one",Toast.LENGTH_SHORT).show()
        }
        binding.dialog2.setOnClickListener {
            val options = arrayOf("Item 1", "Item 2", "Item 3", "Item 4", "Item 5")
            val builder2 = AlertDialog.Builder(this)
            builder2.setTitle("Select an option")
            builder2.setSingleChoiceItems(options,0,DialogInterface.OnClickListener{
                dialog: DialogInterface?, which: Int ->
                Toast.makeText(this,"You have selected ${options[which]}",Toast.LENGTH_SHORT).show()
            })
            builder2.setPositiveButton("Submit",DialogInterface.OnClickListener{
                dialog: DialogInterface?, which: Int ->
//                finish()
            })
            builder2.setNegativeButton("Decline",DialogInterface.OnClickListener { dialog, which:Int ->
//                dialog.dismiss()
                Toast.makeText(this,"You have selected cancel",Toast.LENGTH_SHORT).show()
            })
//            builder2.setItems(options) { dialog, which ->
//                // Handle item selection
//            }
            builder2.show()
        }
        binding.dialog3.setOnClickListener {
            val options = arrayOf("Item 1", "Item 2", "Item 3", "Item 4", "Item 5")
            val builder3 = AlertDialog.Builder(this)
            builder3.setTitle("Select an option")
            builder3.setMultiChoiceItems(options,null,DialogInterface.OnMultiChoiceClickListener{
                    dialog: DialogInterface?, which: Int, isChecked: Boolean ->
                Toast.makeText(this,"You have selected ${options[which]}",Toast.LENGTH_SHORT).show()
            })
            builder3.setPositiveButton("Submit",DialogInterface.OnClickListener{
                    dialog: DialogInterface?, which: Int ->
//                finish()
            })
            builder3.setNegativeButton("Decline",DialogInterface.OnClickListener { dialog, which:Int ->
//                dialog.dismiss()
                Toast.makeText(this,"You have selected cancel",Toast.LENGTH_SHORT).show()
            })
//            builder2.setItems(options) { dialog, which ->
//                // Handle item selection
//            }
            builder3.show()

        }


    }
}