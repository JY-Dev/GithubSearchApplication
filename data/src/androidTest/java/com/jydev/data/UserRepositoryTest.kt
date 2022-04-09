package com.jydev.data

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.jydev.data.datasource.UserLocalDataSource
import com.jydev.data.datasource.UserLocalDataSourceImpl
import com.jydev.data.local.UserDataBase
import com.jydev.data.mock.UserMockFactory
import com.jydev.data.repository.UserRepositoryImpl
import com.jydev.domain.repository.UserRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class UserRepositoryTest {
    private lateinit var db : UserDataBase
    private lateinit var userLocalDataSource : UserLocalDataSource
    private lateinit var userRepository : UserRepository
    private val mockFactory = UserMockFactory()

    @Before
    fun createDataSource(){
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(context, UserDataBase::class.java).build()
        userLocalDataSource = UserLocalDataSourceImpl(db,db.userFavoriteDao())
        userRepository = UserRepositoryImpl(userLocalDataSource)
    }

    @After
    @Throws(IOException::class)
    fun closeDb(){
        db.close()
    }

    @Test
    fun getFavoriteUserByDESCWhenInsertUser() = runBlocking {
        val userList = mockFactory.makeUserList()
        userList.forEach {
            userRepository.insertFavoriteUser(it)
            delay(5)
        }
        val expectResult = userList.reversed()
        val result = userRepository.getFavoriteUser()
        for(i in userList.indices){
            assert(expectResult[i] == result[i])
        }
    }

    @Test
    fun getFavoriteUserReturnEmptyListWhenInsertFavoriteUserThenDeleteFavoriteUser() = runBlocking {
        val favoriteUser = mockFactory.makeUser()
        userRepository.insertFavoriteUser(favoriteUser)
        assert(userRepository.getFavoriteUser().isNotEmpty())
        userRepository.deleteFavoriteUserFromId(favoriteUser.id)
        assert(userRepository.getFavoriteUser().isEmpty())
        db.clearAllTables()
    }

    @Test
    fun getFavoriteUserSearchQueryByWhenInsertUser() = runBlocking {
        val nickname = "고라니"
        val user = mockFactory.makeUser(nickname)
        userRepository.insertFavoriteUser(user)
        val result = userRepository.getFavoriteUserByQuery(nickname)[0]
        assert(user == result)
        db.clearAllTables()
    }
}