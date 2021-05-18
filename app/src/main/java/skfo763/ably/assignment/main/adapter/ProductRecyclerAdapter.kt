package skfo763.ably.assignment.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.scopes.FragmentScoped
import skfo763.ably.assignment.databinding.ItemBannerBinding
import skfo763.ably.assignment.databinding.ItemProductBinding
import skfo763.ably.assignment.main.data.BannerListItem
import skfo763.ably.assignment.main.data.ProductItem
import skfo763.ably.assignment.main.data.ProductRecyclerData
import skfo763.ably.base.BaseViewHolder
import skfo763.ably.domain.data.Product

@FragmentScoped
class ProductRecyclerAdapter : RecyclerView.Adapter<BaseViewHolder<*>>() {

    private val itemList = mutableListOf<ProductRecyclerData>()

    val lastProduct: ProductItem? get() = itemList.lastOrNull() as? ProductItem

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        when(val item = itemList[position]) {
            is BannerListItem -> (holder as? HeaderViewHolder)?.setData(item)
            is ProductItem -> (holder as? ProductViewHolder)?.setData(item)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        return when(viewType.toViewType()) {
            ViewType.HEADER -> HeaderViewHolder.instance(parent)
            ViewType.ITEM -> ProductViewHolder.instance(parent)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when(itemList[position]) {
            is BannerListItem -> ViewType.HEADER.type
            is ProductItem -> ViewType.ITEM.type
        }
    }

    override fun getItemCount() = itemList.size

    private fun Int.toViewType(): ViewType {
        return when(this) {
            0 -> ViewType.HEADER
            else -> ViewType.ITEM
        }
    }

    fun addData(data: List<ProductRecyclerData>) {
        val start = if(itemList.isEmpty()) 0 else itemList.lastIndex + 1
        itemList.addAll(data)
        notifyItemRangeChanged(start, data.size)
    }

    fun initData(data: List<ProductRecyclerData>) {
        itemList.clear()
        itemList.addAll(data)
        notifyDataSetChanged()
    }

    fun refreshItem(item: ProductItem) {
        val target = itemList.indexOfFirst { (it as? ProductItem)?.productId == item.productId }
        if(target == -1) {
            return
        }
        val data = (itemList[target] as? ProductItem) ?: return
        data.liked = item.liked
        notifyItemChanged(target)
    }

    enum class ViewType(val type: Int) {
        HEADER(0),
        ITEM(1)
    }

    class HeaderViewHolder(
            private val binding: ItemBannerBinding
    ): BaseViewHolder<BannerListItem>(binding.root) {
        companion object {
            fun instance(parent: ViewGroup) = HeaderViewHolder(ItemBannerBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
            ))
        }

        init {
            binding.bannerPager.adapter = BannerPagerAdapter()
        }

        override fun setData(data: BannerListItem?) {
            super.setData(data)
            binding.bannerItem = data
        }
    }

}