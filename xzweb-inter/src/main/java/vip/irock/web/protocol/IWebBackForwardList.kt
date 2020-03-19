package vip.irock.web.protocol

interface IWebBackForwardList {
    fun getCurrentItem(): IWebHistoryItem

    fun getCurrentIndex(): Int

    fun getItemAtIndex(var1: Int): IWebHistoryItem

    fun getSize(): Int
}