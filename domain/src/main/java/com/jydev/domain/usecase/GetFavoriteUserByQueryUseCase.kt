package com.jydev.domain.usecase

import com.jydev.domain.model.User
import com.jydev.domain.repository.UserRepository
import javax.inject.Inject

class GetFavoriteUserByQueryUseCase @Inject constructor(private val userRepository: UserRepository) {
    suspend operator fun invoke(query : String) : List<User> =
        userRepository.getFavoriteUserByQuery(query)
}