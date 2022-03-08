package com.android.nytime.di.module

import com.android.nytime.presentation.news.MostViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val AppModule = module {

    viewModel { MostViewModel(get()) }

    single { createGetPostsUseCase(get()) }

    single { createPostRepository(get()) }
}