package vip.irock.web.webkit

import android.webkit.HttpAuthHandler
import vip.irock.web.protocol.IHttpAuthHandler

class WebkitHttpAuthHandler(private val handler: HttpAuthHandler) :
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