package com.jydev.di

import com.jydev.domain.repository.GithubRepository
import com.jydev.domain.repository.UserRepository
import com.jydev.domain.usecase.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object UseCaseModule {

    @Provides
    @ViewModelScoped
    fun provideGetImageThumbnailPagingDataUseCase(githubRepository: GithubRepository) : GetSearchUserPagingUseCase =
        GetSearchUserPagingUseCase(githubRepository)

    @Provides
    @ViewModelScoped
    fun provideGetFavoriteUserUseCase(userRepository: UserRepository) : GetFavoriteUserUseCase =
        GetFavoriteUserUseCase(userRepository)

    @Provides
    @ViewModelScoped
    fun provideGetFavoriteUserByQueryUseCase(userRepository: UserRepository) : GetFavoriteUserByQueryUseCase =
        GetFavoriteUserByQueryUseCase(userRepository)

    @Provides
    @ViewModelScoped
    fun provideDeleteFavoriteUserUseCase(userRepository: UserRepository) : DeleteFavoriteUserUseCase =
        DeleteFavoriteUserUseCase(userRepository)

    @Provides
    @ViewModelScoped
    fun provideInsertFavoriteUserUseCase(userRepository: UserRepository) : InsertFavoriteUserUseCase =
        InsertFavoriteUserUseCase(userRepository)

}