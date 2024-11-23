package com.project.presentation.postregister

import android.net.Uri
import java.net.URI

data class PostRegisterUiState(
    val challenge: Boolean,
    val img: Uri?,
    val title: String,
    val content: String,
    val tagList: List<String>
) {
    companion object {
        fun init() = PostRegisterUiState(
            challenge = false,
            img = null,
            title = "",
            content = "",
            tagList = emptyList()
        )
    }
}