package com.zack.socketdemo.dagger2

import android.app.Application
import com.zack.socketdemo.page.mainsocket.MainStockContract
import dagger.Module
import dagger.Provides

@Module
class CommonModule{

    var view: MainStockContract.IView

    constructor(view: MainStockContract.IView){
        this.view = view
    }

    @ActivityScope
    @Provides
    fun provideView():MainStockContract.IView{
        return this.view
    }

    @ActivityScope
    @Provides
    fun provideApplication(): Application{
        return view.getActivity().application
    }

}