package com.jydev.githubsearchapplication.ui.searchuser

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.jydev.domain.model.User
import com.jydev.domain.usecase.GetSearchUserPagingUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchUerViewModel @Inject constructor(private val getSearchUserPagingUseCase: GetSearchUserPagingUseCase) : ViewModel() {
    private val _searchUserPagingData = MutableLiveData<PagingData<User>>()
    val userPagingData : LiveData<PagingData<User>>
        get() = _searchUserPagingData

    fun getSearchUsers(query : String){
        viewModelScope.launch{
            getSearchUserPagingUseCase(query).collect {
                _searchUserPagingData.value = it
            }
        }
    }
}