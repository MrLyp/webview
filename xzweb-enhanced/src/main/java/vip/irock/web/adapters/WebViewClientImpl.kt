package vip.irock.web.adapters

import android.graphics.Bitmap
import android.os.Message
import android.util.Log
import android.webkit.URLUtil
import vip.irock.web.bridge.NativeBridge
import vip.irock.web.cache.WebResourceCache
import vip.irock.web.protocol.*

class WebViewClientImpl(private val viewHost: IViewHost) : IWebViewClient {

    private val mSecuritySupport = SecuritySupport(viewHost)

    override fun shouldOverrideUrlLoading(view: IWebView, url: String): Boolean {
        Log.d("lyp", "url = $url")
        if ("about:blank" == url) {
            return true
        }
        if (URLUtil.isNetworkUrl(url)) {
            view.loadUrl(url)
            return true
        }
        // intent
        if (url.startsWith(INTENT_SCHEME)) {
            WebRouters.processIntentUrl(view.getView().context, url)
            return true
        }

        WebRouters.processCommonLink(view.getView().context, url)
        return true
    }

    override fun shouldOverrideUrlLoading(view: IWebView, request: IWebResourceRequest): Boolean {
        return this.shouldOverrideUrlLoading(view, request.getUrl().toString())
    }

    override fun onPageStarted(view: IWebView, url: String?, favicon: Bitmap?) {
        super.onPageStarted(view, url, favicon)
        viewHost.onPageStarted()
    }

    override fun onPageFinished(view: IWebView, url: String?) {
        NativeBridge.inject(view)
        viewHost.onPageFinished(url)
    }

    override fun shouldInterceptRequest(
        view: IWebView,
        request: IWebResourceRequest
    ): IWebResourceResponse? {
        return WebResourceCache.interceptRequest(request)
    }

    override fun shouldInterceptRequest(view: IWebView, url: String): IWebResourceResponse? {
        return WebResourceCache.interceptRequest(url)
    }

    override fun onReceivedSslError(view: IWebView, handler: ISslErrorHandler, error: ISslError) {
        super.onReceivedSslError(view, handler, error)
        mSecuritySupport.onReceivedSslError(handler, error)
    }

    override fun onFormResubmission(view: IWebView, var2: Message?, var3: Message?) {
        var2?.sendToTarget()
    }
    companion object {
        private const val INTENT_SCHEME = "intent://"
    }
}