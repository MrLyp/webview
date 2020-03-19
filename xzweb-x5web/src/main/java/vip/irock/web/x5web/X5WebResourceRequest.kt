package vip.irock.web.x5web

import android.net.Uri
import com.tencent.smtt.export.external.interfaces.WebResourceRequest
import vip.irock.web.protocol.IWebResourceRequest

class X5WebResourceRequest(private val request: WebResourceRequest) :
    IWebResourceRequest {
    override fun getUrl(): Uri {
        return request.url
    }

    override fun isForMainFrame(): Boolean {
        return request.isForMainFrame
    }

    override fun isRedirect(): Boolean {
        return request.isRedirect
    }

    override fun getMethod(): String {
        return request.method
    }

    override fun getRequestHeaders(): Map<String, String> {
        return request.requestHeaders
    }

    override fun hasGesture(): Boolean {
        return request.hasGesture()
    }
}