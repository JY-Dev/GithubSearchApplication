package com.jydev.data.repository

import com.jydev.data.datasource.UserLocalDataSource
import com.jydev.data.mapper.toDomain
import com.jydev.data.mapper.toEntity
import com.jydev.domain.model.User
import com.jydev.domain.repository.UserRepository
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(private val userLocalDataSource: UserLocalDataSource) : UserRepository {
    override suspend fun getFavoriteUser(): List<User> =
        userLocalDataSource.getFavoriteUser().map {
            it.toDomain()
        }

    override suspend fun deleteFavoriteUserFromId(id: Long) =
        userLocalDataSource.deleteFavoriteUserFromId(id)

    override suspend fun insertFavoriteUser(user: User) =
        userLocalDataSource.insertFavoriteUser(user.toEntity())

    override suspend fun getFavoriteUserByQuery(query: String): List<User> =
        userLocalDataSource.getFavoriteUserByQuery(query).map {
            it.toDomain()
        }
}