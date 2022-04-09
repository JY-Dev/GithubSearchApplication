package com.jydev.githubsearchapplication.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.google.android.material.tabs.TabLayoutMediator
import com.jydev.githubsearchapplication.R
import com.jydev.githubsearchapplication.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding

    private val favoriteUserViewModel : FavoriteUserViewModel by viewModels()

    private val tabTextResList = listOf(R.string.api, R.string.local)
    private val pagerAdapter by lazy {
        PagerAdapter(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.setView()
        favoriteUserViewModel.getFavoriteUser()
    }

    private fun ActivityMainBinding.setView(){
        viewPager.adapter = pagerAdapter
        TabLayoutMediator(tabLayout,viewPager){ tab, position ->
            tab.text = getString(tabTextResList[position])
        }.attach()
    }

}