package com.project.presentation.mypage

import com.project.presentation.feed.FeedItem

data class MyPageUiState(
    val myPostList: List<FeedItem>,
    val reviewedList: List<FeedItem>,
) {
    companion object {
        fun init() = MyPageUiState(
            myPostList = listOf(),
            reviewedList = listOf()
        )
    }
}