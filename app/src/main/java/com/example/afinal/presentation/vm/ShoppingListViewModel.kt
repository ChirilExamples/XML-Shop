package com.example.afinal.presentation.vm

import androidx.lifecycle.ViewModel
import com.example.afinal.domain.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@HiltViewModel
class ShoppingListViewModel @Inject constructor(repository: Repository) : ViewModel() {

    val repositoryList = runBlocking { repository.getShoppings() }

    //  val repositorySortPrice = repository.getShoppingPriceSort(price)
    val p10 = runBlocking { repository.getShoppingPriceSort(10.0) }
    val p25 = runBlocking { repository.getShoppingPriceSort(25.0) }
    val p50 = runBlocking { repository.getShoppingPriceSort(50.0) }
    val p100 = runBlocking { repository.getShoppingPriceSort(100.0) }

    //  val repositorySortCat = repository.getShoppingCategorySort(cat)
    val jewelery = runBlocking { repository.getShoppingCategorySort("jewelery") }
    val men = runBlocking { repository.getShoppingCategorySort("men's clothing") }
    val women = runBlocking { repository.getShoppingCategorySort("women's clothing") }
    val electronics = runBlocking { repository.getShoppingCategorySort("electronics") }
}
