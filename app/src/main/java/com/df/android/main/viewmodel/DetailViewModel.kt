package com.df.android.main.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.df.android.data.model.GithubUserDto
import com.df.android.domain.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(): ViewModel() {

    private val _getGithubUsersState = MutableStateFlow<UiState<List<GithubUserDto>>?>(null)
    val getGithubUsersState = _getGithubUsersState.asStateFlow()

    init {
        getGithubUser()
    }

    private fun getGithubUser() {
        viewModelScope.launch {

        }
    }
}