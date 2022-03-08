package com.android.nytime.domain.usecase


import com.android.nytime.domain.model.Post
import com.android.nytime.domain.repository.MostRepository
import com.android.nytime.domain.usecase.base.MostViewUseCase

class MostViewUseCase constructor(
    private val postsRepository: MostRepository
) : MostViewUseCase<List<Post>, Any?>() {

    override suspend fun run(params: Any?): List<Post> {
        return postsRepository.getPosts(params as String).results
    }

}