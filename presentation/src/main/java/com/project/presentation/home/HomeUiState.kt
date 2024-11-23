package com.project.presentation.home


data class HomeUiState(
    private val PCP: String
){
    companion object{
        fun init() = HomeUiState(
            PCP = ""
        )
    }
}