package com.example.homework_19.di

import com.example.homework_19.data.service.UserInfoService
import com.example.homework_19.data.service.UserService
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    private const val BASE_URL_USER_MOCKY = "https://run.mocky.io/"

    private const val BASE_URL_USERINFO_REQRES = "https://reqres.in/"

    @Provides
    @Singleton
    @Named("MockyRetrofit")
    fun provideMockyRetrofitClient(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL_USER_MOCKY)
            .addConverterFactory(
                MoshiConverterFactory.create(
                    Moshi.Builder()
                        .add(KotlinJsonAdapterFactory())
                        .build()
                )
            )
            .build()
    }

    @Provides
    @Singleton
    @Named("ReqresRetrofit")
    fun provideReqresRetrofitClient(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL_USERINFO_REQRES)
            .addConverterFactory(
                MoshiConverterFactory.create(
                    Moshi.Builder()
                        .add(KotlinJsonAdapterFactory())
                        .build()
                )
            )
            .build()
    }

    @Provides
    @Singleton
    fun provideMockyApiService(@Named("MockyRetrofit") retrofit: Retrofit): UserService {
        return retrofit.create(UserService::class.java)
    }

    @Provides
    @Singleton
    fun provideReqresApiService(@Named("ReqresRetrofit") retrofit: Retrofit): UserInfoService {
        return retrofit.create(UserInfoService::class.java)
    }

}