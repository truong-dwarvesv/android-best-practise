package com.df.android.main.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.df.android.data.Resource
import com.df.android.data.UiState
import com.df.android.data.domain.GithubUserDetail
import com.df.android.data.message
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
class DetailViewModel @Inject constructor(
    private val repository: GithubRepository
) : ViewModel() {

    private val _githubUserDetailState = MutableStateFlow<UiState<GithubUserDetail>?>(null)
    val githubUserDetailState = _githubUserDetailState.asStateFlow()

    fun getGithubUser(userId: String) {
        viewModelScope.launch {
            repository.getUserDetail(userId)
                .onStart { _githubUserDetailState.emit(UiState.Loading()) }
                .catch { _githubUserDetailState.emit(UiState.Error(it.toError())) }
                .collectLatest { resource ->
                    when (resource) {
                        is Resource.Success -> {
                            Log.d("Carrick", "resource.value")
                            _githubUserDetailState.emit(UiState.Success(resource.value))
                        }
                        is Resource.Error -> {
                            Log.d("Carrick", "resource ${resource.errorType.message()}")
                            _githubUserDetailState.emit(UiState.Error(resource.errorType))
                        }
                    }
                }
        }
    }
}