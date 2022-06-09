package com.example.api_nasaapod

import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.api_nasaapod.databinding.ItemBinding
import com.squareup.picasso.Picasso

class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {

    val binding = ItemBinding.bind(view)

    fun render(astronomyPicture: AstronomyPicture){
        binding.tvTitle.text = astronomyPicture.title
        binding.tvExplanation.text = astronomyPicture.explanation
        binding.tvHdurl.text = astronomyPicture.hdurl
        binding.tvUrl.text = astronomyPicture.url
        Picasso.get().load(astronomyPicture.hdurl).into(view.findViewById<ImageView>(R.id.iv_astronomy))

    }
}