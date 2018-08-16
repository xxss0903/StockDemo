package com.zack.socketdemo.page.mainsocket

import android.os.Bundle
import com.zack.socketdemo.R
import com.zack.socketdemo.base.BaseActivity
import com.zack.socketdemo.dagger2.CommonModule
import com.zack.socketdemo.dagger2.DaggerCommonComponent
import com.zack.socketdemo.utils.AlbumUtils
import javax.inject.Inject

/**
 * 显示股票首页，大盘指数
 */
class MainStockActivity: BaseActivity(), MainStockContract.IView{

    @Inject lateinit var presenter: MainStockPresenter

    override fun getActivity(): BaseActivity {
        return this
    }

    override fun showMainStock() {

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mainstock)

        initView()
    }

    private fun initView() {
        DaggerCommonComponent.builder().commonModule(CommonModule(this)).build().inject(this)
    }


}