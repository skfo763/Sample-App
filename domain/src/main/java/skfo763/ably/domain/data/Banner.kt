package skfo763.ably.domain.data

import skfo763.ably.remote.data.BannerResponse

data class Banner(
    val bannerId: Int,
    val imageUrl: String
)

fun BannerResponse.toDomainData() = Banner(
    bannerId,
    imageUrl
)
