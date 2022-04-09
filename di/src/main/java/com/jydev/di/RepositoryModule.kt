package com.jydev.di

import com.jydev.data.repository.GithubRepositoryImpl
import com.jydev.data.repository.UserRepositoryImpl
import com.jydev.domain.repository.GithubRepository
import com.jydev.domain.repository.UserRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun bindGithubRepository(
        githubRepositoryImpl : GithubRepositoryImpl
    ) : GithubRepository

    @Binds
    abstract fun bindUserRepository(
        userRepositoryImpl: UserRepositoryImpl
    ) : UserRepository
}