package com.example.homework_19.data.common

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Response
import javax.inject.Inject

class HandleResponse @Inject constructor() {

    fun <D : Any> handleApiCall(apiCall: suspend () -> Response<D>): Flow<Resource<D>> = flow {
        try {
            emit(Resource.Loading( true))
            val response = apiCall()
            val body = response.body()
            if (response.isSuccessful && body != null) {
                emit(Resource.Success(body))
            } else {
                emit(Resource.Error(response.errorBody()?.string() ?: ""))
            }
        } catch (e: Throwable) {
            emit(Resource.Error(e.message ?: ""))
        }

        emit(Resource.Loading(false))

    }

}