package vip.irock.web.x5web

import android.graphics.Bitmap
import android.os.Build
import android.os.Message
import android.view.KeyEvent
import com.tencent.smtt.export.external.interfaces.*
import com.tencent.smtt.sdk.WebView
import com.tencent.smtt.sdk.WebViewClient
import vip.irock.web.protocol.IWebView
import vip.irock.web.protocol.IWebViewClient

class X5WebViewClient(
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
            X5WebResourceRequest(request)
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
            X5WebResourceRequest(request),
            X5WebResourceError(error)
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
            X5WebResourceRequest(request),
            X5WebResourceResponse(errorResponse)
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
            X5WebResourceRequest(request)
        )
            ?: return null
        return WebResourceResponse(
            response.getMimeType(),
            response.getEncoding(),
            response.getStatusCode(),
            response.getReasonPhrase(),
            response.getResponseHeaders(),
            response.getData()
        )
    }

    override fun shouldInterceptRequest(view: WebView?, url: String): WebResourceResponse? {
        val response = webViewClientDelegate.shouldInterceptRequest(webViewProxy, url) ?: return null
        return WebResourceResponse(
            response.getMimeType(),
            response.getEncoding(),
            response.getStatusCode(),
            response.getReasonPhrase(),
            response.getResponseHeaders(),
            response.getData()
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
            X5HttpAuthHandler(handler),
            host,
            realm
        )
    }

    override fun onReceivedSslError(view: WebView?, handler: SslErrorHandler, error: SslError) {
        super.onReceivedSslError(view, handler, error)
        webViewClientDelegate.onReceivedSslError(
            webViewProxy,
            X5SslErrorHandler(handler),
            X5SslError(error)
        )
    }

    override fun onReceivedClientCertRequest(view: WebView?, request: ClientCertRequest) {
        super.onReceivedClientCertRequest(view, request)
        webViewClientDelegate.onReceivedClientCertRequest(
            webViewProxy,
            X5ClientCertRequest(request)
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