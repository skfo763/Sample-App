package skfo763.ably.remote.data

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class HomeApiResult(
    @Json(name = "banners") val bannerList: List<BannerResponse>? = null,
    @Json(name = "goods") val productList: List<ProductResponse>
)

@JsonClass(generateAdapter = true)
data class BannerResponse(
    @Json(name = "id") val bannerId: Int,
    @Json(name = "image") val imageUrl: String
)

@JsonClass(generateAdapter = true)
data class ProductResponse(
    @Json(name = "id") val productId: Int,
    @Json(name = "name") val productName: String,
    @Json(name = "image") val imageUrl: String,
    @Json(name = "actual_price") val basePrice: Int,
    @Json(name = "price") val realPrice: Int,
    @Json(name = "is_new") val isNew: Boolean,
    @Json(name = "sell_count") val sellingCount: Int
)