package vip.irock.web.protocol

import android.net.Uri

interface IWebResourceRequest {
    fun getUrl(): Uri

    fun isForMainFrame(): Boolean

    fun isRedirect(): Boolean

    fun getMethod(): String

    fun getRequestHeaders(): Map<String, String>

    fun hasGesture(): Boolean
}