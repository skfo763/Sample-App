package skfo763.ably.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.navigation.fragment.NavHostFragment
import dagger.hilt.android.AndroidEntryPoint

abstract class BindingActivity<Binding: ViewDataBinding, ViewModel: BaseViewModel> : AppCompatActivity() {

    protected abstract val layoutResId: Int

    protected abstract val navHostResId: Int

    protected abstract val viewModel: ViewModel

    protected lateinit var binding: Binding

    protected abstract val setBindingVariable: (Binding) -> Unit

    private var navHostFragment: NavHostFragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, layoutResId)
        binding.lifecycleOwner = this

        setBindingVariable.invoke(binding)
        setNavHost()
    }

    open fun connectNavHostToController(host: NavHostFragment) {
        // override if activity supports navigation component
    }

    private fun setNavHost() {
        navHostResId ?: return
        (supportFragmentManager.findFragmentById(navHostResId) as? NavHostFragment)?.let {
            navHostFragment = it
            connectNavHostToController(it)
        }
    }

}