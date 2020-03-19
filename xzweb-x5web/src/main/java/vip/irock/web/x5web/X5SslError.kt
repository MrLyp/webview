package vip.irock.web.x5web

import android.net.http.SslCertificate
import com.tencent.smtt.export.external.interfaces.SslError
import vip.irock.web.protocol.ISslError

class X5SslError(private val error: SslError) :
    ISslError {
    override fun addError(var1: Int): Boolean {
        return error.addError(var1)
    }

    override fun hasError(var1: Int): Boolean {
        return error.hasError(var1)
    }

    override fun getPrimaryError(): Int {
        return error.primaryError
    }

    override fun getUrl(): String {
        return error.url
    }

    override fun getCertificate(): SslCertificate {
        return error.certificate
    }
}