package vip.irock.web.webkit

import android.os.Build
import android.webkit.WebResourceResponse
import vip.irock.web.protocol.IWebResourceResponse

object WebResourceResponseFactory {

    fun createWebResourceResponse(response: IWebResourceResponse?): WebResourceResponse? {
        if (response == null)
            return null
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            WebResourceResponse(response.getMimeType(), response.getEncoding(), response.getStatusCode(), response.getReasonPhrase(), response.getResponseHeaders(), response.getData())
        } else {
            WebResourceResponse(response.getMimeType(), response.getEncoding(), response.getData())
        }
    }
}