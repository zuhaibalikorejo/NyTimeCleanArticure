package com.android.nytime.di.module

import com.android.nytime.BuildConfig
import com.android.nytime.data.repository.MostViewRepositoryImp
import com.android.nytime.data.source.remote.ApiService
import com.android.nytime.domain.repository.MostRepository
import com.android.nytime.domain.usecase.MostViewUseCase
import com.squareup.moshi.Moshi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

private const val TIME_OUT = 30L

val NetworkModule = module {

    single { createService(get()) }

    single { createRetrofit(get(), BuildConfig.BASE_URL) }

    single { createOkHttpClient() }

    single { MoshiConverterFactory.create() }

    single { Moshi.Builder().build() }

}

fun createOkHttpClient(): OkHttpClient {
    val httpLoggingInterceptor = HttpLoggingInterceptor()
    httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BASIC
    return OkHttpClient.Builder()
        .connectTimeout(TIME_OUT, TimeUnit.SECONDS)
        .readTimeout(TIME_OUT, TimeUnit.SECONDS)
        .addInterceptor(httpLoggingInterceptor).build()
}

fun createRetrofit(okHttpClient: OkHttpClient, url: String): Retrofit {
    return Retrofit.Builder()
        .baseUrl(url)
        .client(okHttpClient)
        .addConverterFactory(MoshiConverterFactory.create()).build()
}

fun createService(retrofit: Retrofit): ApiService {
    return retrofit.create(ApiService::class.java)
}

fun createPostRepository(apiService: ApiService): MostRepository {
    return MostViewRepositoryImp(apiService)
}

fun createGetPostsUseCase(postsRepository: MostRepository): MostViewUseCase {
    return MostViewUseCase(postsRepository)
}
