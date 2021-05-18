package skfo763.ably.domain

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Single
import skfo763.ably.domain.data.HomeData
import skfo763.ably.domain.data.Product

interface HomeRepository {

    fun getHomeData(): Single<HomeData>

    fun getMoreGoods(lastIdx: Int? = null): Single<HomeData>

    fun getLikedProduct(): Single<List<Product>>

    fun addLikedProductToDb(vararg product: Product): Completable

    fun deleteLikedProductToDb(vararg product: Product): Completable

}