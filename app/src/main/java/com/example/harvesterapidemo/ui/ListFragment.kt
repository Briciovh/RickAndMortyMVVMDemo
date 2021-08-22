package com.example.harvesterapidemo.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.harvesterapidemo.R
import com.example.harvesterapidemo.databinding.FragmentListBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ListFragment : Fragment() {

    private val viewModel: SharedViewModel by activityViewModels()
    private lateinit var binding: FragmentListBinding
    private lateinit var adapter: CharListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentListBinding.inflate(inflater)
        setObserversAndListeners()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getCharacters()
    }

    private fun setObserversAndListeners() {
        adapter = CharListAdapter(ItemClickListener { charId ->
            handleCharacterSelection(charId)
        })
        val linearLayoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        binding.mainRecyclerView.layoutManager = linearLayoutManager
        setScrollListener(linearLayoutManager)
        binding.mainRecyclerView.adapter = adapter
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        viewModel.characterList.observe(viewLifecycleOwner, { characters ->
            adapter.addData(characters)
        })
    }

    private fun setScrollListener(linearLayoutManager: LinearLayoutManager) {
        binding.mainRecyclerView.addOnScrollListener(object :
            PaginationScrollListener(linearLayoutManager) {
            override fun isLastPage(): Boolean {
                return viewModel.lastPage()
            }

            override fun isLoading(): Boolean {
                return viewModel.loadingState.value ?: false
            }

            override fun loadMoreItems() {
                viewModel.getCharacters()
            }

        })
    }

    private fun handleCharacterSelection(charId: Int) {
        viewModel.setCurrentId(charId)
        this.findNavController().navigate(R.id.action_listFragment_to_detailFragment)
    }
}