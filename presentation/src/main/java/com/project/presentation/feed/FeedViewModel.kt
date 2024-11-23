package com.project.presentation.feed

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class FeedViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(FeedUiState.init())
    val uiState = _uiState.asStateFlow()

    fun setSearchHashtagText(newText: String) {
        _uiState.value = _uiState.value.copy(
            searchText = newText
        )
    }

    fun setSortCode(code: Int) {
        _uiState.value = _uiState.value.copy(
            sortCode = code
        )
    }

    fun searchFeed(text: String) {
        _uiState.value = _uiState.value.copy(
            feedList = listOf(
                FeedItem(
                    id = 1,
                    imgUrl = "https://www.cctimes.kr/news/photo/201312/357863_120718_5653.jpg",
                    isLike = false,
                ),
                FeedItem(
                    id = 2,
                    imgUrl = "https://www.cctimes.kr/news/photo/201312/357863_120718_5653.jpg",
                    isLike = true,
                ),
                FeedItem(
                    id = 1,
                    imgUrl = "https://www.cctimes.kr/news/photo/201312/357863_120718_5653.jpg",
                    isLike = false,
                ),
                FeedItem(
                    id = 1,
                    imgUrl = "https://www.cctimes.kr/news/photo/201312/357863_120718_5653.jpg",
                    isLike = true,
                ),
                FeedItem(
                    id = 1,
                    imgUrl = "https://www.cctimes.kr/news/photo/201312/357863_120718_5653.jpg",
                    isLike = false,
                ),
                FeedItem(
                    id = 1,
                    imgUrl = "https://www.cctimes.kr/news/photo/201312/357863_120718_5653.jpg",
                    isLike = true,
                ),
                FeedItem(
                    id = 1,
                    imgUrl = "https://www.cctimes.kr/news/photo/201312/357863_120718_5653.jpg",
                    isLike = false,
                ),
                FeedItem(
                    id = 1,
                    imgUrl = "https://www.cctimes.kr/news/photo/201312/357863_120718_5653.jpg",
                    isLike = true,
                ),
                FeedItem(
                    id = 1,
                    imgUrl = "https://www.cctimes.kr/news/photo/201312/357863_120718_5653.jpg",
                    isLike = false,
                ),
                FeedItem(
                    id = 1,
                    imgUrl = "https://www.cctimes.kr/news/photo/201312/357863_120718_5653.jpg",
                    isLike = true,
                ),

            )
        )
    }
}