package com.example.afinal.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.afinal.data.data_structure.ClothesItem

@Dao
interface ShoppingDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(clothesItem: List<ClothesItem>)

    @Query("SELECT * FROM Clothes_items")
    fun getAllShoppings(): LiveData<List<ClothesItem>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDetails(product: ClothesItem)

    //  query for detail second fragment
    @Query("SELECT * FROM Clothes_items Where id = :id")
    fun getShopping(id: Int): LiveData<ClothesItem>

    //  query for sort by category
    @Query("SELECT * FROM Clothes_items Where category = :category")
    fun getSortCategory(category: String): LiveData<List<ClothesItem>>

    //  query for sort by price
    @Query("SELECT * FROM Clothes_items Where price <= :price")
    fun getSortPrice(price: Double): LiveData<List<ClothesItem>>
}
