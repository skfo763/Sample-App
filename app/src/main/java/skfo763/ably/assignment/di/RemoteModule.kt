package skfo763.ably.assignment.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import skfo763.ably.remote.NetworkManager
import skfo763.ably.remote.api.HomeApi
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RemoteModule {

    @Singleton
    @Provides
    fun getNetworkManager() = NetworkManager()

    @Provides
    fun provideHomeApi(manager: NetworkManager): HomeApi {
        return manager.getAblyApiRetrofit().create(HomeApi::class.java)
    }

}