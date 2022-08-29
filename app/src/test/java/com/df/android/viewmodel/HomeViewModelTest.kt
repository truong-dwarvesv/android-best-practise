package com.df.android.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import app.cash.turbine.test
import com.df.android.CoroutinesTestRule
import com.df.android.data.Resource
import com.df.android.data.UiState
import com.df.android.data.toError
import com.df.android.main.viewmodel.HomeViewModel
import com.df.android.mockFlow
import com.df.android.repository.GithubRepository
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.InjectMocks
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.mockito.kotlin.stub
import kotlin.time.ExperimentalTime

@ExperimentalTime
@ExperimentalCoroutinesApi
@OptIn(ExperimentalCoroutinesApi::class, ExperimentalTime::class)
class HomeViewModelTest {

    @get:Rule
    var coroutinesTestRule = CoroutinesTestRule()

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @InjectMocks
    private lateinit var viewModel: HomeViewModel

    private val repository: GithubRepository = mock()


    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
    }

    @Test
    fun `given repository return success, when VM get list user, then VM emit ui state success`() =
        runTest {
            // Given
            val mockSuccess = Resource.Success(MockData.ListGithubUser)
            repository.stub {
                onBlocking { getGithubUsers() } doReturn mockFlow { mockSuccess }
            }

            // When
            viewModel.getGithubUser()

            // Then
            viewModel.listUsersState.test {
                val result = expectMostRecentItem()
                val actualListUsers = result?.value()
                assertThat(result is UiState.Success).isTrue()
                assertThat(actualListUsers).isEqualTo(MockData.ListGithubUser)
            }
        }


    @Test
    fun `given repository return error, when VM get list user, then VM emit ui state error`() =
        runTest {
            // Given
            val mockError = Throwable("Unexpected error").toError()
            val mockErrorState = Resource.Error(mockError)
            repository.stub {
                onBlocking { getGithubUsers() } doReturn mockFlow { mockErrorState }
            }

            // When
            viewModel.getGithubUser()

            // Then
            viewModel.listUsersState.test {
                val result = expectMostRecentItem()
                assertThat(result is UiState.Error).isTrue()
                assertThat(result?.error()).isEqualTo(mockError)
            }
        }
}