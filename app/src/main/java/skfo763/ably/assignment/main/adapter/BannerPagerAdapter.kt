package skfo763.ably.assignment.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import skfo763.ably.assignment.databinding.ItemBannerImageBinding
import skfo763.ably.assignment.main.data.BannerItem
import skfo763.ably.base.BaseViewHolder

class BannerPagerAdapter: RecyclerView.Adapter<BannerPagerAdapter.BannerItemViewHolder>()  {

    private val itemList = mutableListOf<BannerItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BannerItemViewHolder {
        return BannerItemViewHolder.instance(parent)
    }

    override fun onBindViewHolder(holder: BannerItemViewHolder, position: Int) {
        holder.setData(itemList[position])
    }

    override fun getItemCount() = itemList.size

    fun initData(data: List<BannerItem>) {
        itemList.clear()
        itemList.addAll(data)
        notifyDataSetChanged()
    }

    class BannerItemViewHolder(
            private val binding: ItemBannerImageBinding
    ): BaseViewHolder<BannerItem>(binding.root) {
        companion object {
            fun instance(parent: ViewGroup) = BannerItemViewHolder(ItemBannerImageBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
            ))
        }

        override fun setData(data: BannerItem?) {
            super.setData(data)
            binding.bannerImageItem = data
        }
    }
}