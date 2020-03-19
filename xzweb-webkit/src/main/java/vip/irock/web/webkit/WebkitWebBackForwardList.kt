package vip.irock.web.webkit

import android.webkit.WebBackForwardList
import vip.irock.web.protocol.IWebBackForwardList
import vip.irock.web.protocol.IWebHistoryItem

class WebkitWebBackForwardList(val webBackForwardList: WebBackForwardList):
    IWebBackForwardList {
    override fun getCurrentItem(): IWebHistoryItem {
        return WebkitWebHistoryItem(webBackForwardList.currentItem)
    }

    override fun getCurrentIndex(): Int {
        return webBackForwardList.currentIndex
    }

    override fun getItemAtIndex(var1: Int): IWebHistoryItem {
        return WebkitWebHistoryItem(
            webBackForwardList.getItemAtIndex(
                var1
            )
        )
    }

    override fun getSize(): Int {
        return webBackForwardList.size
    }

}