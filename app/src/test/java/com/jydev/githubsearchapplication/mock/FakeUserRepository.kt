package com.jydev.githubsearchapplication.mock

import com.jydev.domain.model.User
import com.jydev.domain.repository.UserRepository

class FakeUserRepository : UserRepository {
    private val userList = mutableListOf<User>()
    override suspend fun getFavoriteUser(): List<User> =
        userList

    override suspend fun deleteFavoriteUserFromId(id: Long) {
        userList.remove(userList.find { it.id == id })
    }

    override suspend fun insertFavoriteUser(user: User) {
        userList.add(user)
    }

    fun clearUserList(){
        userList.clear()
    }
}