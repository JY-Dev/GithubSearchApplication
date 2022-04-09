package com.jydev.data

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.jydev.data.datasource.UserLocalDataSource
import com.jydev.data.datasource.UserLocalDataSourceImpl
import com.jydev.data.local.UserDataBase
import com.jydev.data.mock.UserLocalMockFactory
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class UserDataBaseTest {
    private lateinit var db : UserDataBase
    private lateinit var userLocalDataSource : UserLocalDataSource
    private val mockFactory = UserLocalMockFactory()

    @Before
    fun createDataSource(){
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(context,UserDataBase::class.java).build()
        userLocalDataSource = UserLocalDataSourceImpl(db,db.userFavoriteDao())
    }

    @After
    @Throws(IOException::class)
    fun closeDb(){
        db.close()
    }

    @Test
    fun getFavoriteUserEntityByDESCWhenInsertFavoriteUserEntity() = runBlocking{
        val expectFavoriteUserEntityList = mockFactory.makeFavoriteUserEntityList().sortedByDescending { it.insertTimeMil }
        expectFavoriteUserEntityList.forEach {
            userLocalDataSource.insertFavoriteUser(it)
        }
        val resultFavoriteUserEntityList = userLocalDataSource.getFavoriteUser()
        for(i in expectFavoriteUserEntityList.indices){
            assert(resultFavoriteUserEntityList[i] == expectFavoriteUserEntityList[i])
        }
        db.clearAllTables()
    }

    @Test
    fun getFavoriteUserEntityReturnEmptyListWhenInsertFavoriteUserEntityThenDeleteFavoriteUserEntity() = runBlocking {
        val favoriteUserEntity = mockFactory.makeFavoriteUserEntity()
        userLocalDataSource.insertFavoriteUser(favoriteUserEntity)
        assert(userLocalDataSource.getFavoriteUser().isNotEmpty())
        userLocalDataSource.deleteFavoriteUserFromId(favoriteUserEntity.id)
        assert(userLocalDataSource.getFavoriteUser().isEmpty())
        db.clearAllTables()
    }
}