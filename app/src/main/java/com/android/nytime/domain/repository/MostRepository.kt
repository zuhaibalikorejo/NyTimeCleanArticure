package com.android.nytime.domain.repository

import com.android.nytime.domain.model.NewsDataModel

interface MostRepository {

    suspend fun getPosts(apiKey : Any): NewsDataModel
}