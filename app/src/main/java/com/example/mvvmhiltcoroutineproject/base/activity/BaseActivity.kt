package com.example.mvvmhiltcoroutineproject.base.activity

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.example.mvvmhiltcoroutineproject.base.fragment.BaseFragmentCallback
import com.example.mvvmhiltcoroutineproject.base.viewmodel.BaseViewModel
import com.example.mvvmhiltcoroutineproject.utils.ConnectivityLiveData
import javax.inject.Inject

abstract class BaseActivity<DataBinding : ViewDataBinding> :
    AppCompatActivity(), BaseFragmentCallback {

    private lateinit var binding: DataBinding

    private val baseViewModel:BaseViewModel by viewModels()

    @Inject
    lateinit var internetConnectivityLiveData: ConnectivityLiveData

    @LayoutRes
    abstract fun getLayoutRes(): Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initDataBinding()
        observeData()
    }

    private fun initDataBinding() {
        binding = DataBindingUtil.setContentView(this, getLayoutRes())
        binding.lifecycleOwner = this
    }

    private fun observeData() {
        internetConnectivityLiveData.observe(this) {
            Log.e("TAG", "isInternetOn: $it")
        }
        baseViewModel.shouldShowLoader.observe(this) {
            Log.e("TAG", "observeData: shouldShowLoader -> $it")
        }
        baseViewModel.toastMessage.observe(this) {
            Log.e("TAG", "observeData: $it")
        }
    }
}