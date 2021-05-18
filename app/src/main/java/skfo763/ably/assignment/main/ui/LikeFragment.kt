package skfo763.ably.assignment.main.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import dagger.hilt.android.AndroidEntryPoint
import skfo763.ably.assignment.R
import skfo763.ably.assignment.component.GridItemDecoration
import skfo763.ably.assignment.databinding.FragmentLikeBinding
import skfo763.ably.assignment.main.adapter.LikeRecyclerAdapter
import skfo763.ably.assignment.main.viewmodel.HomeActivityViewModel
import skfo763.ably.base.BindingFragment
import javax.inject.Inject

@AndroidEntryPoint
class LikeFragment: BindingFragment<FragmentLikeBinding>(R.layout.fragment_like) {

    private val activityViewModel: HomeActivityViewModel by activityViewModels()
    @Inject lateinit var adapter: LikeRecyclerAdapter

    private val onSwiped = SwipeRefreshLayout.OnRefreshListener {
        activityViewModel.setSwipeLoading(true)
        activityViewModel.getLikeData()
    }

    override val bindingVariable: (FragmentLikeBinding) -> Unit = {
        it.activityViewModel = this.activityViewModel
        it.likeListView.adapter = this.adapter
        it.likeListView.layoutManager = GridLayoutManager(requireContext(), 2)
        it.likeListView.addItemDecoration(GridItemDecoration(requireContext()))
        it.listSwipeLayout.setOnRefreshListener(onSwiped)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activityViewModel.getLikeData()
    }

}