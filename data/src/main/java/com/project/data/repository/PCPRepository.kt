package com.project.data.repository

import com.project.data.remote.response.ShortTermForecastItemResponse
import com.project.data.remote.service.ShortTermForecastService
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class PCPRepository(
    private val shortTermForecastService: ShortTermForecastService
) {
    suspend fun getPCP(
        pageNo: Int = 1,
        numOfRows: Int = 288,
        dataType: String = "JSON",
        baseDate: String = LocalDate.now().minusDays(1).format(DateTimeFormatter.ofPattern("yyyyMMdd")),  // 기준 날짜 (yyyyMMdd)
        baseTime: String = "2300",  // 기준 시간 (HHmm)
        nx: Int,                  // 예보 지점 X좌표
        ny: Int
    ): List<ShortTermForecastItemResponse>? = shortTermForecastService.getWeatherForecast(
        pageNo = pageNo,
        numOfRows = numOfRows,
        dataType = dataType,
        baseDate = baseDate,
        baseTime = baseTime,
        nx = nx,
        ny = ny
    )?.response?.body?.items?.item?.filter {
        it.category == "PTY"
    }
}