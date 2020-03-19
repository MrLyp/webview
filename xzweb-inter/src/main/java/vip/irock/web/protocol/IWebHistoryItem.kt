package vip.irock.web.protocol

import android.graphics.Bitmap

interface IWebHistoryItem {

    fun getUrl(): String

    fun getOriginalUrl(): String

    fun getTitle(): String

    fun getFavicon(): Bitmap?


    fun getCustomData(): Any?

    fun setCustomData(var1: Any)

}