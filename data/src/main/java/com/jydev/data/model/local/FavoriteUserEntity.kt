package com.jydev.data.model.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "FavoriteUser")
data class FavoriteUserEntity(
    @PrimaryKey val id : Long,
    val insertTimeMil : Long,
    val nickname : String,
    val avatarUrl : String
)