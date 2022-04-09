package com.jydev.githubsearchapplication.mock

import com.jydev.domain.model.User

class UserMockFactory {
    fun makeUser(id : Long) : User
        = User(id,"","")
}