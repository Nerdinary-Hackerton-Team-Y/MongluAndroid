package com.project.presentation.postdeatil

data class ContentItem(
    val iconImg: String,
    val contentImg: String,
    val writer: String,
    val title: String,
    val content: String,
    val tag: List<String>
)