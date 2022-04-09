package com.jydev.githubsearchapplication.ui

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.jydev.githubsearchapplication.ui.favoriteuser.FavoriteUserFragment
import com.jydev.githubsearchapplication.ui.searchuser.SearchUserFragment

class PagerAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {
    private val fragmentList = listOf(SearchUserFragment(), FavoriteUserFragment())
    override fun getItemCount(): Int =
        fragmentList.size

    override fun createFragment(position: Int): Fragment =
        fragmentList[position]
}