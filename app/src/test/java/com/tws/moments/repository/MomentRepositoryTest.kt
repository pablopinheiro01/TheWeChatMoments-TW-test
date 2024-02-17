package com.tws.moments.repository

import com.tws.moments.data.api.MomentService
import com.tws.moments.data.repository.MomentRepositoryImpl
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.mockk
import io.mockk.spyk
import junit.framework.TestCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test
import userJsmith

class MomentRepositoryTest {

    @InjectMockKs
    private lateinit var repositoryImpl: MomentRepositoryImpl

    private var momentService: MomentService = mockk()

    @Before
    fun setup() {
        Dispatchers.setMain(UnconfinedTestDispatcher())

        MockKAnnotations.init(this)

        repositoryImpl = spyk(
            MomentRepositoryImpl(
                momentService
            )
        )
    }

    @After
    fun after() {
        Dispatchers.resetMain()
    }

    @Test
    fun `GIVEN get data of user WHEN load data THEN return data of user`() {
        runTest {
            coEvery {
                momentService.user(any())
            } returns userJsmith

            val data = repositoryImpl.fetchUser()

            coVerify {
                repositoryImpl.fetchUser()
            }
            TestCase.assertEquals(userJsmith, data)
        }
    }


}