package com.jydev.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.jydev.data.model.local.FavoriteUserEntity

@Dao
interface FavoriteUserDao {
    @Query("SELECT * FROM FavoriteUser ORDER BY `insertTimeMil` DESC")
    suspend fun getFavoriteUser() : List<FavoriteUserEntity>

    @Query("DELETE FROM FavoriteUser WHERE `id` =:id")
    suspend fun deleteFavoriteUserFromId(id : Long)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavoriteUser(favoriteUserEntity: FavoriteUserEntity)

    @Query("SELECT * FROM FavoriteUser WHERE nickname LIKE :query || '%' ORDER BY `insertTimeMil` DESC")
    suspend fun getFavoriteUserByQuery(query : String) : List<FavoriteUserEntity>

}