package com.example.homework_19.di

import com.example.homework_19.data.common.HandleResponse
import com.example.homework_19.data.repository.UserInfoRepositoryImpl
import com.example.homework_19.data.repository.UserRepositoryImpl
import com.example.homework_19.data.service.UserInfoService
import com.example.homework_19.data.service.UserService
import com.example.homework_19.domain.repository.UserInfoRepository
import com.example.homework_19.domain.repository.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideUserRepository(
        userService: UserService,
        handleResponse: HandleResponse
    ): UserRepository {
        return UserRepositoryImpl(
            userService = userService,
            handleResponse = handleResponse
        )
    }

    @Provides
    @Singleton
    fun provideUserInfoRepository(
        userInfoService: UserInfoService,
        handleResponse: HandleResponse
    ): UserInfoRepository {
        return UserInfoRepositoryImpl(
            userInfoService = userInfoService,
            handleResponse = handleResponse
        )
    }

}