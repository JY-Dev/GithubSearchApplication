package com.jydev.githubsearchapplication.ui.favoriteuser.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.jydev.domain.model.User
import com.jydev.githubsearchapplication.databinding.ItemFavoriteUserBinding
import com.jydev.githubsearchapplication.ui.userDiffUtil

class FavoriteUserAdapter(private val deleteFavoriteUser : (user : User) -> Unit) : ListAdapter<User,FavoriteUserViewHolder>(userDiffUtil) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteUserViewHolder =
        FavoriteUserViewHolder(ItemFavoriteUserBinding.inflate(LayoutInflater.from(parent.context),parent,false),deleteFavoriteUser)

    override fun onBindViewHolder(holder: FavoriteUserViewHolder, position: Int) =
        holder.bind(getItem(position))
}