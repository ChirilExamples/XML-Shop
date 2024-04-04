package com.example.afinal.data

import com.example.afinal.data.local.ShoppingDAO
import com.example.afinal.data.remote.ShoppingRemoteDatasource
import com.example.afinal.domain.Repository
import com.example.afinal.utils.performGetOperation
import javax.inject.Inject

class ShoppingRepository @Inject constructor(
    private val remoteDatasource: ShoppingRemoteDatasource, private val localDatasource: ShoppingDAO
) : Repository {
    override suspend fun getShoppings() =
        performGetOperation(databaseQuery = { localDatasource.getAllShoppings() },
            networkCall = { remoteDatasource.getAllShoppings() },
            saveCallResult = { localDatasource.insertAll(it) })

    override fun getShoppingDetailsData(id: Int) =
        performGetOperation(databaseQuery = { localDatasource.getShopping(id) },
            networkCall = { remoteDatasource.getShoppingsDetails(id) },
            saveCallResult = { localDatasource.insertDetails(it) })

    override suspend fun getShoppingCategorySort(category: String) = performGetOperation(
        databaseQuery = { localDatasource.getSortCategory(category) },
        networkCall = { remoteDatasource.getCategorySort(category) },
        saveCallResult = { localDatasource.insertAll(it) })

    override suspend fun getShoppingPriceSort(price: Double) =
        performGetOperation(databaseQuery = { localDatasource.getSortPrice(price) },
            networkCall = { remoteDatasource.getPriceSort(price) },
            saveCallResult = { localDatasource.insertAll(it) })
}
