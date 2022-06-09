package com.example.api_nasaapod

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class Adapter(val listItems: List<AstronomyPicture>): RecyclerView.Adapter<ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater= LayoutInflater.from(parent.context)
        return ViewHolder(layoutInflater.inflate(R.layout.item,parent,false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.render(listItems[position])
    }

    override fun getItemCount(): Int {
        return listItems.size
    }
}
