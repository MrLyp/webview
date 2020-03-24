package vip.irock.web.cache

import vip.irock.web.protocol.IWebResourceResponse
import java.io.InputStream

class WebResourceResponseImpl(
    private var mimeType: String,
    private var encoding: String,
    private var code: Int,
    private var reason: String,
    private var headers: Map<String, String>,
    private var data: InputStream
) : IWebResourceResponse {
    override fun setMimeType(var1: String) {
        mimeType = var1
    }

    override fun getMimeType(): String {
        return mimeType
    }

    override fun setEncoding(var1: String) {
        encoding = var1
    }

    override fun getEncoding(): String {
        return encoding
    }

    override fun setStatusCodeAndReasonPhrase(var1: Int, var2: String) {
        code = var1
        reason = var2
    }

    override fun getStatusCode(): Int {
        return code
    }

    override fun getReasonPhrase(): String {
        return reason
    }

    override fun setResponseHeaders(var1: Map<String, String>) {
        headers = var1
    }

    override fun getResponseHeaders(): Map<String, String> {
        return headers
    }

    override fun setData(var1: InputStream) {
        data = var1
    }

    override fun getData(): InputStream {
        return data
    }
}