package skfo763.ably.assignment.ext

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import skfo763.ably.assignment.main.adapter.BannerPagerAdapter
import skfo763.ably.assignment.main.adapter.LikeRecyclerAdapter
import skfo763.ably.assignment.main.adapter.ProductRecyclerAdapter
import skfo763.ably.assignment.main.data.BannerItem
import skfo763.ably.assignment.main.data.ProductItem
import skfo763.ably.assignment.main.data.ProductRecyclerData

object BindingAdapter {

    @BindingAdapter("productItemList")
    @JvmStatic
    fun RecyclerView.setProductItemList(itemList: List<ProductRecyclerData>?) {
        itemList?.let {
            (adapter as? ProductRecyclerAdapter)?.initData(itemList)
        }
    }

    @BindingAdapter("likeItemList")
    @JvmStatic
    fun RecyclerView.setLikedItemList(itemList: List<ProductItem>?) = itemList?.let {
        (adapter as? LikeRecyclerAdapter)?.initData(it)
    }

    @BindingAdapter("additionalProductItemList")
    @JvmStatic
    fun RecyclerView.addProductItemList(itemList: List<ProductRecyclerData>?) {
        itemList?.let {
            (adapter as? ProductRecyclerAdapter)?.addData(itemList)
        }
    }

    @BindingAdapter("likedItem")
    @JvmStatic
    fun RecyclerView.refreshLikedItem(item: ProductItem?) = item?.let {
        (adapter as? ProductRecyclerAdapter)?.refreshItem(item)
    }


    @BindingAdapter("imageUrl")
    @JvmStatic
    fun ImageView.setImageUrl(url: String?) = url?.let {
        Glide.with(context).load(it).into(this).clearOnDetach()
    }

    @BindingAdapter("isSwipe")
    @JvmStatic
    fun SwipeRefreshLayout.isSwipe(isSwipe: Boolean?) {
        isRefreshing = (isSwipe == true)
    }

    @BindingAdapter("itemList")
    @JvmStatic
    fun ViewPager2.setItemList(itemList: List<BannerItem>?) = itemList?.let {
        (adapter as? BannerPagerAdapter)?.initData(itemList)
    }


}