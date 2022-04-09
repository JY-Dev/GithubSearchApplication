package com.jydev.domain.repository

import androidx.paging.PagingData
import com.jydev.domain.model.User
import kotlinx.coroutines.flow.Flow

interface GithubRepository {
    fun getSearchUserPagingData(query : String) : Flow<PagingData<User>>
}