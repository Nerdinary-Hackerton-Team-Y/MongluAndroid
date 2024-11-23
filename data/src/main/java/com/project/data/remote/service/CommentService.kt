package com.project.data.remote.service

import com.project.data.remote.request.ReqCommentPost
import com.project.data.remote.response.CommentResult
import com.project.data.remote.response.ServerResponse
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface CommentService {
    @POST("/comments")
    suspend fun postComment(
        @Body req: ReqCommentPost
    ): ServerResponse<CommentResult>

    @DELETE("/comments")
    suspend fun deleteComment(
        @Query("commentId") commentId: Int
    ): ServerResponse<String>

    @GET("/comments")
    suspend fun getComment(
        @Query("postId") postId: Int
    ): ServerResponse<List<CommentResult>>

    @GET("/comments/user")
    suspend fun getCommentForUser(
    ): ServerResponse<List<CommentResult>>
}