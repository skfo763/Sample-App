package com.skfo763.storage.room

import androidx.room.Database
import androidx.room.RoomDatabase
import skfo763.ably.local.BuildConfig

@Database(entities = [ProductEntity::class], version = BuildConfig.LOCAL_DB_VERSION)
abstract class ProductDatabase: RoomDatabase() {
    abstract fun productDao(): ProductDao
}