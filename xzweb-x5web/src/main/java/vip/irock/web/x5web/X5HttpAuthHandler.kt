package vip.irock.web.x5web

import com.tencent.smtt.export.external.interfaces.HttpAuthHandler
import vip.irock.web.protocol.IHttpAuthHandler

class X5HttpAuthHandler(private val handler: HttpAuthHandler) :
    IHttpAuthHandler {
    override fun proceed(var1: String, var2: String) {
        handler.proceed(var1, var2)
    }

    override fun cancel() {
        handler.cancel()
    }

    override fun useHttpAuthUsernamePassword(): Boolean {
        return handler.useHttpAuthUsernamePassword()
    }
}