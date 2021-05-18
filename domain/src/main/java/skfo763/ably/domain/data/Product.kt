package skfo763.ably.domain.data

import com.skfo763.storage.room.ProductEntity
import skfo763.ably.remote.data.ProductResponse

data class Product(
    val productId: Int,
    val productName: String,
    val imageUrl: String,
    val basePrice: Int,
    val realPrice: Int,
    val isNew: Boolean,
    val sellingCount: Int
) {
    fun toLocalData() = ProductEntity(
        productId,
        productName,
        imageUrl,
        basePrice,
        realPrice,
        isNew,
        sellingCount
    )
}

fun ProductResponse.toDomainData() = Product(
    productId,
    productName,
    imageUrl,
    basePrice,
    realPrice,
    isNew,
    sellingCount
)

fun ProductEntity.toDomainData() = Product(
    productId,
    productName,
    imageUrl,
    basePrice,
    realPrice,
    isNew,
    sellingCount
)