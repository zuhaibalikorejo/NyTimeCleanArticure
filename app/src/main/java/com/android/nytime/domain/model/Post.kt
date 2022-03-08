package com.android.nytime.domain.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class NewsDataModel(
    var status : String,
    var copyright : String,
    var num_results : Int,
    var results : List<Post>

)
@JsonClass(generateAdapter = true)
data class Post(
    var id: Long,
    var title: String,
    var abstract: String
)


