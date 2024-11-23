package com.project.presentation.postdeatil

sealed interface PostDetailUiState {
    data class NormalUiState(
        val contentItem: ContentItem,
        val commentItem: List<CommentItem>,
        val isMine: Boolean
    ) : PostDetailUiState

    data object Init : PostDetailUiState
}