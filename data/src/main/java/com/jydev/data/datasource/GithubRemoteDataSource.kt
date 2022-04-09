package com.jydev.data.datasource

import com.jydev.data.model.remote.SearchUserResponse

interface GithubRemoteDataSource {
    suspend fun getSearchUser(query : String, page : Int) : SearchUserResponse
}