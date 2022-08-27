package com.df.android.main.view

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.df.android.R
import com.df.android.data.UiState
import com.df.android.data.domain.GithubUser
import com.df.android.data.message
import com.df.android.databinding.FragmentHomeBinding
import com.df.android.main.adapter.GithubUserAdapter
import com.df.android.main.viewmodel.HomeViewModel
import com.df.android.utils.viewBinding
import com.google.android.material.appbar.AppBarLayout
import com.gyf.immersionbar.ImmersionBar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlin.math.abs

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home) {

    private val binding by viewBinding(FragmentHomeBinding::bind)
    private val viewModel by viewModels<HomeViewModel>()
    private var adapter = GithubUserAdapter(::onItemClicked)


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        setUpEvent()
        observeData()
    }

    var isCollapsed = false

    private fun initView() {
        (activity as? AppCompatActivity)?.setSupportActionBar(binding.toolbar)
        binding.appbar.addOnOffsetChangedListener(
            AppBarLayout.OnOffsetChangedListener { appBarLayout, verticalOffset ->
                if (abs(verticalOffset) - appBarLayout.totalScrollRange == 0) {
                    // Collapsed
                    isCollapsed = true
                    binding.collapsingToolbar.title = getString(R.string.home_page_title)
                    binding.toolbarTitle.text = getString(R.string.home_page_title)

                    ImmersionBar.with(this)
                        .statusBarColor(R.color.colorPrimary)
                        .autoStatusBarDarkModeEnable(true, 0.2f)
                        .init()
                } else {
                    // Expanded
                    binding.collapsingToolbar.title = ""
                    binding.toolbarTitle.text = ""

                    isCollapsed = false
                    ImmersionBar.with(this).reset()
                    ImmersionBar.with(this)
                        .transparentStatusBar()
                        .init()
                }
            }
        )



        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = adapter
    }


    override fun onResume() {
        super.onResume()
        if (isCollapsed) {
            ImmersionBar.with(this)
                .statusBarColor(R.color.colorPrimary)
                .autoStatusBarDarkModeEnable(true, 0.2f)
                .init()
        } else {
            ImmersionBar.with(this).reset()
            ImmersionBar.with(this)
                .transparentStatusBar()
                .init()
        }
    }


    private fun setUpEvent() {
        binding.swipeRefresh.setOnRefreshListener {
            Toast.makeText(
                requireContext(),
                getString(R.string.home_cta_refresh),
                Toast.LENGTH_SHORT
            ).show()
            viewModel.getGithubUser()
        }
    }

    private fun observeData() {
        viewModel.listUsersState
            .flowWithLifecycle(viewLifecycleOwner.lifecycle)
            .onEach { state ->
                when (state) {
                    is UiState.Loading -> {
                        if (!binding.swipeRefresh.isRefreshing) {
                            binding.swipeRefresh.isRefreshing = true
                        }
                    }
                    is UiState.Success -> {
                        binding.swipeRefresh.isRefreshing = false
                        adapter.submitList(state.data)

                        binding.txtNoData.isVisible = state.data.isEmpty()
                    }
                    is UiState.Error -> {
                        Toast.makeText(
                            requireContext(),
                            state.error.message(),
                            Toast.LENGTH_SHORT
                        ).show()
                        binding.swipeRefresh.isRefreshing = false
                    }
                }
            }.launchIn(viewLifecycleOwner.lifecycleScope)
    }

    private fun onItemClicked(item: GithubUser) {
        val bundle = bundleOf(
            DetailFragment.ARG_USER to item
        )
        findNavController().navigate(R.id.DetailFragment, bundle)
    }

}