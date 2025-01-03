package com.project.presentation.feed

data class FeedUiState(
    val questStr: String,
    val searchText: String,
    val feedList: List<FeedItem>,
    // sortCode 0 -> 최신순, 1 -> 인기순
    val sortCode: Int
) {
    companion object {
        fun init() = FeedUiState(
            questStr = "",
            searchText = "",
            feedList = listOf(),
            sortCode = 0
        )
    }
}