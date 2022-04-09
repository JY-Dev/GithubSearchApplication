package com.jydev.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.jydev.data.datasource.GithubRemoteDataSource
import com.jydev.data.paging.SearchUserPagingSource
import com.jydev.domain.model.User
import com.jydev.domain.repository.GithubRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GithubRepositoryImpl @Inject constructor(private val githubRemoteDataSource: GithubRemoteDataSource) : GithubRepository {
    override fun getSearchUserPagingData(query : String): Flow<PagingData<User>> =
        Pager(
            PagingConfig(pageSize = 30)
        ) {
            SearchUserPagingSource(query,githubRemoteDataSource)
        }.flow
}