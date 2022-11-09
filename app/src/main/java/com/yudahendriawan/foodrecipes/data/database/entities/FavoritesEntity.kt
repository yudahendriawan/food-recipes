package com.yudahendriawan.foodrecipes.data.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.yudahendriawan.foodrecipes.models.Result
import com.yudahendriawan.foodrecipes.util.Constants.Companion.FAVORITES_RECIPES_TABLE

@Entity(tableName = FAVORITES_RECIPES_TABLE)
class FavoritesEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    var result: Result
) {

}