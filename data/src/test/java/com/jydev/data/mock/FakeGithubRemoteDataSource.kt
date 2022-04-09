package com.jydev.data.mock

import com.jydev.data.datasource.GithubRemoteDataSource
import com.jydev.data.model.remote.SearchUserResponse

class FakeGithubRemoteDataSource : GithubRemoteDataSource {
    private val githubMockFactory = GithubMockFactory()
    override suspend fun getSearchUser(query: String, page: Int): SearchUserResponse =
        githubMockFactory.makeSearchUserResponse()
}