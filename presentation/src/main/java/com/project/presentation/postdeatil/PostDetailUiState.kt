package com.project.presentation.postdeatil

data class PostDetailUiState(
    val contentItem: ContentItem?,
val commentItem: List<CommentItem>,
val isMine: Boolean
){
    companion object {
        fun init() = PostDetailUiState(
            contentItem = null,
            commentItem = emptyList(),
            isMine = false
        )
    }
}
