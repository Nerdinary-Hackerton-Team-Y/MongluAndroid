package com.project.presentation.postregister

import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.data.remote.request.ReqPost
import com.project.data.repository.RepositoryFactory
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class PostRegisterViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(PostRegisterUiState.init())
    val uiState = _uiState.asStateFlow()

    private val postRepository = RepositoryFactory.createPostRepository()

    fun updateEtUiState(type: UpdateEtType, data: String) {
        when (type) {
            UpdateEtType.TITLE -> {
                _uiState.update { prev ->
                    prev.copy(
                        title = data
                    )
                }
            }

            UpdateEtType.CONTENT -> {
                _uiState.update { prev ->
                    prev.copy(
                        content = data
                    )
                }
            }

            UpdateEtType.TAG -> {
            }
        }
    }

    fun updateImgUri(uri: Uri) {
        _uiState.update { prev ->
            prev.copy(
                img = uri
            )
        }
    }

    fun postContent(clear: () -> Unit) = viewModelScope.launch {
        try {
            uiState.value.let {
                postRepository.post(
                    ReqPost(
                        title = it.title,
                        content = it.content,
                        imageUrl = "",
                        isQuest = it.challenge,
                        questId = 1,
                        hashtags = listOf("string")
                    )
                )
            }
        }catch (e:Exception){
            e.printStackTrace()
        }
    }
}

enum class UpdateEtType {
    TITLE, CONTENT, TAG
}