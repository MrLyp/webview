package vip.irock.web.protocol

interface ISslErrorHandler {
    fun proceed()

    fun cancel()
}