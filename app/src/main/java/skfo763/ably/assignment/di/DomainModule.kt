package skfo763.ably.assignment.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import skfo763.ably.domain.HomeRepository
import skfo763.ably.domain.HomeRepositoryImpl

@Module
@InstallIn(ActivityComponent::class)
abstract class DomainModule {

    @Binds
    abstract fun bindHomeRepository(impl: HomeRepositoryImpl): HomeRepository

}