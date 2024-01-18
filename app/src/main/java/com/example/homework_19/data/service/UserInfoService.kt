package com.example.homework_19.data.service

import com.example.homework_19.data.dto.UserInfoDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface UserInfoService {

    @GET("api/users/{id}")
    suspend fun getUserInfo(@Path("id") userId: Int): Response<UserInfoDto>

}