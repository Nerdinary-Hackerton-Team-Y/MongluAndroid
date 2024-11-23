package com.project.data.remote.request

import com.google.gson.annotations.SerializedName

data class ReqEditPost(
    @SerializedName("title") val title: String,
    @SerializedName("content") val content: String,
    @SerializedName("imageUrl") val imageUrl: String,
    @SerializedName("hashtags") val hashtags: List<String>,
)