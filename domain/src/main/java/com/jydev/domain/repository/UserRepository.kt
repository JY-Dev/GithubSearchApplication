package com.jydev.domain.repository

import com.jydev.domain.model.User

interface UserRepository {
    suspend fun getFavoriteUser() : List<User>
    suspend fun deleteFavoriteUserFromId(id : Long)
    suspend fun insertFavoriteUser(user: User)
    suspend fun getFavoriteUserByQuery(query : String) : List<User>
}