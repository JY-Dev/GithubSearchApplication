package com.jydev.domain.usecase

import androidx.paging.PagingData
import com.jydev.domain.model.User
import com.jydev.domain.repository.GithubRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetSearchUserPagingUseCase @Inject constructor(private val githubRepository: GithubRepository) {
    operator fun invoke(query : String) : Flow<PagingData<User>>{
        return githubRepository.getSearchUserPagingData(query)
    }
}