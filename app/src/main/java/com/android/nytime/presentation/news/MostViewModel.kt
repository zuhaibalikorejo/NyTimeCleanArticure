package com.android.nytime.presentation.news

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.nytime.domain.model.ApiError
import com.android.nytime.domain.model.Post
import com.android.nytime.domain.usecase.MostViewUseCase
import com.android.nytime.domain.usecase.base.MostViewUseCaseResponse
import kotlinx.coroutines.cancel


class MostViewModel constructor(private val getPostsUseCase: MostViewUseCase) : ViewModel() {

    val postsData = MutableLiveData<List<Post>>()
    val showProgressbar = MutableLiveData<Boolean>()
    val messageData = MutableLiveData<String>()

    fun getMost(apikey : Any) {
        showProgressbar.value = true
        getPostsUseCase.invoke(viewModelScope, apikey, object : MostViewUseCaseResponse<List<Post>> {
                override fun onSuccess(result: List<Post>) {
                    Log.i(TAG, "result: $result")
                    postsData.value = result
                    showProgressbar.value = false
                }

                override fun onError(apiError: ApiError?) {
                    messageData.value = apiError?.getErrorMessage()
                    showProgressbar.value = false
                }
            },
        )
    }

    override fun onCleared() {
        viewModelScope.cancel()
        super.onCleared()
    }

    companion object {
        private val TAG = MostViewModel::class.java.name
    }

}