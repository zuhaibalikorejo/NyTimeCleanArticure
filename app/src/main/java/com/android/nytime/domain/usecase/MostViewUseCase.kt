package com.android.nytime.domain.usecase


import com.android.nytime.domain.model.Post
import com.android.nytime.domain.repository.MostRepository
import com.android.nytime.domain.usecase.base.UseCase

class MostViewUseCase constructor(
    private val postsRepository: MostRepository
) : UseCase<List<Post>, Any?>() {

    override suspend fun run(params: Any?): List<Post> {
        return postsRepository.getPosts(params as String).results
    }

}