package com.example.homework_19.domain.use_case

import com.example.homework_19.data.common.Resource
import com.example.homework_19.domain.model.User
import com.example.homework_19.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetUserUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    suspend operator fun invoke(): Flow<Resource<List<User>>> {
        return userRepository.getUser()
    }
}
