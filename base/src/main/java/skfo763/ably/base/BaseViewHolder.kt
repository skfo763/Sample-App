package skfo763.ably.base

import android.view.View
import androidx.recyclerview.widget.RecyclerView

open class BaseViewHolder<T>(
        itemView: View
): RecyclerView.ViewHolder(itemView) {

    open fun onViewHolderAttached() = Unit

    open fun onViewHolderDetached() = Unit

    open fun setData(data: T?) = Unit

}