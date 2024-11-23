package com.project.presentation.postregister

import android.net.Uri
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class PostRegisterViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(PostRegisterUiState.init())
    val uiState = _uiState.asStateFlow()

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

    fun postContent(){}
}

enum class UpdateEtType {
    TITLE, CONTENT, TAG
}