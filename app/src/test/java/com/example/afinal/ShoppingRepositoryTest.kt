package com.example.afinal

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.afinal.data.ShoppingRepository
import com.example.afinal.data.data_structure.ClothesItem
import com.example.afinal.data.local.ShoppingDAO
import com.example.afinal.data.remote.ShoppingRemoteDatasource
import com.example.afinal.utils.Resource
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class ShoppingRepositoryTest {
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private val charactersObserver: Observer<Resource<List<ClothesItem>>> = mockk(relaxed = true)
    private val charactersObserverDetails: Observer<Resource<ClothesItem>> = mockk(relaxed = true)

    // Arrange
    private val remoteDatasource: ShoppingRemoteDatasource = mockk(relaxed = true)
    private val localDatasource: ShoppingDAO = mockk(relaxed = true)
    private lateinit var repository: ShoppingRepository

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setUp() {
        repository = ShoppingRepository(remoteDatasource, localDatasource)
        Dispatchers.setMain(UnconfinedTestDispatcher())
    }

    @Test
    fun `get character should return livedata of character API data`() {
        runBlocking { repository.getShoppings().observeForever(charactersObserver) }
        verify { charactersObserver.onChanged(any()) }
    }

    @Test
    fun `get character detailed data should return livedata of a single character`() {
        repository.getShoppingDetailsData(3).observeForever(charactersObserverDetails)
        verify { charactersObserverDetails.onChanged(any()) }
    }

    @Test
    fun testGetShoppingCategorySort() = runBlocking {
        repository.getShoppingCategorySort("male").observeForever(charactersObserver)
        verify { charactersObserver.onChanged(any()) }
    }

    @Test
    fun testgetShoppingPriceSort() = runBlocking {
        repository.getShoppingPriceSort(10.0).observeForever(charactersObserver)
        verify { charactersObserver.onChanged(any()) }
    }
}
