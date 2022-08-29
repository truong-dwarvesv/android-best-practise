package com.df.android.viewmodel

import com.df.android.data.domain.GithubUser

object MockData {
    val ListGithubUser = Array(10) {
        GithubUser()
    }.toList()
}