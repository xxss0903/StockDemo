package com.zack.socketdemo.page.splash

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.database.Cursor
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.provider.DocumentsContract
import android.provider.MediaStore
import android.widget.Toast
import com.google.zxing.*
import com.google.zxing.common.HybridBinarizer
import com.zack.socketdemo.R
import com.zack.socketdemo.base.BaseActivity
import com.zack.socketdemo.page.mainsocket.MainStockActivity
import com.zack.socketdemo.utils.AlbumUtils
import kotlinx.android.synthetic.main.activity_splash.*
import java.text.SimpleDateFormat
import java.util.*

class SplashActivity : BaseActivity() {

    private val HINTS: MutableMap<DecodeHintType, Any> = EnumMap<DecodeHintType, Any>(DecodeHintType::class.java)

    init {
        HINTS.put(DecodeHintType.TRY_HARDER, true)
        HINTS.put(DecodeHintType.POSSIBLE_FORMATS, EnumSet.allOf(BarcodeFormat::class.java))
        HINTS.put(DecodeHintType.PURE_BARCODE, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

//        scanQrcode()
        jumpToMainStockActivity()
    }

    private fun scanQrcode() {
        btn_scan_qrcode.setOnClickListener {
            openAlbum()
        }
    }

    private fun openAlbum() {
        AlbumUtils.openAlbum(this)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && data != null){
            when(requestCode){
                AlbumUtils.SELECT_PIC -> {
                    decodeFromAlbum(data)
                }
            }
        }
    }

    private fun decodeFromAlbum(data: Intent?) {
        AlbumUtils.decodeFromAlbum(this, data)
    }

    private fun jumpToMainStockActivity() {
        startActivity(Intent(this, MainStockActivity::class.java))
    }
}
