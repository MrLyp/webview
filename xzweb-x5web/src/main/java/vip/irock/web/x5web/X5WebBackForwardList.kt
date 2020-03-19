package vip.irock.web.x5web

import com.tencent.smtt.sdk.WebBackForwardList
import vip.irock.web.protocol.IWebBackForwardList
import vip.irock.web.protocol.IWebHistoryItem

class X5WebBackForwardList(val webBackForwardList: WebBackForwardList):
    IWebBackForwardList {
    override fun getCurrentItem(): IWebHistoryItem {
        return X5WebHistoryItem(webBackForwardList.currentItem)
    }

    override fun getCurrentIndex(): Int {
        return webBackForwardList.currentIndex
    }

    override fun getItemAtIndex(var1: Int): IWebHistoryItem {
        return X5WebHistoryItem(
            webBackForwardList.getItemAtIndex(
                var1
            )
        )
    }

    override fun getSize(): Int {
        return webBackForwardList.size
    }

}