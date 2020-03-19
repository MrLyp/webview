package vip.irock.web.protocol

interface IWebBackForwardListClient {
    fun onNewHistoryItem(var1: IWebHistoryItem)

    fun onIndexChanged(var1: IWebHistoryItem, var2: Int)

    fun onRemoveHistoryItem(var1: IWebHistoryItem)
}