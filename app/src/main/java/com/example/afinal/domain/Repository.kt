package com.example.afinal.domain

import androidx.lifecycle.LiveData
import com.example.afinal.data.data_structure.ClothesItem
import com.example.afinal.utils.Resource

interface Repository {

    suspend fun getShoppings(): LiveData<Resource<List<ClothesItem>>>
    fun getShoppingDetailsData(id: Int): LiveData<Resource<ClothesItem>>
    suspend fun getShoppingCategorySort(category: String): LiveData<Resource<List<ClothesItem>>>
    suspend fun getShoppingPriceSort(price: Double): LiveData<Resource<List<ClothesItem>>>
}