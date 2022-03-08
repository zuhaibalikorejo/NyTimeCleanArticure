package com.android.nytime.domain.usecase.base

import com.android.nytime.domain.exception.traceErrorException
import kotlinx.coroutines.*
import java.util.concurrent.CancellationException

abstract class MostViewUseCase<Type, in Params>() where Type : Any {

    abstract suspend fun run(params: Params? = null): Type


    fun invoke(scope: CoroutineScope, params: Params?, onResult: MostViewUseCaseResponse<Type>?) {

        scope.launch {
            try {
                val result = run(params)
                onResult?.onSuccess(result)
            } catch (e: CancellationException) {
                e.printStackTrace()
                onResult?.onError(traceErrorException(e))
            } catch (e: Exception) {
                e.printStackTrace()
                onResult?.onError(traceErrorException(e))
            }
        }
    }

}