package vip.irock.web.x5web

import com.tencent.smtt.export.external.interfaces.ClientCertRequest
import vip.irock.web.protocol.IClientCertRequest
import java.security.Principal
import java.security.PrivateKey
import java.security.cert.X509Certificate

class X5ClientCertRequest(private val request: ClientCertRequest) :
    IClientCertRequest {
    override fun getKeyTypes(): Array<String> {
        return request.keyTypes ?: Array<String>(0) { "" }
    }

    override fun getPrincipals(): Array<Principal> {
        return request.principals ?: Array<Principal>(0) { Principal { "" } }
    }

    override fun getHost(): String {
        return request.host
    }

    override fun getPort(): Int {
        return request.port
    }

    override fun proceed(var1: PrivateKey, var2: Array<X509Certificate>) {
        return request.proceed(var1, var2)
    }

    override fun ignore() {
        return request.ignore()
    }

    override fun cancel() {
        return request.cancel()
    }
}