package skfo763.ably.assignment.di

import android.app.Application
import androidx.room.Room
import com.skfo763.storage.room.ProductDao
import com.skfo763.storage.room.ProductDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import skfo763.ably.local.BuildConfig
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocalModule {

    @Singleton
    @Provides
    fun provideProductDataBase(application: Application): ProductDatabase {
        return Room.databaseBuilder(
            application.applicationContext,
            ProductDatabase::class.java,
            BuildConfig.LOCAL_DB_NAME
        ).build()
    }

    @Provides
    fun provideProductDao(database: ProductDatabase): ProductDao {
        return database.productDao()
    }

}