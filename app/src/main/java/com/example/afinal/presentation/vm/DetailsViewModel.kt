package com.example.afinal.presentation.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import com.example.afinal.data.ShoppingRepository
import com.example.afinal.data.data_structure.ClothesItem
import com.example.afinal.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(private val repository: ShoppingRepository) :
    ViewModel() {

    private val _id = MutableLiveData<Int>()

    private val _clothes = _id.switchMap { id -> repository.getShoppingDetailsData(id) }

    val clothes: LiveData<Resource<ClothesItem>> = _clothes

    fun startDetailsCall(id: Int) {
        _id.value = id
    }
}
