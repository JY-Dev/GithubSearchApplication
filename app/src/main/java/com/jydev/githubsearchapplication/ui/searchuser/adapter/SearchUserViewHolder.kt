package com.jydev.githubsearchapplication.ui.searchuser.adapter

import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.jydev.domain.model.User
import com.jydev.githubsearchapplication.R
import com.jydev.githubsearchapplication.databinding.ItemSearchUserBinding

class SearchUserViewHolder(
    private val binding: ItemSearchUserBinding,
    private val saveSearchUserPosition: (user : User, position : Int) -> Unit,
    private val favoriteUserStateChange: (User, Boolean) -> Unit,
    private val favoriteUserState: (user: User) -> Boolean
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(user: User?,position: Int) {
        user?.let {
            with(binding) {
                Glide.with(binding.root)
                    .load(user.avatarUrl)
                    .placeholder(R.drawable.user)
                    .into(binding.profileImageView)
                nickNameTextView.text = it.nickname
                setButtonDrawable(user)
                saveSearchUserPosition(user,position)
                favoriteButton.setOnClickListener {
                    favoriteUserStateChange(user,favoriteUserState(user).not())
                    setButtonDrawable(user)
                }
            }
        }
    }

    private fun setButtonDrawable(user: User) {
        val buttonDrawable =
            if (favoriteUserState(user)) R.drawable.selected_heart else R.drawable.unselected_heart
        binding.favoriteButton.background =
            ContextCompat.getDrawable(binding.root.context, buttonDrawable)
    }
}