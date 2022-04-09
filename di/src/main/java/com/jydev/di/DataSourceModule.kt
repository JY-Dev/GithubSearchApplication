package com.jydev.di

import com.jydev.data.datasource.GithubRemoteDataSource
import com.jydev.data.datasource.GithubRemoteDataSourceImpl
import com.jydev.data.datasource.UserLocalDataSource
import com.jydev.data.datasource.UserLocalDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModule {

    @Binds
    abstract fun bindGithubRemoteDataSource(
        githubRemoteDataSourceImpl : GithubRemoteDataSourceImpl
    ) : GithubRemoteDataSource

    @Binds
    abstract fun bindUserLocalDataSource(
        userLocalDataSourceImpl: UserLocalDataSourceImpl
    ) : UserLocalDataSource

}