package com.example.listviewproject2

import android.content.Intent
import android.os.Bundle
import android.widget.ListView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    private lateinit var userArrayList: ArrayList<User>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val name = arrayOf("Saumya","Prastuti","Ankit","Pradeep","Aman")
        val lastMsg = arrayOf("Hey wats up?","I am fine","good","Awesome","cool")
        val lastMsgTime = arrayOf("10:00","10:01","10:02","10:03","10:04")
        val phoneNumber = arrayOf("9999999999","8888888888","7777777777","6666666666","5555555555")
        val imageId = intArrayOf(R.drawable.gujjia,R.drawable.gulab_jamun,R.drawable.jalebi,R.drawable.laddu,R.drawable.malpua)
        userArrayList = ArrayList()
        for (i in name.indices){
            val user = User(name[i],lastMsg[i],lastMsgTime[i],phoneNumber[i],imageId[i])
            userArrayList.add(user)
        }
        val listView = findViewById<ListView>(R.id.listView)
        listView.adapter = MyAdapter(this,userArrayList)

        listView.setOnItemClickListener { parent, view, position, id ->
            Toast.makeText(this,"You Clicked on "+userArrayList[position].name,Toast.LENGTH_SHORT).show()
//            Toast.makeText(this,"You Clicked on "+name[position],Toast.LENGTH_SHORT).show()
            val intent = Intent(this,UserProfileData::class.java)
            intent.putExtra("name",userArrayList[position].name)
            intent.putExtra("lastMsg",userArrayList[position].lastMsg)
            intent.putExtra("lastMsgTime",userArrayList[position].lastMsgTime)
            intent.putExtra("phoneNumber",userArrayList[position].phoneNumber)
            intent.putExtra("imageId",userArrayList[position].imageId)
            startActivity(intent)
        }

    }
}