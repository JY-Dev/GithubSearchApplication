package com.jydev.domain.usecase

import com.jydev.domain.model.User
import com.jydev.domain.repository.UserRepository
import javax.inject.Inject

class GetFavoriteUserUseCase @Inject constructor(private val userRepository: UserRepository) {
    suspend operator fun invoke() : List<User>{
        return userRepository.getFavoriteUser()
    }
}