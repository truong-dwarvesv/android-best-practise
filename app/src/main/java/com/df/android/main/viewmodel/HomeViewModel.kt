package com.df.android.main.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.df.android.data.Resource
import com.df.android.data.UiState
import com.df.android.data.domain.GithubUser
import com.df.android.data.toError
import com.df.android.repository.GithubRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: GithubRepository
) : ViewModel() {

    private val _listUsersState = MutableStateFlow<UiState<List<GithubUser>>?>(null)
    val listUsersState = _listUsersState.asStateFlow()

    init {
        getGithubUser()
    }

    fun getGithubUser() {
        viewModelScope.launch {
            repository.getGithubUsers()
                .onStart { _listUsersState.emit(UiState.Loading()) }
                .catch { _listUsersState.emit(UiState.Error(it.toError())) }
                .collectLatest { resource ->
                    when (resource) {
                        is Resource.Success -> {
                            _listUsersState.emit(UiState.Success(resource.value))
                        }
                        is Resource.Error -> {
                            _listUsersState.emit(UiState.Error(resource.errorType))
                        }
                    }
                }
        }
    }
}