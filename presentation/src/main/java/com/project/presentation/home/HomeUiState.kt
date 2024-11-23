package com.project.presentation.home


data class HomeUiState(
    val PCP: String,
    val weather: WeatherEnum?,
    val region: String?,
    val challengeHonorList: List<HonorRankItem>,
    val normalHonorList: List<HonorRankItem>
){
    companion object{
        fun init() = HomeUiState(
            PCP = "",
            weather = null,
            region = null,
            challengeHonorList = listOf(),
            normalHonorList = listOf()
        )
    }
}