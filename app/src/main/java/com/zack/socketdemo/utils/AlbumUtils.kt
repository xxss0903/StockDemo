package com.zack.socketdemo.utils

import android.content.Context
import android.content.Intent
import android.database.Cursor
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Environment
import android.provider.DocumentsContract
import android.provider.MediaStore
import com.google.zxing.*
import com.google.zxing.common.HybridBinarizer
import com.zack.socketdemo.base.BaseActivity
import java.text.SimpleDateFormat
import java.util.*

object AlbumUtils {

    private val HINTS: MutableMap<DecodeHintType, Any> = EnumMap<DecodeHintType, Any>(DecodeHintType::class.java)
    val SELECT_PIC: Int = 100

    /**
     * open album
     */
    fun openAlbum(activity: BaseActivity) {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        activity.startActivityForResult(intent, SELECT_PIC)
    }

    /**
     * decode qrcode by zxing
     */
    fun decodeFromAlbum(context: Context,data: Intent?) {
        fillHints()
        val path = getPicPath(context,data!!.data)
        val pic = getBitmapByPath(path)
        decodeByZxing(pic)
    }

    private fun fillHints() {
        if (HINTS.isEmpty()){
            HINTS[DecodeHintType.TRY_HARDER] = true
            HINTS[DecodeHintType.POSSIBLE_FORMATS] = EnumSet.allOf(BarcodeFormat::class.java)
            HINTS[DecodeHintType.PURE_BARCODE] = false
        }
    }


    private fun decodeByZxing(pic: Bitmap): String? {
        try{
            var result: Result?
            var source: RGBLuminanceSource? = null
            val pixels = IntArray(pic.width * pic.height)
            pic.getPixels(pixels, 0, pic.width, 0, 0, pic.width, pic.height)
            source = RGBLuminanceSource(pic.width, pic.height, pixels)
            result = MultiFormatReader().decode(BinaryBitmap(HybridBinarizer(source)), HINTS)
            return result.text
        } catch (e: Exception){
            e.printStackTrace()
        }
        return "decode qrcode error"
    }

    private fun getBitmapByPath(path: String?): Bitmap {
        val options = BitmapFactory.Options()
        options.inJustDecodeBounds = true
        BitmapFactory.decodeFile(path, options)
        var sampleSize = options.outHeight / 400
        if(sampleSize <= 0){
            sampleSize = 1
        }
        options.inSampleSize = sampleSize
        options.inJustDecodeBounds = false
        return BitmapFactory.decodeFile(path, options)
    }

    private fun getPicPath(context: Context,data: Uri?):String? {
        if(DocumentsContract.isDocumentUri(context, data)){
            if (isExternalStorageDocument(data)) {
                val docId = DocumentsContract.getDocumentId(data)
                val split = docId.split(":".toRegex()).dropLastWhile {
                    it.isEmpty()
                }.toTypedArray()
                val type = split[0]
                if ("primary".equals(type, true)) {
                    return Environment.getExternalStorageDirectory().toString() + "/" + split[1]
                }
            } else if(isMediaDocument(data)){
                val docId = DocumentsContract.getDocumentId(data)
                val split = docId.split(":".toRegex()).dropLastWhile {
                    it.isEmpty()
                }.toTypedArray()
                val type = split[0]
                var contentUri: Uri? = null
                if ("image" == type){
                    contentUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI
                } else if("video" == type){
                    contentUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI
                } else if("audio" == type){
                    contentUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI
                }

                val selection = "_id=?"
                val selectionArgs = arrayOf(split[1])
                return getDataColumn(context, contentUri, selection, selectionArgs)
            }
        } else if("content".equals(data!!.scheme, true)){
            return if(isGooglePhotosUri(data)) data.lastPathSegment else getDataColumn(context, data, null, null)
        } else if ("file".equals(data.scheme, true)){
            return data.path
        }
        return null
    }

    private fun isGooglePhotosUri(data: Uri): Boolean {
        return "com.google.android.apps.photos.content" == data.authority
    }

    private fun getDataColumn(context: Context, contentUri: Uri?, selection: String?, selectionArgs: Array<String>?): String? {
        var cursor: Cursor? = null
        val column = "_data"
        val projection = arrayOf(column)
        try {
            cursor = context.contentResolver.query(contentUri, projection, selection,selectionArgs, null)
            if (cursor != null && cursor.moveToFirst()){
                val index = cursor.getColumnIndexOrThrow(column)
                return cursor.getString(index)
            }
        } finally {
            if (cursor != null){
                cursor.close()
            }
        }
        return null
    }

    private fun isMediaDocument(data: Uri?): Boolean {
        return "com.android.provider.media.documents" == data!!.authority
    }

    private fun isExternalStorageDocument(data: Uri?): Boolean {
        return "com.android.externalstorage.documents" == data!!.authority
    }

}