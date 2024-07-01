package com.example.listviewproject2

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import de.hdodenhof.circleimageview.CircleImageView

class MyAdapter (private val context: Activity, private val arrayList: ArrayList<User>) :
ArrayAdapter<User>(context, R.layout.each_row_layout, arrayList){

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
//        val view = layoutInflater.inflate(R.layout.each_row_layout, null)
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.each_row_layout, null)

        val image = view.findViewById<CircleImageView>(R.id.profile_image)
        val name = view.findViewById<TextView>(R.id.personName)
        val lastMsg = view.findViewById<TextView>(R.id.lastMessage)
        val lastMsgTime = view.findViewById<TextView>(R.id.lastMsgTime)

        image.setImageResource(arrayList[position].imageId)
        name.text = arrayList[position].name
        lastMsg.text = arrayList[position].lastMsg
        lastMsgTime.text = arrayList[position].lastMsgTime
//        return super.getView(position, convertView, parent)
        return view
    }
}