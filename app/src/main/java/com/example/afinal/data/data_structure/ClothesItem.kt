package com.example.afinal.data.data_structure

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Clothes_items")
data class ClothesItem(
    @PrimaryKey val id: Int,
    val category: String,
    val description: String,
    val image: String,
    val price: Double,
    val title: String
)
