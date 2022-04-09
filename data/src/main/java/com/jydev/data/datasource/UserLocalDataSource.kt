package com.jydev.data.datasource

import com.jydev.data.model.local.FavoriteUserEntity

interface UserLocalDataSource {
    suspend fun getFavoriteUser() : List<FavoriteUserEntity>
    suspend fun deleteFavoriteUserFromId(id : Long)
    suspend fun insertFavoriteUser(favoriteUserEntity: FavoriteUserEntity)
    suspend fun getFavoriteUserByQuery(query : String) : List<FavoriteUserEntity>
}