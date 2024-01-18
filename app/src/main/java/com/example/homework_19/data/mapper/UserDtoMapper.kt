package com.example.homework_19.data.mapper

import com.example.homework_19.data.dto.UserDto
import com.example.homework_19.domain.model.User

fun UserDto.toUser(): User {

    return User(
        id = id,
        email = email,
        firstName = firstName,
        lastName = lastName,
        avatar = avatar
    )

}