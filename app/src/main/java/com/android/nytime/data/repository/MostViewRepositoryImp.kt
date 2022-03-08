package com.android.nytime.data.repository

import com.android.nytime.data.source.remote.ApiService
import com.android.nytime.domain.model.NewsDataModel
import com.android.nytime.domain.repository.MostRepository

class MostViewRepositoryImp(private val apiService: ApiService) : MostRepository {

    override suspend fun getPosts(apiKey : Any):NewsDataModel {
        return apiService.getPosts(apiKey)
    }
}