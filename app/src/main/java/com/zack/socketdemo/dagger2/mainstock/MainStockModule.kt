package com.zack.socketdemo.dagger2.mainstock

import com.zack.socketdemo.page.mainsocket.MainStockContract
import com.zack.socketdemo.page.mainsocket.MainStockPresenter
import dagger.Module
import dagger.Provides

@Module
class MainStockModule {

    @Provides
    fun getPresenter(view: MainStockContract.IView): MainStockPresenter {
        val presenter = MainStockPresenter(view)
        return presenter
    }

}