package com.example.rickandmortydemo.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.rickandmortydemo.databinding.FragmentDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : Fragment() {

    private val viewModel: SharedViewModel by activityViewModels()
    private lateinit var binding: FragmentDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailBinding.inflate(layoutInflater, container, false)
        setObservers()
        return binding.root
    }

    private fun setObservers() {
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        viewModel.currentCharacter.observe(viewLifecycleOwner, { character ->
            binding.character = character
            binding.executePendingBindings()
        })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val currentId: Int = viewModel.currentId.value ?: 0
        if (currentId > 0) {
            viewModel.getCharacterById(currentId)
        } else {
            viewModel.getCharacterById(5)
        }
    }
}