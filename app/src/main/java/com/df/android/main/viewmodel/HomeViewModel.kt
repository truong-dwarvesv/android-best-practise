package com.df.android.main.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.df.android.data.model.GithubUserDto
import com.df.android.di.GithubRepository
import com.df.android.domain.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: GithubRepository
) : ViewModel() {

    private val _listUsersState = MutableStateFlow<UiState<List<GithubUserDto>>?>(null)
    val listUsersState = _listUsersState.asStateFlow()

    init {
        getGithubUser()
    }

    fun getGithubUser() {
        viewModelScope.launch {
            repository.getGithubUsers()
        }
    }
}