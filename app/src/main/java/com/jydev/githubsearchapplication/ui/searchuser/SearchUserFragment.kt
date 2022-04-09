package com.jydev.githubsearchapplication.ui.searchuser

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.jydev.githubsearchapplication.databinding.FragmentSearchUserBinding
import com.jydev.githubsearchapplication.ui.FavoriteUserViewModel
import com.jydev.githubsearchapplication.ui.searchuser.adapter.SearchUserAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

@AndroidEntryPoint
class SearchUserFragment : Fragment() , CoroutineScope {
    override val coroutineContext: CoroutineContext
        = Dispatchers.Main

    private val searchUserViewModel : SearchUerViewModel by viewModels()
    private val favoriteUserViewModel : FavoriteUserViewModel by viewModels({requireActivity()})

    private lateinit var binding : FragmentSearchUserBinding
    private lateinit var searchUserAdapter: SearchUserAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSearchUserBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        with(binding){
            setView()
            setListener()
            observeData()
        }
    }

    private fun FragmentSearchUserBinding.setListener(){
        searchEditText.setOnEditorActionListener { textView, i, keyEvent ->
            val query = textView.text.toString()
            searchUserViewModel.getSearchUsers(query)
            favoriteUserViewModel.clearSearchUser()
            true
        }
    }

    private fun FragmentSearchUserBinding.setView(){
        setAdapter()
    }

    private fun FragmentSearchUserBinding.setAdapter(){
        searchUserAdapter = SearchUserAdapter({ user , position ->
            favoriteUserViewModel.saveSearchUserPositionFromId(user, position)
        },{ user, favoriteUserState ->
            when(favoriteUserState){
                true -> favoriteUserViewModel.insertFavoriteUser(user)
                false -> favoriteUserViewModel.deleteFavoriteUser(user)
            }
        }) {
            favoriteUserViewModel.isFavoriteUser(it)
        }
        recyclerView.adapter = searchUserAdapter
    }

    private fun observeData(){
        searchUserViewModel.observeData()
        favoriteUserViewModel.observeData()
    }

    private fun SearchUerViewModel.observeData(){
        userPagingData.observe(viewLifecycleOwner){
            launch {
                searchUserAdapter.submitData(it)
            }
        }
    }

    private fun FavoriteUserViewModel.observeData(){
        updateSearchUser.observe(viewLifecycleOwner){
            it.getContentIfNotHandled()?.let { position ->
                searchUserAdapter.notifyItemChanged(position)
            }
        }
    }
}