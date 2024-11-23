package com.project.data.remote.service

import com.project.data.remote.request.ReqEditPost
import com.project.data.remote.request.ReqPost
import com.project.data.remote.request.ReqCommentPost
import com.project.data.remote.response.CommentResult
import com.project.data.remote.response.ServerResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Path

interface PostService {
    @POST("/posts")
    suspend fun post(
        @Body req: ReqPost
    ): Response<String?>

    @POST("/posts/{postsId}")
    suspend fun editPost(
        @Path(value = "postsId") albumId : Int,
        @Body req: ReqEditPost
    ): Response<String?>

    @PATCH("/posts/{postsId}")
    suspend fun deletePost(
        @Path(value = "postsId") albumId : Int,
    ): Response<String?>

}