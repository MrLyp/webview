package vip.irock.web.x5web

import com.tencent.smtt.export.external.interfaces.WebResourceError
import vip.irock.web.protocol.IWebResourceError

class X5WebResourceError(private val error: WebResourceError) :
    IWebResourceError {
    override fun getErrorCode(): Int {
        return error.errorCode
    }

    override fun getDescription(): CharSequence {
        return error.description
    }
}