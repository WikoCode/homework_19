package com.example.homework_19.domain.use_case

import com.example.homework_19.data.common.Resource
import com.example.homework_19.data.dto.UserInfoDto
import com.example.homework_19.domain.repository.UserInfoRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetUserInfoUseCase @Inject constructor(
    private val userInfoRepository: UserInfoRepository
) {
    suspend operator fun invoke(userId: Int): Flow<Resource<UserInfoDto>> {
        return userInfoRepository.getUserInfo(userId)
    }
}
