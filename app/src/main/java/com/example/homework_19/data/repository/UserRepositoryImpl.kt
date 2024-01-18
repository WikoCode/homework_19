package com.example.homework_19.data.repository

import com.example.homework_19.data.common.HandleResponse
import com.example.homework_19.data.common.Resource
import com.example.homework_19.data.dto.UserDto
import com.example.homework_19.data.mapper.toUser
import com.example.homework_19.data.service.UserService
import com.example.homework_19.domain.model.User
import com.example.homework_19.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val userService: UserService,
    private val handleResponse: HandleResponse
) : UserRepository {

    override suspend fun getUser(): Flow<Resource<List<User>>> {
        return handleResponse.handleApiCall {
            userService.getUser()
        }.map { resource ->
            when (resource) {
                is Resource.Success -> {

                    Resource.Success(resource.data?.map { it.toUser() })
                }
                is Resource.Error -> {
                    Resource.Error(resource.errorMessage)
                }
                is Resource.Loading -> {
                    Resource.Loading(resource.loading)
                }
            }
        }
    }
}

