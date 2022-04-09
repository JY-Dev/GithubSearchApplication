package com.jydev.githubsearchapplication.ui.favoriteuser.adapter

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.jydev.domain.model.User
import com.jydev.githubsearchapplication.R
import com.jydev.githubsearchapplication.databinding.ItemFavoriteUserBinding

class FavoriteUserViewHolder(private val binding : ItemFavoriteUserBinding, private val deleteFavoriteUser : (user : User) -> Unit) : RecyclerView.ViewHolder(binding.root) {
    fun bind(user : User?){
        user?.let {
            with(binding) {
                Glide.with(binding.root)
                    .load(user.avatarUrl)
                    .placeholder(R.drawable.user)
                    .into(binding.profileImageView)
                nickNameTextView.text = it.nickname
                deleteButton.setOnClickListener {
                    deleteFavoriteUser(user)
                }
            }
        }
    }
}