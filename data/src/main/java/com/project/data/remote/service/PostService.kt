package com.project.data.remote.service

import com.project.data.remote.request.ReqEditPost
import com.project.data.remote.request.ReqPost
import com.project.data.remote.request.ReqCommentPost
import com.project.data.remote.response.CommentResult
import com.project.data.remote.response.ResGetCurrRequestPost
import com.project.data.remote.response.PostResponse
import com.project.data.remote.response.PostResult
import com.project.data.remote.response.ServerResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface PostService {
    @POST("/posts/")
    suspend fun post(
        @Body req: ReqPost
    ): ServerResponse<PostResult?>

    @GET("/posts")
    suspend fun getPost(): List<PostResponse>

    @GET("/posts")
    suspend fun post(
        @Query("order") orderCode: Int,
        @Query("hashtag") hashtag: String
    ): Response<List<ResGetCurrRequestPost>>


    @POST("/posts/{postsId}")
    suspend fun editPost(
        @Path(value = "postsId") albumId: Int,
        @Body req: ReqEditPost
    ): Response<String?>

    @PATCH("/posts/{postsId}")
    suspend fun deletePost(
        @Path(value = "postsId") albumId: Int,
    ): Response<String?>

}