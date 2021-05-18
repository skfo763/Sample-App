package skfo763.ably.assignment.main.data

import skfo763.ably.domain.data.Banner
import skfo763.ably.domain.data.Product

sealed class ProductRecyclerData

data class BannerListItem(
        val bannerList: List<BannerItem>
): ProductRecyclerData()

data class BannerItem(
        val bannerId: Int,
        val imageUrl: String
)

data class ProductItem(
        val itemType: ItemType,
        val productId: Int,
        val productName: String,
        val imageUrl: String,
        val discountRate: Int,
        val price: Int,
        val isNew: Boolean,
        val sellingCount: Int,
        val onLikeClickListener: (ProductItem) -> Unit,
        var liked: Boolean = false,
): ProductRecyclerData() {

    enum class ItemType {
        PRODUCT, LIKE
    }

    fun toDomainData() = Product(
            productId,
            productName,
            imageUrl,
            100 * (price / (100 - discountRate)),
            price,
            isNew,
            sellingCount
    )
}

fun List<Banner>.toViewItem() = BannerListItem(map { BannerItem(it.bannerId, it.imageUrl) })

fun Product.toViewItem(
        itemType: ProductItem.ItemType = ProductItem.ItemType.PRODUCT,
        onLikeClicked: ((ProductItem) -> Unit) = {}
): ProductItem {
    return ProductItem(
            itemType,
            productId,
            productName,
            imageUrl,
            ((basePrice - realPrice).toFloat() / basePrice * 100).toInt(),
            realPrice,
            isNew,
            sellingCount,
            onLikeClicked
    )
}