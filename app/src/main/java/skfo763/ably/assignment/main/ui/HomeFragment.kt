package skfo763.ably.assignment.main.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import dagger.hilt.android.AndroidEntryPoint
import skfo763.ably.assignment.R
import skfo763.ably.assignment.component.GridItemDecoration
import skfo763.ably.assignment.component.GridPageListener
import skfo763.ably.assignment.databinding.FragmentHomeBinding
import skfo763.ably.assignment.main.adapter.ProductRecyclerAdapter
import skfo763.ably.assignment.main.viewmodel.HomeActivityViewModel
import skfo763.ably.base.BindingFragment
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment: BindingFragment<FragmentHomeBinding>(R.layout.fragment_home) {

    private val activityViewModel: HomeActivityViewModel by activityViewModels()
    @Inject lateinit var adapter: ProductRecyclerAdapter

    private val productSpanLookup = object: GridLayoutManager.SpanSizeLookup() {
        override fun getSpanSize(position: Int) = if(position == 0) 2 else 1
    }

    private val onSwiped = SwipeRefreshLayout.OnRefreshListener {
        activityViewModel.setSwipeLoading(true)
        activityViewModel.getHomeData()
    }

    private val pageListener = object: GridPageListener(8) {
        override fun onNeedLoad() {
            if(binding.productSwipeLayout.isRefreshing) {
                return
            }
            val lastItemIdx = adapter.lastProduct?.productId ?: return
            activityViewModel.getMoreGoods(lastItemIdx)
        }
    }

    override val bindingVariable: (FragmentHomeBinding) -> Unit = {
        it.activityViewModel = this.activityViewModel
        it.productSwipeLayout.setOnRefreshListener(onSwiped)
        setRecyclerView()
        setObserver()
    }

    private fun setObserver() = with(activityViewModel) {
        productItemList.observe(viewLifecycleOwner, {
            pageListener.initFlags()
        })
    }

    private fun setRecyclerView() = with(binding.productListView) {
        adapter = this@HomeFragment.adapter
        addOnScrollListener(pageListener)
        layoutManager = GridLayoutManager(requireContext(), 2).apply {
            spanSizeLookup = productSpanLookup
        }
        addItemDecoration(GridItemDecoration(requireContext(), productSpanLookup))
        itemAnimator = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activityViewModel.getHomeData()
    }
}