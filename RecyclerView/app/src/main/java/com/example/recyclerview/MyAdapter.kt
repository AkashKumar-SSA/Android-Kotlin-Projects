package com.example.recyclerview

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.imageview.ShapeableImageView

class MyAdapter(private var context: Activity, private var newsArrayList : ArrayList<News>) :
RecyclerView.Adapter<MyAdapter.MyViewHolder>(){

    private lateinit var myListener: OnItemClickListener

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }
    fun setOnItemClickListener(listener: OnItemClickListener) {
        myListener = listener
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.each_row,parent,false)
        return MyViewHolder(itemView,myListener)
    }

    //populate items with data
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = newsArrayList[position]
        holder.heading.text = currentItem.newsHeading
        holder.image.setImageResource(currentItem.newsImage)

    }

    override fun getItemCount(): Int {
        return newsArrayList.size
    }

    class MyViewHolder(itemView : View , listener: OnItemClickListener) : RecyclerView.ViewHolder(itemView) {
        val heading: TextView = itemView.findViewById(R.id.newsHeading)
        val image: ShapeableImageView = itemView.findViewById(R.id.newsPic)

        init {
            itemView.setOnClickListener {
                listener.onItemClick(adapterPosition)
            }
        }
    }
}