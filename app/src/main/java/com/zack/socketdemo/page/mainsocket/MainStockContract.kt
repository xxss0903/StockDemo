package com.zack.socketdemo.page.mainsocket

import com.zack.socketdemo.base.BaseActivity
import com.zack.socketdemo.base.BaseContract

class MainStockContract{
    interface IView: BaseContract.BaseView{
        fun showMainStock()

    }

    interface IPresenter: BaseContract.BasePresenter{
        fun loadMainStockDatas()
    }
}