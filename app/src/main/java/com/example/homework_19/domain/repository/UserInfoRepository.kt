package com.example.homework_19.domain.repository

import com.example.homework_19.data.common.Resource
import com.example.homework_19.data.dto.UserInfoDto
import kotlinx.coroutines.flow.Flow

interface UserInfoRepository {

    suspend fun getUserInfo(userId : Int) : Flow<Resource<UserInfoDto>>

}