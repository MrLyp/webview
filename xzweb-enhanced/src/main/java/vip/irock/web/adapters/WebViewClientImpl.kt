package vip.irock.web.adapters

import android.util.Log
import android.webkit.URLUtil
import vip.irock.web.bridge.NativeBridge
import vip.irock.web.cache.WebResourceCache
import vip.irock.web.protocol.IWebResourceRequest
import vip.irock.web.protocol.IWebResourceResponse
import vip.irock.web.protocol.IWebView
import vip.irock.web.protocol.IWebViewClient

class WebViewClientImpl : IWebViewClient {

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

    override fun onPageFinished(view: IWebView, url: String?) {
        NativeBridge.inject(view)
    }

    override fun shouldInterceptRequest(
        view: IWebView,
        request: IWebResourceRequest
    ): IWebResourceResponse? {
        return WebResourceCache.interceptRequest(request)
    }

    override fun shouldInterceptRequest(view: IWebView, url: String?): IWebResourceResponse? {
        return super.shouldInterceptRequest(view, url)
    }

    companion object {
        private const val INTENT_SCHEME = "intent://"
    }
}