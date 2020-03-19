package vip.irock.web.protocol

interface IWebResourceError {
    fun getErrorCode(): Int

    fun getDescription(): CharSequence
}