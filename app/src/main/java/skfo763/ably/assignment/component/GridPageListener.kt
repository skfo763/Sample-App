package skfo763.ably.assignment.component

import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView


abstract class GridPageListener(
        private val visibleThreshold: Int = 27
): RecyclerView.OnScrollListener() {

    /**
     * The total number of items in the dataset after the last load
     */
    private var mPreviousTotal = 0

    /**
     * True if we are still waiting for the last set of data to load.
     */
    private var mLoading = false

    fun initFlags() {
        mPreviousTotal = 0
        mLoading = true
    }

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)
        val totalItemCount = recyclerView.layoutManager?.itemCount ?: return

        val visibleItemCount = recyclerView.childCount
        val firstVisibleItem = (recyclerView.layoutManager as GridLayoutManager).findFirstVisibleItemPosition()

        if (mLoading) {
            if (totalItemCount > mPreviousTotal) {
                mLoading = false
                mPreviousTotal = totalItemCount
            }
            return
        }
        if (totalItemCount - visibleItemCount <= firstVisibleItem + visibleThreshold) {
            onNeedLoad()
            mLoading = true
        }
    }

    abstract fun onNeedLoad()
}