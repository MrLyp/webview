package vip.irock.web.x5web

import com.tencent.smtt.export.external.interfaces.SslErrorHandler
import vip.irock.web.protocol.ISslErrorHandler

class X5SslErrorHandler(private val handler: SslErrorHandler) :
    ISslErrorHandler {
    override fun proceed() {
        handler.proceed()
    }

    override fun cancel() {
        handler.cancel()
    }
}