package com.example.api_nasaapod

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebView
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.api_nasaapod.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: Adapter
    private val articles2 = mutableListOf<AstronomyPicture>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        getRetrofit()
        initRecyclerView()
        binding.button.setOnClickListener {
            getAstronomyPictures()
        }
    }

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://api.nasa.gov/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private fun getAstronomyPictures() {
        val startDate = binding.etdStartDate.text.toString()
        val endDate = binding.etdEndDate.text.toString()

        if (startDate.isNotEmpty() && startDate.isNotEmpty()) {
            CoroutineScope(Dispatchers.IO).launch {
                val call: Response<List<AstronomyPicture>> =
                    getRetrofit().create(AstronomyApi::class.java).getResponse(startDate, endDate)
                val astronomyPictureResponse: List<AstronomyPicture> = call.body() ?: emptyList()
                runOnUiThread {
                    if (call.isSuccessful) {
                        articles2.clear()
                        articles2.addAll(astronomyPictureResponse)
                        adapter.notifyDataSetChanged()
                    }
                }
            }
        }
    }

    private fun initRecyclerView() {
        adapter = Adapter(articles2)
        binding.recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false)
        binding.recyclerView.adapter = adapter

    }



}