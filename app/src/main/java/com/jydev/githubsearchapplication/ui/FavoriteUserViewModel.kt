package com.jydev.githubsearchapplication.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jydev.domain.model.User
import com.jydev.domain.usecase.DeleteFavoriteUserUseCase
import com.jydev.domain.usecase.GetFavoriteUserByQueryUseCase
import com.jydev.domain.usecase.GetFavoriteUserUseCase
import com.jydev.domain.usecase.InsertFavoriteUserUseCase
import com.jydev.githubsearchapplication.util.EventWrapper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteUserViewModel @Inject constructor(
    private val getFavoriteUserUseCase: GetFavoriteUserUseCase,
    private val deleteFavoriteUserUseCase: DeleteFavoriteUserUseCase,
    private val insertFavoriteUserUseCase: InsertFavoriteUserUseCase,
    private val getSearchUserByQueryUseCase: GetFavoriteUserByQueryUseCase
) : ViewModel() {
    val favoriteUserList : LiveData<List<User>>
        get() = _favoriteUserList
    private val _favoriteUserList = MutableLiveData<List<User>>()

    val updateSearchUser : LiveData<EventWrapper<Int>>
        get() = _updateSearchUser
    private val _updateSearchUser = MutableLiveData<EventWrapper<Int>>()

    private val userFavoriteHashMap = hashMapOf<Long, Boolean>()
    private val searchUserHashMap = hashMapOf<Long,Int>()

    fun getFavoriteUser(){
        viewModelScope.launch {
            val data = getFavoriteUserUseCase()
            data.forEach {
                userFavoriteHashMap[it.id] = true
            }
            _favoriteUserList.value = data
        }
    }

    fun getFavoriteUserByQuery(query : String){
        viewModelScope.launch {
            val data = getSearchUserByQueryUseCase(query)
            _favoriteUserList.value = data
        }
    }

    fun deleteFavoriteUser(user: User){
        viewModelScope.launch {
            val id = user.id
            userFavoriteHashMap.remove(id)
            deleteFavoriteUserUseCase(id)
            searchUserHashMap[user.id]?.let {
                _updateSearchUser.value = EventWrapper(it)
            }
            getFavoriteUser()
        }
    }

    fun insertFavoriteUser(user: User) {
        viewModelScope.launch {
            userFavoriteHashMap[user.id] = true
            insertFavoriteUserUseCase(user)
            getFavoriteUser()
        }
    }

    fun isFavoriteUser(user: User) : Boolean =
        userFavoriteHashMap[user.id]?:false

    fun saveSearchUserPositionFromId(user: User, position : Int){
        searchUserHashMap[user.id] = position
    }

    fun clearSearchUser(){
        searchUserHashMap.clear()
    }
}