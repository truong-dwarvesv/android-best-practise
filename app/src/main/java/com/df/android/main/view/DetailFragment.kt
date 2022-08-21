package com.df.android.main.view

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.df.android.R
import com.df.android.data.model.GithubUserDetailDto
import com.df.android.data.model.GithubUserDto
import com.df.android.databinding.FragmentDetailBinding
import com.df.android.main.viewmodel.DetailViewModel
import com.df.android.utils.viewBinding

class DetailFragment: Fragment(R.layout.fragment_detail) {

    private val binding by viewBinding(FragmentDetailBinding::bind)
    private lateinit var userInfo: GithubUserDto
    val viewModel by viewModels<DetailViewModel>()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        setUpEvent()
        observerData()
    }

    private fun observerData() {
//        viewModel.userDetailLiveData.observe(this, Observer { it ->
//            binding.apply {
//                when (it) {
//                    is ResponseData.Loading -> {
//                        if (!swipeRefresh.isRefreshing) {
//                            loadingProgressbar.visible()
//                        }
//                    }
//                    is ResponseData.Success -> {
//                        loadingProgressbar.visibility = View.GONE
//                        if (swipeRefresh.isRefreshing) swipeRefresh.isRefreshing = false
//                        updateUserInfo(it.data)
//                    }
//                    is ResponseData.Error -> {
//                        Toast.makeText(
//                            this@DetailActivity,
//                            it.message(),
//                            Toast.LENGTH_SHORT
//                        ).show()
//                        loadingProgressbar.gone()
//                        if (swipeRefresh.isRefreshing) swipeRefresh.isRefreshing = false
//                    }
//                }
//            }
//
//        })
//
//        viewModel.getUserDetail(userInfo)
    }


    private fun initView() {
        TODO("Not yet implemented")
    }

    private fun setUpEvent() {
        TODO("Not yet implemented")
    }

    private fun updateUserInfo(user: GithubUserDto) {
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

    private fun updateUserInfo(user: GithubUserDetailDto) {
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

    companion object{
        const val ARG_USER = "ARG_USER"
    }

}