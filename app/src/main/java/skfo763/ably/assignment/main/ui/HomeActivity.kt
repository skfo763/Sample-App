package skfo763.ably.assignment.main.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import dagger.hilt.android.AndroidEntryPoint
import skfo763.ably.assignment.R
import skfo763.ably.assignment.databinding.ActivityHomeBinding
import skfo763.ably.assignment.main.viewmodel.HomeActivityViewModel
import skfo763.ably.base.BindingActivity

@AndroidEntryPoint
class HomeActivity(
    override val layoutResId: Int = R.layout.activity_home,
    override val navHostResId: Int = R.id.main_contents
): BindingActivity<ActivityHomeBinding, HomeActivityViewModel>() {

    override val viewModel: HomeActivityViewModel by viewModels()

    override val setBindingVariable: (ActivityHomeBinding) -> Unit = { }

    override fun connectNavHostToController(host: NavHostFragment) {
        binding.mainBottomNavigation.setupWithNavController(host.navController)
        binding.mainBottomNavigation.itemIconTintList = null
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getLikeData()
    }

}