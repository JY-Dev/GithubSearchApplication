package com.jydev.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.jydev.data.datasource.GithubRemoteDataSource
import com.jydev.data.mapper.toDomain
import com.jydev.domain.model.User

class SearchUserPagingSource(
    private val query: String,
    private val githubRemoteDataSource: GithubRemoteDataSource
) : PagingSource<Int, User>() {
    override fun getRefreshKey(state: PagingState<Int, User>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.prevKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, User> {
        return try {
            val page = params.key ?: 1
            val searchUsers = githubRemoteDataSource.getSearchUser(query,page).toDomain()
            LoadResult.Page(
                data = searchUsers,
                prevKey = if (page == 1) null else page.minus(1),
                nextKey = if (searchUsers.isEmpty()) null else page.plus(1)
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}