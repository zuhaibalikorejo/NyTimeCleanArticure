package com.android.nytime.data.source.remote

import com.android.nytime.domain.model.NewsDataModel
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("mostviewed/all-sections/7.json")
    suspend fun getPosts(@Query("api-key") apiKey : Any): NewsDataModel
}