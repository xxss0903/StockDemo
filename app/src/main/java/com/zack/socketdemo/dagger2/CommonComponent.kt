package com.zack.socketdemo.dagger2

import com.zack.socketdemo.page.mainsocket.MainStockActivity
import dagger.Component

@ActivityScope
@Component(modules = arrayOf(CommonModule::class))
interface CommonComponent{
    fun inject(activity: MainStockActivity)
}