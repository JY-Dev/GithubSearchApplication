package com.jydev.githubsearchapplication.ui.favoriteuser

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.jydev.githubsearchapplication.databinding.FragmentFavoriteUserBinding
import com.jydev.githubsearchapplication.ui.FavoriteUserViewModel
import com.jydev.githubsearchapplication.ui.favoriteuser.adapter.FavoriteUserAdapter

class FavoriteUserFragment : Fragment() {
    private lateinit var binding : FragmentFavoriteUserBinding
    private lateinit var favoriteUserAdapter : FavoriteUserAdapter
    private val favoriteUserViewModel : FavoriteUserViewModel by viewModels({requireActivity()})

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFavoriteUserBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        with(binding){
            setView()
            setListener()
            observeData()
        }
    }

    private fun FragmentFavoriteUserBinding.setView(){
        setAdapter()
    }

    private fun FragmentFavoriteUserBinding.setListener(){
        searchEditText.setOnEditorActionListener { _, _, _ ->
            true
        }
        searchEditText.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun afterTextChanged(s: Editable?) {
                val query = s.toString()
                favoriteUserViewModel.getFavoriteUserByQuery(query)
            }

        })
    }

    private fun FragmentFavoriteUserBinding.setAdapter(){
        favoriteUserAdapter = FavoriteUserAdapter{
            favoriteUserViewModel.deleteFavoriteUser(it)
        }
        recyclerView.adapter = favoriteUserAdapter
    }

    private fun observeData(){
        favoriteUserViewModel.observeData()
    }

    private fun FavoriteUserViewModel.observeData(){
        favoriteUserList.observe(viewLifecycleOwner){
            favoriteUserAdapter.submitList(it)
        }
    }
}