package vip.irock.web.webkit

import android.graphics.Bitmap
import android.net.http.SslError
import android.os.Build
import android.os.Message
import android.view.KeyEvent
import android.webkit.*
import vip.irock.web.protocol.IWebView
import vip.irock.web.protocol.IWebViewClient

class WebkitWebViewClient(
    private val webViewProxy: IWebView,
    private val webViewClientDelegate: IWebViewClient
) : WebViewClient() {

    override fun shouldOverrideUrlLoading(view: WebView, var2: String): Boolean {
        return webViewClientDelegate.shouldOverrideUrlLoading(webViewProxy, var2)
    }

    override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest): Boolean {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            return false
        }
        return webViewClientDelegate.shouldOverrideUrlLoading(
            webViewProxy,
            WebkitWebResourceRequest(request)
        )
    }

    override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
        webViewClientDelegate.onPageStarted(webViewProxy, url, favicon)
    }

    override fun onPageFinished(view: WebView?, url: String?) {
        webViewClientDelegate.onPageFinished(webViewProxy, url)
    }

    override fun onReceivedError(
        view: WebView?,
        request: WebResourceRequest,
        error: WebResourceError
    ) {
        super.onReceivedError(view, request, error)
        webViewClientDelegate.onReceivedError(
            webViewProxy,
            WebkitWebResourceRequest(request),
            WebkitWebResourceError(error)
        )
    }

    override fun onReceivedError(
        view: WebView?,
        errorCode: Int,
        description: String?,
        failingUrl: String?
    ) {
        webViewClientDelegate.onReceivedError(webViewProxy, errorCode, description, failingUrl)
    }

    override fun onReceivedHttpError(
        view: WebView?,
        request: WebResourceRequest,
        errorResponse: WebResourceResponse
    ) {
        super.onReceivedHttpError(view, request, errorResponse)
        webViewClientDelegate.onReceivedHttpError(
            webViewProxy,
            WebkitWebResourceRequest(request),
            WebkitWebResourceResponse(errorResponse)
        )
    }

    override fun onLoadResource(view: WebView?, url: String?) {
        super.onLoadResource(view, url)
        webViewClientDelegate.onLoadResource(webViewProxy, url)
    }

    override fun shouldInterceptRequest(
        view: WebView?,
        request: WebResourceRequest
    ): WebResourceResponse? {
        val response = webViewClientDelegate.shouldInterceptRequest(
            webViewProxy,
            WebkitWebResourceRequest(request)
        )
        return WebResourceResponseFactory.createWebResourceResponse(
            response
        )
    }

    override fun shouldInterceptRequest(view: WebView?, url: String?): WebResourceResponse? {
        val response = webViewClientDelegate.shouldInterceptRequest(webViewProxy, url)
        return WebResourceResponseFactory.createWebResourceResponse(
            response
        )
    }

    override fun doUpdateVisitedHistory(view: WebView?, url: String?, isReload: Boolean) {
        super.doUpdateVisitedHistory(view, url, isReload)
        webViewClientDelegate.doUpdateVisitedHistory(webViewProxy, url, isReload)
    }

    override fun onFormResubmission(view: WebView?, dontResend: Message?, resend: Message?) {
        super.onFormResubmission(view, dontResend, resend)
        webViewClientDelegate.onFormResubmission(webViewProxy, dontResend, resend)
    }

    override fun onReceivedHttpAuthRequest(
        view: WebView?,
        handler: HttpAuthHandler,
        host: String?,
        realm: String?
    ) {
        super.onReceivedHttpAuthRequest(view, handler, host, realm)
        webViewClientDelegate.onReceivedHttpAuthRequest(
            webViewProxy,
            WebkitHttpAuthHandler(handler),
            host,
            realm
        )
    }

    override fun onReceivedSslError(view: WebView?, handler: SslErrorHandler, error: SslError) {
        super.onReceivedSslError(view, handler, error)
        webViewClientDelegate.onReceivedSslError(
            webViewProxy,
            WebkitSslErrorHandler(handler),
            WebkitSslError(error)
        )
    }

    override fun onReceivedClientCertRequest(view: WebView?, request: ClientCertRequest) {
        super.onReceivedClientCertRequest(view, request)
        webViewClientDelegate.onReceivedClientCertRequest(
            webViewProxy,
            WebkitClientCertRequest(request)
        )
    }

    override fun onScaleChanged(view: WebView?, oldScale: Float, newScale: Float) {
        super.onScaleChanged(view, oldScale, newScale)
        webViewClientDelegate.onScaleChanged(webViewProxy, oldScale, newScale)
    }

    override fun onUnhandledKeyEvent(view: WebView?, event: KeyEvent?) {
        super.onUnhandledKeyEvent(view, event)
        webViewClientDelegate.onUnhandledKeyEvent(webViewProxy, event)
    }

    override fun shouldOverrideKeyEvent(view: WebView?, event: KeyEvent?): Boolean {
        return webViewClientDelegate.shouldOverrideKeyEvent(webViewProxy, event)
    }

    override fun onTooManyRedirects(view: WebView?, cancelMsg: Message?, continueMsg: Message?) {
        super.onTooManyRedirects(view, cancelMsg, continueMsg)
        webViewClientDelegate.onTooManyRedirects(webViewProxy, cancelMsg, continueMsg)
    }

    override fun onReceivedLoginRequest(
        view: WebView?,
        realm: String?,
        account: String?,
        args: String?
    ) {
        super.onReceivedLoginRequest(view, realm, account, args)
        webViewClientDelegate.onReceivedLoginRequest(webViewProxy, realm, account, args)
    }

    override fun onPageCommitVisible(view: WebView?, url: String?) {
        super.onPageCommitVisible(view, url)
        webViewClientDelegate.onPageCommitVisible(webViewProxy, url)
    }

}