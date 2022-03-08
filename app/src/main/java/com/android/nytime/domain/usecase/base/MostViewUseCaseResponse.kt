package com.android.nytime.domain.usecase.base

import com.android.nytime.domain.model.ApiError

interface MostViewUseCaseResponse<Type> {

    fun onSuccess(result: Type)

    fun onError(apiError: ApiError?)
}

