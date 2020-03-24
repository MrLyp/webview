package vip.irock.web.protocol

import android.graphics.Bitmap
import android.os.Build
import android.os.Message
import android.view.KeyEvent
import androidx.annotation.RequiresApi

interface IWebViewClient {

    fun shouldOverrideUrlLoading(view: IWebView, url: String): Boolean {
        return false
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    fun shouldOverrideUrlLoading(view: IWebView, request: IWebResourceRequest): Boolean {
        return false
    }

    fun onPageStarted(view: IWebView, url: String?, favicon: Bitmap?) {

    }

    fun onPageFinished(view: IWebView, url: String?) {

    }

    fun onReceivedError(view: IWebView, errorCode: Int, description: String?, failingUrl: String?) {

    }

    fun onReceivedError(view: IWebView, request: IWebResourceRequest, error: IWebResourceError) {

    }

    fun onReceivedHttpError(
        view: IWebView,
        request: IWebResourceRequest,
        response: IWebResourceResponse
    ) {

    }

    fun onLoadResource(view: IWebView, var2: String?) {

    }

    fun shouldInterceptRequest(view: IWebView, request: IWebResourceRequest): IWebResourceResponse? {
        return null
    }

    fun shouldInterceptRequest(view: IWebView, url: String?): IWebResourceResponse? {
        return null
    }

    fun doUpdateVisitedHistory(view: IWebView, var2: String?, var3: Boolean) {

    }

    fun onFormResubmission(view: IWebView, var2: Message?, var3: Message?) {

    }

    fun onReceivedHttpAuthRequest(
        view: IWebView?,
        handler: IHttpAuthHandler?,
        host: String?,
        realm: String?
    ) {

    }

    fun onReceivedSslError(view: IWebView, handler: ISslErrorHandler, error: ISslError) {

    }

    fun onReceivedClientCertRequest(view: IWebView, certRequest: IClientCertRequest) {

    }

    fun onScaleChanged(view: IWebView, var2: Float, var3: Float) {

    }

    fun onUnhandledKeyEvent(view: IWebView, var2: KeyEvent?) {

    }

    fun shouldOverrideKeyEvent(view: IWebView, var2: KeyEvent?): Boolean {
        return false
    }


    @Deprecated("")
    fun onTooManyRedirects(view: IWebView, var2: Message?, var3: Message?) {

    }

    fun onReceivedLoginRequest(view: IWebView?, realm: String?, account: String?, args: String?) {

    }

    fun onPageCommitVisible(view: IWebView, var2: String?) {

    }

    companion object {
        val ERROR_UNKNOWN = -1
        val ERROR_HOST_LOOKUP = -2
        val ERROR_UNSUPPORTED_AUTH_SCHEME = -3
        val ERROR_AUTHENTICATION = -4
        val ERROR_PROXY_AUTHENTICATION = -5
        val ERROR_CONNECT = -6
        val ERROR_IO = -7
        val ERROR_TIMEOUT = -8
        val ERROR_REDIRECT_LOOP = -9
        val ERROR_UNSUPPORTED_SCHEME = -10
        val ERROR_FAILED_SSL_HANDSHAKE = -11
        val ERROR_BAD_URL = -12
        val ERROR_FILE = -13
        val ERROR_FILE_NOT_FOUND = -14
        val ERROR_TOO_MANY_REQUESTS = -15
        val INTERCEPT_BY_ISP = -16
    }
}
