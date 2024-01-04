package com.example.homework_19.di

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    private const val BASE_URL_USER_MOCKY = "https://run.mocky.io/"

    private const val BASE_URL_USERINFO_REQRES = "https://reqres.in/"

    @Provides
    @Singleton
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

}