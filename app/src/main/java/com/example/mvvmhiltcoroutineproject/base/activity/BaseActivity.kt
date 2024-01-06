package com.example.mvvmhiltcoroutineproject.base.activity

import android.os.Bundle
import android.util.Log
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.mvvmhiltcoroutineproject.base.fragment.BaseFragmentCallback
import com.example.mvvmhiltcoroutineproject.base.viewmodel.BaseViewModel
import com.example.mvvmhiltcoroutineproject.utils.InternetConnectivityObserver
import kotlinx.coroutines.launch
import javax.inject.Inject

abstract class BaseActivity<DataBinding : ViewDataBinding, ViewModel : BaseViewModel> :
    AppCompatActivity(), BaseFragmentCallback {

    protected lateinit var binding: DataBinding

    protected lateinit var viewModel: ViewModel

    @Inject
    lateinit var internetConnectivityObserver: InternetConnectivityObserver

    @LayoutRes
    abstract fun getLayoutRes(): Int

    abstract fun getViewModelClass(): Class<ViewModel>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initDataBinding()
        initViewModel()
        observeData()
    }

    override fun onDestroy() {
        internetConnectivityObserver.cleanUp()
        super.onDestroy()
    }

    private fun initDataBinding() {
        binding = DataBindingUtil.setContentView(this, getLayoutRes())
        binding.lifecycleOwner = this
    }

    private fun initViewModel() {
        viewModel = ViewModelProvider(this)[getViewModelClass()]
    }

    private fun observeData() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    internetConnectivityObserver.networkState.collect {
                        Log.e("TAG", "isInternetOn: $it")
                    }
                }
                launch {
                    viewModel.loadingStatusFlow.collect {
                        Log.e("TAG", "observeData: shouldShowLoader -> $it")
                    }
                }
                launch {
                    viewModel.toastMessageFlow.collect {
                        Log.e("TAG", "observeData: $it")
                    }
                }
            }
        }
    }
}