package com.jydev.data

import androidx.paging.PagingSource
import com.jydev.data.datasource.GithubRemoteDataSource
import com.jydev.data.mapper.toDomain
import com.jydev.data.mock.FakeGithubRemoteDataSource
import com.jydev.data.mock.GithubMockFactory
import com.jydev.data.paging.SearchUserPagingSource
import kotlinx.coroutines.runBlocking
import org.junit.Test

class SearchUserPagingSourceTest {
    private val githubRemoteDataSource: GithubRemoteDataSource = FakeGithubRemoteDataSource()
    private val mockFactory: GithubMockFactory = GithubMockFactory()

    @Test
    fun loadReturnsPageWhenOnSuccessfulLoadOfItemKeyedData() = runBlocking {
        val pagingSource = SearchUserPagingSource("", githubRemoteDataSource)
        val searchUserList = mockFactory.makeSearchUserResponse().toDomain()
        val expectResult  = PagingSource.LoadResult.Page(
            data = searchUserList,
            prevKey = null,
            nextKey = 2
        )
        val result = pagingSource.load(
            PagingSource.LoadParams.Refresh(
                key = null,
                loadSize = 5,
                placeholdersEnabled = false
            )
        )
        assert(result == expectResult)
    }
}