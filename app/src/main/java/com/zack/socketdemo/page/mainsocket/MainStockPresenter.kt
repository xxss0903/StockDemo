package com.zack.socketdemo.page.mainsocket

import javax.inject.Inject

class MainStockPresenter @Inject constructor(private var view: MainStockContract.IView) : MainStockContract.IPresenter{

    override fun loadMainStockDatas() {
        view.showMainStock()
    }


}