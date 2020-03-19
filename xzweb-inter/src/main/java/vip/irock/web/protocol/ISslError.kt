package vip.irock.web.protocol

import android.net.http.SslCertificate

interface ISslError {
    fun getCertificate(): SslCertificate

    fun addError(var1: Int): Boolean

    fun hasError(var1: Int): Boolean

    fun getPrimaryError(): Int

    fun getUrl(): String
}