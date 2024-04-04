package com.example.afinal

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import com.example.afinal.data.ShoppingRepository
import com.example.afinal.data.data_structure.ClothesItem
import com.example.afinal.presentation.vm.DetailsViewModel
import com.example.afinal.utils.Resource
import io.mockk.every
import io.mockk.mockk
import junit.framework.TestCase.assertNull
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.kotlin.mock

class DetailsViewModelTest {
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: DetailsViewModel
    private val viewLifecycleOwner: LifecycleOwner = mock()

    private val shoppingList = listOf(
        ClothesItem(1, "unknown", "Thing", "", 15.0, "Human"),
        ClothesItem(2, "male", "some", "", 10.0, "Alien")
    )

    private val repository: ShoppingRepository = mockk(relaxed = true) {
        every { runBlocking { getShoppings() } } returns MutableLiveData(
            Resource.success(
                shoppingList
            )
        )
    }

    @Before
    fun setUp() {
        viewModel = DetailsViewModel(repository)
    }

    @Test
    fun testViewModelBehavior() {
        viewModel.startDetailsCall(0)
        assertNull(viewModel.clothes.value)
    }
}