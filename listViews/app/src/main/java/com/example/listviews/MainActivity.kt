package com.example.listviews

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.listviews.databinding.ActivityMainBinding

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
        val taskList = arrayListOf<String>()
        taskList.add("data a")
        taskList.add("data b")
        taskList.add("data c")
        taskList.add("data d")
        taskList.add("data d")
        taskList.add("data e")
        taskList.add("data f")
        taskList.add("data g")
        taskList.add("data h")

//        val listView : ListView = findViewById(R.id.listView)
        val listAdapter = ArrayAdapter(this,android.R.layout.simple_list_item_1,taskList)
        binding.listView.adapter = listAdapter

        binding.listView.setOnItemClickListener { parent, view, position, id ->
            val text = "Clicked on " + (view as TextView).text.toString()
//            Toast.makeText(this,text,Toast.LENGTH_SHORT).show()
//            Toast.makeText(this,"This is id: $id",Toast.LENGTH_SHORT).show()
//            Toast.makeText(this,"this is position number : $position",Toast.LENGTH_SHORT).show()
            Toast.makeText(this,"This is parent :$view",Toast.LENGTH_SHORT).show()
            (view as TextView).setText("This is clicked")


        }


//        val header:TextView = findViewById(R.id.header)
        binding.header.setOnClickListener {
            Toast.makeText(this,"clicked on header of the list",Toast.LENGTH_SHORT).show()
        }


    }
}