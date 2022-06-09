package com.example.api_nasaapod

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface AstronomyApi {

    @GET("planetary/apod?api_key=vpAX7vsbIlqxzKOsW7wrRenDkFnTvDdG05T80IZw")
    suspend fun getResponse(@Query("start_date") startDate: String,
                            @Query("end_date") endDate: String): Response<List<AstronomyPicture>>

}