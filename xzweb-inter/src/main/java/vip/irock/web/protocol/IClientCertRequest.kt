package vip.irock.web.protocol

import java.security.Principal
import java.security.PrivateKey
import java.security.cert.X509Certificate

interface IClientCertRequest {
    fun getKeyTypes(): Array<String>

    fun getPrincipals(): Array<Principal>

    fun getHost(): String

    fun getPort(): Int

    fun proceed(var1: PrivateKey, var2: Array<X509Certificate>)

    fun ignore()

    fun cancel()
}