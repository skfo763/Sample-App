package skfo763.ably.assignment.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.components.FragmentComponent
import dagger.hilt.components.SingletonComponent
import skfo763.ably.assignment.main.adapter.LikeRecyclerAdapter
import skfo763.ably.assignment.main.adapter.ProductRecyclerAdapter

@Module
@InstallIn(SingletonComponent::class)
object AppModule {



}

@Module
@InstallIn(ActivityComponent::class)
class ActivityModule {

}

@Module
@InstallIn(FragmentComponent::class)
object FragmentModule {

    @Provides
    fun provideProductAdapter() = ProductRecyclerAdapter()

    @Provides
    fun provideLikeAdapter() = LikeRecyclerAdapter()

}