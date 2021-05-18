package skfo763.ably.assignment.main.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.scopes.FragmentScoped
import skfo763.ably.assignment.main.data.ProductItem

@FragmentScoped
class LikeRecyclerAdapter: RecyclerView.Adapter<ProductViewHolder>() {

    private val itemList = mutableListOf<ProductItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        return ProductViewHolder.instance(parent)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.setData(itemList[position])
    }

    override fun getItemCount(): Int = itemList.size

    fun initData(data: List<ProductItem>) {
        itemList.clear()
        itemList.addAll(data)
        notifyDataSetChanged()
    }

}