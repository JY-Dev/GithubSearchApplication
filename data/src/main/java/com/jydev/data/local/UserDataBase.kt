package com.jydev.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.jydev.data.model.local.FavoriteUserEntity

@Database(entities = [FavoriteUserEntity::class],version = 1)
abstract class UserDataBase : RoomDatabase() {
    abstract fun userFavoriteDao() : FavoriteUserDao
}