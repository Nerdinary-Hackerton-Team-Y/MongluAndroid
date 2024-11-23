package com.project.data.remote.response

import com.google.gson.annotations.SerializedName

data class ServerResponse<T>(
    @SerializedName("code") val code: String?,
    @SerializedName("message") val message: String?,
    @SerializedName("isSuccess") val isSuccess: Boolean?,
    @SerializedName("result") val result: T?
)

data class CommentResult(
    @SerializedName("id") val id: Int,
    @SerializedName("nickname") val nickname: String,
    @SerializedName("content") val content: String,
    @SerializedName("createdAt") val createdAt: String,
    @SerializedName("updateAt") val updateAt: String
)

data class PostResult(
    @SerializedName("postId") val postId: Int,
    @SerializedName("createdAt") val createdAt: String
)


data class PostResponse(
    @SerializedName("id") val id: Int,
    @SerializedName("imageUrl") val imageUrl: String?,
    @SerializedName("title") val title: String,
    @SerializedName("content") val content: String,
    @SerializedName("isQuest") val isQuest: Boolean,
    @SerializedName("score") val score: Int,
    @SerializedName("nickname") val nickname: String,
    @SerializedName("likeCount") val likeCount: Int
)