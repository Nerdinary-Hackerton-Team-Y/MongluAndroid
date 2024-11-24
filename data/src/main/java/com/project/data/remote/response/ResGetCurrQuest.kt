package com.project.data.remote.response

import com.google.gson.annotations.SerializedName

data class ResGetCurrQuest(
    @SerializedName("id") val id: Int,
    @SerializedName("title") val title: String,
    @SerializedName("posts") val posts: List<ResGetCurrRequestPost>,
)

data class ResGetCurrRequestPost(
    @SerializedName("id") val id: Int,
    @SerializedName("imageUrl") val imageUrl: String?,
    @SerializedName("title") val title: String,
    @SerializedName("content") val content: String,
    @SerializedName("isQuest") val isQuest: Boolean,
    @SerializedName("score") val score: Int,
    @SerializedName("likeCount") val likeCount: Int,
)
