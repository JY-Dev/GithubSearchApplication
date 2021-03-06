package com.jydev.githubsearchapplication

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule


@OptIn(ExperimentalCoroutinesApi::class)
abstract class BaseViewModelTest {
    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()
    private val dispatcher = TestCoroutineDispatcher()
    @Before
    fun setup() {
        Dispatchers.setMain(dispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }
}