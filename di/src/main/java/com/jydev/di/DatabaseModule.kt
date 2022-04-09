package com.jydev.di

import android.content.Context
import androidx.room.Room
import com.jydev.data.local.UserDataBase
import com.jydev.data.local.FavoriteUserDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Singleton
    @Provides
    fun provideAppDatabase(
        @ApplicationContext context: Context
    ): UserDataBase = Room
        .databaseBuilder(context, UserDataBase::class.java, "user.db")
        .build()

    @Singleton
    @Provides
    fun provideFavoriteUserDao(appDatabase: UserDataBase): FavoriteUserDao = appDatabase.userFavoriteDao()
}