package skfo763.ably.assignment.component

import android.content.Context
import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import skfo763.ably.base.extension.DP

class GridItemDecoration(
        context: Context,
        private val spanSizeLookup: GridLayoutManager.SpanSizeLookup? = null
): RecyclerView.ItemDecoration() {
    
    private val plainMargin = 6.DP(context).toInt()
    private val boundaryMargin = 16.DP(context).toInt()
        
    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        super.getItemOffsets(outRect, view, parent, state)
        val position = parent.getChildAdapterPosition(view)
        val layoutParams = view.layoutParams as GridLayoutManager.LayoutParams
        val spanIndex = layoutParams.spanIndex

        if(spanSizeLookup == null || spanSizeLookup.getSpanSize(position) == 1) {
           setGridViewPadding(spanIndex, view)
        } else {
            view.setPadding(0, 0, 0, plainMargin)
        }
    }

    private fun setGridViewPadding(spanIndex: Int, view: View) {
        when(spanIndex) {
            0 -> view.setPadding(boundaryMargin, plainMargin, plainMargin, plainMargin)
            1 -> view.setPadding(plainMargin, plainMargin, boundaryMargin, plainMargin)
        }
    }

}