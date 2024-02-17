package com.tws.moments.viewmodels

import ERROR_DEFAULT
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import com.tws.moments.data.api.entry.UserBean
import com.tws.moments.data.repository.MomentRepositoryImpl
import com.tws.moments.ui.model.Tweet
import com.tws.moments.ui.viewmodels.MainViewModel
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.mockk
import io.mockk.spyk
import junit.framework.TestCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.asCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import listTweet
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import userJsmith
import java.util.concurrent.Executors

@OptIn(ExperimentalCoroutinesApi::class)
class MainViewModelTest {

    @InjectMockKs
    private lateinit var viewModel: MainViewModel

    private var repository: MomentRepositoryImpl = mockk()

    @Rule
    @JvmField
    var rule: TestRule = InstantTaskExecutorRule()

    private val mainThreadSurrogate = Executors.newSingleThreadExecutor().asCoroutineDispatcher()

    @Before
    fun setUp() {
        Dispatchers.setMain(mainThreadSurrogate)

        viewModel = spyk(
            MainViewModel(
                repository
            )
        )
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        mainThreadSurrogate.close()
    }

    @Test
    fun `Give fetch tweets and call user info When prepare data to show in screen Then return five itens`() {
        runTest {

            coEvery {
                repository.fetchTweets()
            } returns listTweet

            coEvery {
                repository.fetchUser()
            } returns userJsmith

            viewModel.refreshTweets()

            coVerify { repository.fetchTweets() }
            coVerify { repository.fetchUser() }
            TestCase.assertEquals(viewModel.tweets.value,listTweet.subList(0, 5))
        }
    }

    @Test
    fun `Give fetch tweets When prepare data to show in screen Then receive Exception and return error`() {
        runTest {

            coEvery {
                repository.fetchTweets()
            } throws Exception(ERROR_DEFAULT)

            viewModel.refreshTweets()

            coVerify { repository.fetchTweets() }

            TestCase.assertEquals(mutableListOf<Tweet>(),viewModel.tweets.value)
        }
    }






}