package com.jydev.githubsearchapplication.ui

import androidx.recyclerview.widget.DiffUtil
import com.jydev.domain.model.User

val userDiffUtil = object : DiffUtil.ItemCallback<User>() {
    override fun areItemsTheSame(oldItem: User, newItem: User): Boolean =
        oldItem.id == newItem.id


    override fun areContentsTheSame(oldItem: User, newItem: User): Boolean =
        oldItem == newItem
}