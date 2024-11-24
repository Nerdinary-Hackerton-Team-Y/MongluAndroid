package com.project.presentation.feed

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.data.repository.RepositoryFactory
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class FeedViewModel : ViewModel() {
    private val questRepository = RepositoryFactory.createQuestRepository()
    private val postRepository = RepositoryFactory.createPostRepository()

    private val _uiState = MutableStateFlow(FeedUiState.init())
    val uiState = _uiState.asStateFlow()

    init {
        getQuest()
    }

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

    fun getQuest() {
        viewModelScope.launch {
            val res = questRepository.getCurrQuest()
            _uiState.value = _uiState.value.copy(
                questStr = res?.title ?: ""
            )
        }
    }

    fun searchFeed(text: String, orderCode: Int) {
        viewModelScope.launch {
            val res = postRepository.getHashtagPost(
                orderCode = orderCode,
                hashtag = text
            )
            if (res != null) {
                _uiState.value = _uiState.value.copy(
                    feedList = res.map {
                        FeedItem(
                            id = it.id,
                            imgUrl = it.imageUrl ?: "",
                            isLike = it.isQuest,
                        )
                    }
                )
            }
        }
//        _uiState.value = _uiState.value.copy(
//            feedList = listOf(
//                FeedItem(
//                    id = 1,
//                    imgUrl = "https://www.cctimes.kr/news/photo/201312/357863_120718_5653.jpg",
//                    isLike = false,
//                ),
//                FeedItem(
//                    id = 2,
//                    imgUrl = "https://www.cctimes.kr/news/photo/201312/357863_120718_5653.jpg",
//                    isLike = true,
//                ),
//                FeedItem(
//                    id = 1,
//                    imgUrl = "https://www.cctimes.kr/news/photo/201312/357863_120718_5653.jpg",
//                    isLike = false,
//                ),
//                FeedItem(
//                    id = 1,
//                    imgUrl = "https://www.cctimes.kr/news/photo/201312/357863_120718_5653.jpg",
//                    isLike = true,
//                ),
//                FeedItem(
//                    id = 1,
//                    imgUrl = "https://www.cctimes.kr/news/photo/201312/357863_120718_5653.jpg",
//                    isLike = false,
//                ),
//                FeedItem(
//                    id = 1,
//                    imgUrl = "https://www.cctimes.kr/news/photo/201312/357863_120718_5653.jpg",
//                    isLike = true,
//                ),
//                FeedItem(
//                    id = 1,
//                    imgUrl = "https://www.cctimes.kr/news/photo/201312/357863_120718_5653.jpg",
//                    isLike = false,
//                ),
//                FeedItem(
//                    id = 1,
//                    imgUrl = "https://www.cctimes.kr/news/photo/201312/357863_120718_5653.jpg",
//                    isLike = true,
//                ),
//                FeedItem(
//                    id = 1,
//                    imgUrl = "https://www.cctimes.kr/news/photo/201312/357863_120718_5653.jpg",
//                    isLike = false,
//                ),
//                FeedItem(
//                    id = 1,
//                    imgUrl = "https://www.cctimes.kr/news/photo/201312/357863_120718_5653.jpg",
//                    isLike = true,
//                ),
//
//                )
//        )
    }
}