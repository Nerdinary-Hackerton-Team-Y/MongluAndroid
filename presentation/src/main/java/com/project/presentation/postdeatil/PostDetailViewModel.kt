package com.project.presentation.postdeatil

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.data.repository.RepositoryFactory
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class PostDetailViewModel : ViewModel() {
    private val _uiState = MutableStateFlow<PostDetailUiState>(PostDetailUiState.init())
    val uiState = _uiState.asStateFlow()

    private val commentRepository = RepositoryFactory.createCommentRepository()
    private val postRepository = RepositoryFactory.createPostRepository()

    fun addComment(comment: String) = viewModelScope.launch {
        commentRepository.postComment(content = comment)
    }

    fun getComment(postId: Int) = viewModelScope.launch {
        commentRepository.getComment(postId).result?.map {
            CommentItem(
                comment = it.content,
                writer = it.nickname
            )
        }.let {
            _uiState.update { prev ->
                prev.copy(
                    commentItem = it ?: emptyList()
                )
            }
        }
    }
}