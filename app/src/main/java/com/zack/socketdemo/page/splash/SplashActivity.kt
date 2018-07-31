package com.zack.socketdemo.page.splash

import android.content.Intent
import android.os.Bundle
import com.zack.socketdemo.R
import com.zack.socketdemo.base.BaseActivity
import com.zack.socketdemo.page.mainsocket.MainStockActivity

class SplashActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        jumpToMainStockActivity()
    }

    private fun jumpToMainStockActivity() {
        
        startActivity(Intent(this, MainStockActivity::class.java))
    }
}
