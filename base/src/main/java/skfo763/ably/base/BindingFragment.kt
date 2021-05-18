package skfo763.ably.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment

abstract class BindingFragment<Binding: ViewDataBinding>(
    private val layoutResId: Int
): Fragment() {

    protected lateinit var binding: Binding

    abstract val bindingVariable: (Binding) -> Unit

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, layoutResId, container, false)
        binding.lifecycleOwner = this
        bindingVariable.invoke(binding)
        return binding.root
    }

}