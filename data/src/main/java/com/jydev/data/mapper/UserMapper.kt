package com.jydev.data.mapper

import com.jydev.data.model.local.FavoriteUserEntity
import com.jydev.domain.model.User

fun FavoriteUserEntity.toDomain() : User =
    User(id, nickname, avatarUrl)

fun User.toEntity() : FavoriteUserEntity =
    FavoriteUserEntity(id,System.currentTimeMillis(),nickname, avatarUrl)