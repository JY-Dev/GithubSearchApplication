package com.jydev.data.datasource

import androidx.room.withTransaction
import com.jydev.data.local.UserDataBase
import com.jydev.data.local.FavoriteUserDao
import com.jydev.data.model.local.FavoriteUserEntity
import javax.inject.Inject

class UserLocalDataSourceImpl @Inject constructor(
    private val database: UserDataBase,
    private val favoriteUserDao: FavoriteUserDao
) :
    UserLocalDataSource {
    override suspend fun getFavoriteUser(): List<FavoriteUserEntity> =
        database.withTransaction {
            favoriteUserDao.getFavoriteUser()
        }

    override suspend fun deleteFavoriteUserFromId(id: Long) =
        database.withTransaction {
            favoriteUserDao.deleteFavoriteUserFromId(id)
        }

    override suspend fun insertFavoriteUser(favoriteUserEntity: FavoriteUserEntity) =
        database.withTransaction {
            favoriteUserDao.insertFavoriteUser(favoriteUserEntity)
        }

    override suspend fun getFavoriteUserByQuery(query: String): List<FavoriteUserEntity> =
        database.withTransaction {
            favoriteUserDao.getFavoriteUserByQuery(query)
        }

}