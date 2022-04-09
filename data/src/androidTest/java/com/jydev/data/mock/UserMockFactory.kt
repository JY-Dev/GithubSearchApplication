package com.jydev.data.mock

import com.jydev.data.model.local.FavoriteUserEntity
import com.jydev.domain.model.User

class UserMockFactory {
    fun makeUserList() : List<User>{
        val list = mutableListOf<User>()
        for(i in 0 until 5){
            val id = i.toLong()
            Thread.sleep(5)
            list.add(User(id,"",""))
        }
        return list
    }

    fun makeUser(nickname : String = "") : User =
        User(0L,nickname,"")

}