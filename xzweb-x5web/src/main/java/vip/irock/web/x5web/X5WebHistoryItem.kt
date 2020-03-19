package vip.irock.web.x5web

import android.graphics.Bitmap
import com.tencent.smtt.sdk.WebHistoryItem
import vip.irock.web.protocol.IWebHistoryItem

class X5WebHistoryItem(val webHistoryItem: WebHistoryItem) :
    IWebHistoryItem {

    private var mCustomData: Any? = null

    override fun getUrl(): String {
        return webHistoryItem.url
    }

    override fun getOriginalUrl(): String {
        return webHistoryItem.originalUrl
    }

    override fun getTitle(): String {
        return webHistoryItem.title
    }

    override fun getFavicon(): Bitmap? {
        return webHistoryItem.favicon
    }

    override fun getCustomData(): Any? {
        return mCustomData
    }

    override fun setCustomData(var1: Any) {
        mCustomData = var1
    }
}