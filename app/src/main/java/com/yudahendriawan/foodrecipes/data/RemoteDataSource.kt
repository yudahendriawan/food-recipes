package com.yudahendriawan.foodrecipes.data

import com.yudahendriawan.foodrecipes.data.network.FoodRecipesApi
import com.yudahendriawan.foodrecipes.models.FoodRecipe
import retrofit2.Response
import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private val foodRecipesApi: FoodRecipesApi
) {

    suspend fun getRecipes(queries: Map<String, String>) : Response<FoodRecipe> {
        return foodRecipesApi.getRecipes(queries)
    }

}