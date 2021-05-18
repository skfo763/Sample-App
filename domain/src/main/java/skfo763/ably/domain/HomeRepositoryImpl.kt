package skfo763.ably.domain

import com.skfo763.storage.room.ProductDao
import dagger.hilt.android.scopes.ViewModelScoped
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import skfo763.ably.domain.data.HomeData
import skfo763.ably.domain.data.Product
import skfo763.ably.domain.data.toDomainData
import skfo763.ably.remote.api.HomeApi
import javax.inject.Inject

@ViewModelScoped
class HomeRepositoryImpl @Inject constructor(
    private val homeApi: HomeApi,
    private val productDatabase: ProductDao
): HomeRepository {

    override fun getHomeData(): Single<HomeData> {
        return homeApi.getHomeData().map { it.toDomainData() }
    }

    override fun getMoreGoods(lastIdx: Int?): Single<HomeData> {
        return homeApi.getMoreGoods(lastIdx).map { it.toDomainData() }
    }

    override fun getLikedProduct(): Single<List<Product>> {
        return productDatabase.getLikedProductList().map {
            it.map { entity -> entity.toDomainData() }
        }
    }

    override fun addLikedProductToDb(vararg product: Product): Completable {
        return productDatabase.insertLikedProduct(*product.map { it.toLocalData() }.toTypedArray())
    }

    override fun deleteLikedProductToDb(vararg product: Product): Completable {
        return productDatabase.deleteLikedProduct(*product.map { it.toLocalData() }.toTypedArray())
    }

}