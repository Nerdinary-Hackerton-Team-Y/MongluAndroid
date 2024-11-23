package com.project.presentation.mypage

import android.util.Log
import androidx.lifecycle.ViewModel
import com.project.presentation.feed.FeedItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class MyPageViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(MyPageUiState.init())
    val uiState = _uiState.asStateFlow()

    init {
        getMyPost()
        getReviewedPost()
    }

    fun getMyPost() {
        _uiState.value = _uiState.value.copy(
            myPostList = listOf(
                FeedItem(
                    id = 1,
                    imgUrl = "https://www.cctimes.kr/news/photo/201312/357863_120718_5653.jpg",
                    isLike = false,
                ),
                FeedItem(
                    id = 1,
                    imgUrl = "https://www.cctimes.kr/news/photo/201312/357863_120718_5653.jpg",
                    isLike = false,
                ),
                FeedItem(
                    id = 1,
                    imgUrl = "https://www.cctimes.kr/news/photo/201312/357863_120718_5653.jpg",
                    isLike = false,
                ),
                FeedItem(
                    id = 1,
                    imgUrl = "https://www.cctimes.kr/news/photo/201312/357863_120718_5653.jpg",
                    isLike = false,
                ),
                FeedItem(
                    id = 1,
                    imgUrl = "https://www.cctimes.kr/news/photo/201312/357863_120718_5653.jpg",
                    isLike = false,
                ),
                FeedItem(
                    id = 1,
                    imgUrl = "https://www.cctimes.kr/news/photo/201312/357863_120718_5653.jpg",
                    isLike = false,
                ),
                FeedItem(
                    id = 1,
                    imgUrl = "https://www.cctimes.kr/news/photo/201312/357863_120718_5653.jpg",
                    isLike = false,
                ),
                FeedItem(
                    id = 1,
                    imgUrl = "https://www.cctimes.kr/news/photo/201312/357863_120718_5653.jpg",
                    isLike = false,
                ),
                FeedItem(
                    id = 1,
                    imgUrl = "https://www.cctimes.kr/news/photo/201312/357863_120718_5653.jpg",
                    isLike = false,
                ),

                )
        )
    }

    fun getReviewedPost() {
        _uiState.value = _uiState.value.copy(
            reviewedList = listOf(
                FeedItem(
                    id = 1,
                    imgUrl = "https://img.freepik.com/premium-vector/vector-illustration-cute-snowman-cartoon-waving-isolated-white-background_769891-62.jpg",
                    isLike = false,
                ),
                FeedItem(
                    id = 1,
                    imgUrl = "https://img.freepik.com/premium-vector/vector-illustration-cute-snowman-cartoon-waving-isolated-white-background_769891-62.jpg",
                    isLike = false,
                ),
                FeedItem(
                    id = 1,
                    imgUrl = "https://img.freepik.com/premium-vector/vector-illustration-cute-snowman-cartoon-waving-isolated-white-background_769891-62.jpg",
                    isLike = false,
                ),
                FeedItem(
                    id = 1,
                    imgUrl = "https://img.freepik.com/premium-vector/vector-illustration-cute-snowman-cartoon-waving-isolated-white-background_769891-62.jpg",
                    isLike = false,
                ),
                FeedItem(
                    id = 1,
                    imgUrl = "https://img.freepik.com/premium-vector/vector-illustration-cute-snowman-cartoon-waving-isolated-white-background_769891-62.jpg",
                    isLike = false,
                ),
                FeedItem(
                    id = 1,
                    imgUrl = "https://img.freepik.com/premium-vector/vector-illustration-cute-snowman-cartoon-waving-isolated-white-background_769891-62.jpg",
                    isLike = false,
                ),
                FeedItem(
                    id = 1,
                    imgUrl = "https://img.freepik.com/premium-vector/vector-illustration-cute-snowman-cartoon-waving-isolated-white-background_769891-62.jpg",
                    isLike = false,
                ),
            )
        )
    }
}