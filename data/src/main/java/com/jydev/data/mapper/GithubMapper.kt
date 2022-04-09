package com.jydev.data.mapper

import com.jydev.data.model.remote.SearchUserResponse
import com.jydev.domain.model.User

fun SearchUserResponse.toDomain() : List<User>
    = items.map { it.toDomain() }

fun SearchUserResponse.ItemResponse.toDomain() : User
    = User(id,login,avatar_url)