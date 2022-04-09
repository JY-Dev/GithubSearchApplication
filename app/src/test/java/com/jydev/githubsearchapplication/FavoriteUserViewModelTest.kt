package com.jydev.githubsearchapplication

import com.jydev.domain.usecase.DeleteFavoriteUserUseCase
import com.jydev.domain.usecase.GetFavoriteUserUseCase
import com.jydev.domain.usecase.InsertFavoriteUserUseCase
import com.jydev.githubsearchapplication.mock.FakeUserRepository
import com.jydev.githubsearchapplication.mock.UserMockFactory
import com.jydev.githubsearchapplication.ui.FavoriteUserViewModel
import org.junit.Test

class FavoriteUserViewModelTest : BaseViewModelTest() {
    private val userMockFactory = UserMockFactory()
    private val userRepository = FakeUserRepository()
    private val getFavoriteUserUseCase : GetFavoriteUserUseCase = GetFavoriteUserUseCase(userRepository)
    private val deleteFavoriteUserUseCase : DeleteFavoriteUserUseCase = DeleteFavoriteUserUseCase(userRepository)
    private val insertFavoriteUserUseCase : InsertFavoriteUserUseCase = InsertFavoriteUserUseCase(userRepository)
    private val favoriteViewModel : FavoriteUserViewModel = FavoriteUserViewModel(getFavoriteUserUseCase, deleteFavoriteUserUseCase, insertFavoriteUserUseCase)

    @Test
    fun isFavoriteUserReturnTrueWhenInsertFavoriteUser(){
        val user = userMockFactory.makeUser(0L)
        favoriteViewModel.insertFavoriteUser(user)
        assert(favoriteViewModel.isFavoriteUser(user))
        userRepository.clearUserList()
    }

    @Test
    fun favoriteUserListGetOrAwaitValueIsInsertFavoriteUserWhenInsertFavoriteUser(){
        val user = userMockFactory.makeUser(0L)
        favoriteViewModel.insertFavoriteUser(user)
        val result = favoriteViewModel.favoriteUserList.getOrAwaitValue()[0]
        assert(user == result)
    }

    @Test
    fun favoriteUserListIsEmptyWhenInsertFavoriteUserThenDeleteFavoriteUser(){
        val user = userMockFactory.makeUser(0L)
        favoriteViewModel.insertFavoriteUser(user)
        favoriteViewModel.deleteFavoriteUser(user)
        val result = favoriteViewModel.favoriteUserList.getOrAwaitValue()
        assert(result.isEmpty())
    }

    @Test
    fun getUpdateSearchUserWhenSaveSearchUserPositionThenInsertFavoriteUserThenDeleteFavoriteUser(){
        val user = userMockFactory.makeUser(0L)
        val position = 10
        favoriteViewModel.saveSearchUserPositionFromId(user,position)
        favoriteViewModel.insertFavoriteUser(user)
        favoriteViewModel.deleteFavoriteUser(user)
        val result = favoriteViewModel.updateSearchUser.getOrAwaitValue().getContentIfNotHandled()
        assert(position == result)
    }
}