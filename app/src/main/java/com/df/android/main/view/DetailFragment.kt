package com.df.android.main.view

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.df.android.R
import com.df.android.data.UiState
import com.df.android.data.domain.GithubUser
import com.df.android.data.domain.GithubUserDetail
import com.df.android.data.message
import com.df.android.databinding.FragmentDetailBinding
import com.df.android.main.viewmodel.DetailViewModel
import com.df.android.utils.argument
import com.df.android.utils.viewBinding
import com.df.android.utils.visible
import com.gyf.immersionbar.ImmersionBar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class DetailFragment : Fragment(R.layout.fragment_detail) {

    private val binding by viewBinding(FragmentDetailBinding::bind)
    private val viewModel by viewModels<DetailViewModel>()
    private var userInfo by argument<GithubUser>(ARG_USER)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getGithubUser(userInfo.login)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        ImmersionBar.with(this).reset().init()

        initView()
        setUpEvent()
        dataObserver()
    }

    private fun dataObserver() {
        viewModel.githubUserDetailState
            .flowWithLifecycle(viewLifecycleOwner.lifecycle)
            .onEach { state ->
                when (state) {
                    is UiState.Loading -> {
                        if (!binding.swipeRefresh.isRefreshing) {
                            binding.loadingProgressbar.visible()
                        }
                    }
                    is UiState.Success -> {
                        binding.loadingProgressbar.visibility = View.GONE
                        if (binding.swipeRefresh.isRefreshing) binding.swipeRefresh.isRefreshing =
                            false
                        updateUserInfo(state.data)
                    }
                    is UiState.Error -> {
                        Toast.makeText(
                            requireContext(),
                            state.error.message(),
                            Toast.LENGTH_SHORT
                        ).show()
                        binding.loadingProgressbar.visibility = View.GONE
                        if (binding.swipeRefresh.isRefreshing) binding.swipeRefresh.isRefreshing =
                            false
                    }
                }
            }.launchIn(viewLifecycleOwner.lifecycleScope)
    }

    private fun initView() {
        updateUserInfo(userInfo)
    }

    private fun setUpEvent() {
        binding.swipeRefresh.setOnRefreshListener {
            Toast.makeText(
                requireContext(),
                getString(R.string.home_cta_refresh),
                Toast.LENGTH_SHORT
            ).show()
            viewModel.getGithubUser(userInfo.login)
        }

        binding.buttonBack.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun updateUserInfo(user: GithubUser) {
        binding.apply {
            textUsername.text = user.login
            Glide.with(imageAvatar.context)
                .load(user.avatarUrl)
                .apply(RequestOptions.circleCropTransform())
                .transition(
                    DrawableTransitionOptions()
                        .crossFade()
                )
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imageAvatar)
        }
    }

    private fun updateUserInfo(user: GithubUserDetail) {
        binding.apply {

            textUsername.text = user.name ?: userInfo.login
            textLocation.text = user.location ?: "N/A"
            textBio.text = user.bio ?: "N/A"

            textNumFollower.text = user.followers.toString()
            textNumFollowing.text = user.following.toString()
            textNumRepo.text = user.publicRepos.toString()


            Glide.with(imageAvatar.context)
                .load(user.avatarUrl)
                .apply(RequestOptions.circleCropTransform())
                .transition(
                    DrawableTransitionOptions()
                        .crossFade()
                )
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imageAvatar)
        }

    }

    companion object {
        const val ARG_USER = "ARG_USER"
    }

}