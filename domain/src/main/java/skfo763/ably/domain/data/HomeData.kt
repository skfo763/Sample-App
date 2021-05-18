package skfo763.ably.domain.data

import skfo763.ably.remote.data.HomeApiResult

data class HomeData(
    val bannerList: List<Banner>? = null,
    val productList: List<Product>
)

fun HomeApiResult.toDomainData(): HomeData {
    val bannerList = bannerList?.map { it.toDomainData() }
    val productList = productList.map { it.toDomainData() }
    return HomeData(bannerList, productList)
}