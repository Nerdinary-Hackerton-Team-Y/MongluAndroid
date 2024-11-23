package com.project.data.repository

import com.project.data.remote.request.ReqEditPost
import com.project.data.remote.request.ReqPost
import com.project.data.remote.service.PostService
import retrofit2.Response

class PostRepository(
    private val postService: PostService
) {

    suspend fun post(
        req: ReqPost
    ) = postService.post(req)

    suspend fun getPost() = postService.getPost()

    suspend fun editPost(
        albumId: Int,
        req: ReqEditPost
    ): Response<String?> = postService.editPost(
        albumId,
        req
    )

    suspend fun deletePost(
        albumId: Int,
    ): Response<String?> = postService.deletePost(
        albumId
    )
}