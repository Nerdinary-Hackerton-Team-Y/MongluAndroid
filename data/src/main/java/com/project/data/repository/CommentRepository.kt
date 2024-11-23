package com.project.data.repository

import com.project.data.remote.request.ReqCommentPost
import com.project.data.remote.service.CommentService

class CommentRepository(
    private val commentService: CommentService
) {

    suspend fun postComment(
        content: String
    ) = commentService.postComment(
        ReqCommentPost(
            content = content
        )
    )

    suspend fun deleteComment(
        commentId: Int
    ) = commentService.deleteComment(
        commentId
    )

    suspend fun getComment(
        postId: Int
    ) = commentService.getComment(
        postId
    )

    suspend fun getCommentForUser(
    ) = commentService.getCommentForUser()
}