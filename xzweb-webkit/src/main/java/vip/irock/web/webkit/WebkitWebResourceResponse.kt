package vip.irock.web.webkit

import android.os.Build
import android.webkit.WebResourceResponse
import androidx.annotation.RequiresApi
import vip.irock.web.protocol.IWebResourceResponse
import java.io.InputStream

class WebkitWebResourceResponse(private val response: WebResourceResponse) :
    IWebResourceResponse {
    override fun setMimeType(var1: String) {
        response.mimeType = var1
    }

    override fun getMimeType(): String {
        return response.mimeType
    }

    override fun setEncoding(var1: String) {
        response.encoding = var1
    }

    override fun getEncoding(): String {
        return response.encoding
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun setStatusCodeAndReasonPhrase(var1: Int, var2: String) {
        response.setStatusCodeAndReasonPhrase(var1, var2)
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun getStatusCode(): Int {
        return response.statusCode
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun getReasonPhrase(): String {
        return response.reasonPhrase
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun setResponseHeaders(var1: Map<String, String>) {
        response.responseHeaders = var1
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun getResponseHeaders(): Map<String, String> {
        return response.responseHeaders
    }

    override fun setData(var1: InputStream) {
        response.data = var1
    }

    override fun getData(): InputStream {
        return response.data
    }
}