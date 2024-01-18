package com.example.homework_19.data.repository

import com.example.homework_19.data.common.HandleResponse
import com.example.homework_19.data.common.Resource
import com.example.homework_19.data.dto.UserInfoDto
import com.example.homework_19.data.service.UserInfoService
import com.example.homework_19.domain.repository.UserInfoRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UserInfoRepositoryImpl @Inject constructor(
    private val userInfoService: UserInfoService,
    private val handleResponse: HandleResponse
) : UserInfoRepository {

    override suspend fun getUserInfo(userId: Int): Flow<Resource<UserInfoDto>> {
        return handleResponse.handleApiCall {
            userInfoService.getUserInfo(userId)
        }
    }

}
