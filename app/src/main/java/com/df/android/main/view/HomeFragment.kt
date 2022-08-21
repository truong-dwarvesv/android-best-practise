package com.df.android.main.view

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.df.android.R
import com.df.android.data.message
import com.df.android.data.model.GithubUserDto
import com.df.android.databinding.FragmentHomeBinding
import com.df.android.domain.UiState
import com.df.android.main.adapter.GithubUserAdapter
import com.df.android.main.viewmodel.HomeViewModel
import com.df.android.utils.viewBinding
import com.df.android.utils.visible
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class HomeFragment : Fragment(R.layout.fragment_home) {

    private val binding by viewBinding(FragmentHomeBinding::bind)
    private val viewModel by viewModels<HomeViewModel>()
    private var adapter = GithubUserAdapter(::onItemClicked)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        setUpEvent()
    }


    private fun initView() {
        binding.swipeRefresh.setOnRefreshListener {
            Toast.makeText(requireContext(), "Refreshing", Toast.LENGTH_SHORT).show()
            viewModel.getGithubUser()
        }

        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = adapter

        viewModel.listUsersState
            .flowWithLifecycle(viewLifecycleOwner.lifecycle)
            .onEach {
                when (it) {
                    is UiState.Loading -> {
                        if (!binding.swipeRefresh.isRefreshing) {
                            binding.swipeRefresh.isRefreshing = true
                        }
                    }
                    is UiState.Success -> {
                        binding.swipeRefresh.isRefreshing = false
                        adapter.submitList(it.data)

                        if (it.data.isNotEmpty()) {
                            binding.txtNoData.visible()
                        } else {
                            binding.txtNoData.visible()
                        }
                    }
                    is UiState.Error -> {
                        Toast.makeText(
                            requireContext(),
                            it.error.message(),
                            Toast.LENGTH_SHORT
                        ).show()
                        binding.swipeRefresh.isRefreshing = false
                    }
                }
            }.launchIn(viewLifecycleOwner.lifecycleScope)
    }

    private fun setUpEvent() {
        TODO("Not yet implemented")
    }

    private fun onItemClicked(item: GithubUserDto) {
        val bundle = bundleOf(
            DetailFragment.ARG_USER to item
        )
        findNavController().navigate(R.id.DetailFragment, bundle)
    }

}