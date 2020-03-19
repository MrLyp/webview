package vip.irock.web.webkit

import android.net.Uri
import android.os.Build
import android.webkit.WebResourceRequest
import androidx.annotation.RequiresApi
import vip.irock.web.protocol.IWebResourceRequest

class WebkitWebResourceRequest(private val request: WebResourceRequest) :
    IWebResourceRequest {
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun getUrl(): Uri {
        return request.url
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun isForMainFrame(): Boolean {
        return request.isForMainFrame
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun isRedirect(): Boolean {
        return request.isRedirect
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun getMethod(): String {
        return request.method
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun getRequestHeaders(): Map<String, String> {
        return request.requestHeaders
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun hasGesture(): Boolean {
        return request.hasGesture()
    }
}