package com.zack.socketdemo.page.mainsocket

import android.os.Bundle
import com.zack.socketdemo.R
import com.zack.socketdemo.base.BaseActivity

/**
 * 显示股票首页，大盘指数
 */
class MainStockActivity: BaseActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mainstock)
    }
}