package com.jydev.domain.usecase

import com.jydev.domain.repository.UserRepository
import javax.inject.Inject

class DeleteFavoriteUserUseCase @Inject constructor(private val userRepository: UserRepository) {
    suspend operator fun invoke(id : Long){
        userRepository.deleteFavoriteUserFromId(id)
    }
}