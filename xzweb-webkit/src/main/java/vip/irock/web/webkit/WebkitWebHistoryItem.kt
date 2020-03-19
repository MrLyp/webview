package vip.irock.web.webkit

import android.graphics.Bitmap
import android.webkit.WebHistoryItem
import vip.irock.web.protocol.IWebHistoryItem

class WebkitWebHistoryItem(val webHistoryItem: WebHistoryItem) :
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