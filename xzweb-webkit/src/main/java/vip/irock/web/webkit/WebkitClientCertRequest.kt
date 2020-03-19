package vip.irock.web.webkit

import android.os.Build
import android.webkit.ClientCertRequest
import androidx.annotation.RequiresApi
import vip.irock.web.protocol.IClientCertRequest
import java.security.Principal
import java.security.PrivateKey
import java.security.cert.X509Certificate

class WebkitClientCertRequest(private val request: ClientCertRequest) :
    IClientCertRequest {
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun getKeyTypes(): Array<String> {
        return request.keyTypes ?: Array<String>(0) { "" }
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun getPrincipals(): Array<Principal> {
        return request.principals ?: Array<Principal>(0) { Principal { "" } }
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun getHost(): String {
        return request.host
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun getPort(): Int {
        return request.port
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun proceed(var1: PrivateKey, var2: Array<X509Certificate>) {
        return request.proceed(var1, var2)
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun ignore() {
        return request.ignore()
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun cancel() {
        return request.cancel()
    }
}