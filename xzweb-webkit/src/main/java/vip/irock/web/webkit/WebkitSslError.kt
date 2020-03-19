package vip.irock.web.webkit

import android.net.http.SslCertificate
import android.net.http.SslError
import vip.irock.web.protocol.ISslError

class WebkitSslError(private val error: SslError) :
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