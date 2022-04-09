package com.jydev.data.mock

import com.jydev.data.model.local.FavoriteUserEntity

class UserLocalMockFactory {
    fun makeFavoriteUserEntityList() : List<FavoriteUserEntity>{
        val list = mutableListOf<FavoriteUserEntity>()
        for(i in 0 until 5){
            val id = i.toLong()
            Thread.sleep(5)
            list.add(FavoriteUserEntity(id,System.currentTimeMillis(),"",""))
        }
        return list
    }

    fun makeFavoriteUserEntity() : FavoriteUserEntity =
        FavoriteUserEntity(0L,System.currentTimeMillis(),"","")
}