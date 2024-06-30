package com.example.customisedalertbox

import android.app.Dialog
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.customisedalertbox.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var dialog: Dialog
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        dialog = Dialog(this)
        dialog.setContentView(R.layout.custom_dialog)
        //finding id for buttons of cusotom_dialog box
        val yesButton : Button = dialog.findViewById(R.id.yes_button)
        val noButton : Button = dialog.findViewById(R.id.no_button)
        val warning: TextView = dialog.findViewById(R.id.warning)
        val warningDescription : TextView = dialog.findViewById(R.id.warning_description)
        warningDescription.text = "This will delete this file permanently"
        warning.text = "Are you sure you want to delete this file?"

        yesButton.setOnClickListener {
            Toast.makeText(this,"yes button clicked",Toast.LENGTH_SHORT).show()
        }
        noButton.setOnClickListener {
            Toast.makeText(this,"no button clicked",Toast.LENGTH_SHORT).show()
            dialog.dismiss()
        }
        val openDialog : Button = findViewById(R.id.openDialog)
        openDialog.setOnClickListener {
            dialog.show()
            Toast.makeText(this,"Clicked on open dialog button",Toast.LENGTH_SHORT).show()
        }

    }
}