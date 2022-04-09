package com.jydev.data.datasource

import com.jydev.data.model.remote.SearchUserResponse
import com.jydev.data.remote.GithubApi
import javax.inject.Inject

class GithubRemoteDataSourceImpl @Inject constructor(private val githubApi: GithubApi) : GithubRemoteDataSource {
    override suspend fun getSearchUser(query: String, page: Int): SearchUserResponse {
        return githubApi.getSearchUsers(query, page)
    }
}