package skfo763.ably.assignment.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import skfo763.ably.assignment.databinding.ItemProductBinding
import skfo763.ably.assignment.main.data.ProductItem
import skfo763.ably.base.BaseViewHolder

class ProductViewHolder(
        private val binding: ItemProductBinding
): BaseViewHolder<ProductItem>(binding.root) {

    companion object {
        fun instance(parent: ViewGroup) = ProductViewHolder(ItemProductBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
        ))
    }

    override fun setData(data: ProductItem?) {
        super.setData(data)
        binding.productItem = data
    }

}
