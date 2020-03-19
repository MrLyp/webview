package vip.irock.web.x5web

import com.tencent.smtt.export.external.interfaces.WebResourceResponse
import vip.irock.web.protocol.IWebResourceResponse
import java.io.InputStream

class X5WebResourceResponse(private val response: WebResourceResponse) :
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

    override fun setStatusCodeAndReasonPhrase(var1: Int, var2: String) {
        response.setStatusCodeAndReasonPhrase(var1, var2)
    }

    override fun getStatusCode(): Int {
        return response.statusCode
    }

    override fun getReasonPhrase(): String {
        return response.reasonPhrase
    }

    override fun setResponseHeaders(var1: Map<String, String>) {
        response.responseHeaders = var1
    }

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