package com.jydev.githubsearchapplication.ui.searchuser.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import com.jydev.domain.model.User
import com.jydev.githubsearchapplication.databinding.ItemSearchUserBinding
import com.jydev.githubsearchapplication.ui.userDiffUtil

class SearchUserAdapter(
    private val saveSearchUserPosition: (user: User, position : Int) -> Unit,
    private val favoriteUserStateChange: (User, Boolean) -> Unit,
    private val favoriteUserState: (user: User) -> Boolean
) : PagingDataAdapter<User, SearchUserViewHolder>(userDiffUtil) {
    override fun onBindViewHolder(holder: SearchUserViewHolder, position: Int) =
        holder.bind(getItem(position),position)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchUserViewHolder =
        SearchUserViewHolder(
            ItemSearchUserBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ), saveSearchUserPosition, favoriteUserStateChange, favoriteUserState
        )
}