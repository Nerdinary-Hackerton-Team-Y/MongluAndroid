package com.project.presentation.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class HomeViewModel(): ViewModel() {
    private val _uiState = MutableStateFlow<HomeUiState>(HomeUiState.init())
    val uiState = _uiState.asStateFlow()

    fun getPCP(nx: Int, ny: Int) = viewModelScope.launch {
        val now = LocalDate.now().minusDays(1)
        val baseDate = now.format(DateTimeFormatter.ofPattern("yyyyMMdd"))

//        shortTermForecastService.getWeatherForecast(
//            pageNo = 1,
//            baseDate = baseDate,
//            baseTime = "2300", //"0200", "0500", "0800", "1100", "1400", "1700", "2000", "2300")
//            nx = nx,
//            ny = ny,
//            numOfRows = 288
//        )?.response?.body?.items?.item?.filter { it.category == "PCP" }
//            .let { Log.d("pcp", it.toString()) }
    }
}