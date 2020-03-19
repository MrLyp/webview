package vip.irock.web.webkit

import android.webkit.SslErrorHandler
import vip.irock.web.protocol.ISslErrorHandler

class WebkitSslErrorHandler(private val handler: SslErrorHandler) :
    ISslErrorHandler {
    override fun proceed() {
        handler.proceed()
    }

    override fun cancel() {
        handler.cancel()
    }
}