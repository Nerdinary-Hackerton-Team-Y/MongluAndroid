package com.project.presentation.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.data.repository.RepositoryFactory
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.time.LocalDateTime

class HomeViewModel() : ViewModel() {
    private val _uiState = MutableStateFlow<HomeUiState>(HomeUiState.init())
    val uiState = _uiState.asStateFlow()

    private val pcpRepository = RepositoryFactory.createPCPRepository()

    init {
        getChallengeHonor()
    }
    fun getWeatherState(nx: Int, ny: Int) = viewModelScope.launch {
        val nowHourStr = LocalDateTime.now().run {
            "${hour}00"
        }
        val data = pcpRepository.getPCP(nx = nx, ny = ny)?.firstOrNull { it.fcstTime == nowHourStr}
        val weather = when(data?.value){

            else -> null
        }
        _uiState.value = _uiState.value.copy(
            weather = weather
        )
    }

    private fun getChallengeHonor() = viewModelScope.launch {
        _uiState.value = _uiState.value.copy(
            challengeHonorList = listOf(
                HonorRankItem(
                    id = 1,
                    nickname = "닉네임입니다!",
                    title = "제목입니다제목입니다",
                    rank = 1,
                    likeCount = 700,
                    imgUrl = "https://www.cctimes.kr/news/photo/201312/357863_120718_5653.jpg",
                ),
                HonorRankItem(
                    id = 2,
                    nickname = "닉네임입니다!",
                    title = "제목입니다제목입니다",
                    rank = 1,
                    likeCount = 700,
                    imgUrl = "https://www.cctimes.kr/news/photo/201312/357863_120718_5653.jpg",
                ),
                HonorRankItem(
                    id = 3,
                    nickname = "닉네임입니다!",
                    title = "제목입니다제목입니다",
                    rank = 1,
                    likeCount = 700,
                    imgUrl = "https://www.cctimes.kr/news/photo/201312/357863_120718_5653.jpg",
                ),
                HonorRankItem(
                    id = 4,
                    nickname = "닉네임입니다!",
                    title = "제목입니다제목입니다",
                    rank = 1,
                    likeCount = 700,
                    imgUrl = "https://www.cctimes.kr/news/photo/201312/357863_120718_5653.jpg",
                ),
            ),
            normalHonorList = listOf(
                HonorRankItem(
                    id = 5,
                    nickname = "닉네임입니다!",
                    title = "제목입니다제목입니다",
                    rank = 1,
                    likeCount = 700,
                    imgUrl = "https://www.cctimes.kr/news/photo/201312/357863_120718_5653.jpg",
                ),
                HonorRankItem(
                    id = 6,
                    nickname = "닉네임입니다!",
                    title = "제목입니다제목입니다",
                    rank = 1,
                    likeCount = 700,
                    imgUrl = "https://www.cctimes.kr/news/photo/201312/357863_120718_5653.jpg",
                ),
                HonorRankItem(
                    id = 7,
                    nickname = "닉네임입니다!",
                    title = "제목입니다제목입니다",
                    rank = 1,
                    likeCount = 700,
                    imgUrl = "https://www.cctimes.kr/news/photo/201312/357863_120718_5653.jpg",
                ),
                HonorRankItem(
                    id = 8,
                    nickname = "닉네임입니다!",
                    title = "제목입니다제목입니다",
                    rank = 1,
                    likeCount = 700,
                    imgUrl = "https://www.cctimes.kr/news/photo/201312/357863_120718_5653.jpg",
                ),
            )
        )
    }
}