package com.skfo763.storage.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "product", indices = [Index(value = ["id"])])
data class ProductEntity(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") val productId: Int,
    @ColumnInfo(name = "name") val productName: String,
    @ColumnInfo(name = "image") val imageUrl: String,
    @ColumnInfo(name = "actual_price") val basePrice: Int,
    @ColumnInfo(name = "price") val realPrice: Int,
    @ColumnInfo(name = "is_new") val isNew: Boolean,
    @ColumnInfo(name = "sell_count") val sellingCount: Int
)