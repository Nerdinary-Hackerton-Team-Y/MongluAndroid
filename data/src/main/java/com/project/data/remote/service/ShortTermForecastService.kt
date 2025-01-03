package com.project.data.remote.service

import com.project.data.remote.response.ShortTermForecastResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ShortTermForecastService {
    @GET("getVilageFcst")  // 단기 예보 URL
    suspend fun getWeatherForecast(
        @Query("pageNo") pageNo: Int,
        @Query("numOfRows") numOfRows: Int,
        @Query("dataType") dataType: String = "JSON",
        @Query("base_date") baseDate: String,  // 기준 날짜 (yyyyMMdd)
        @Query("base_time") baseTime: String,  // 기준 시간 (HHmm)
        @Query("nx") nx: Int,                  // 예보 지점 X좌표
        @Query("ny") ny: Int                   // 예보 지점 Y좌표
    ): ShortTermForecastResponse
}
