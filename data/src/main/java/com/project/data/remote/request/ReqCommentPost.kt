package com.project.data.remote.request

import com.google.gson.annotations.SerializedName

data class ReqCommentPost(
    @SerializedName("content") val content: String
)