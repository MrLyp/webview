package vip.irock.web.adapters

import android.webkit.URLUtil
import vip.irock.web.bridge.NativeBridge
import vip.irock.web.protocol.IWebView
import vip.irock.web.protocol.IWebViewClient

class WebViewClientImpl : IWebViewClient {
    override fun shouldOverrideUrlLoading(view: IWebView, url: String): Boolean {
        if (URLUtil.isNetworkUrl(url)) {
            view.loadUrl(url)
            return true
        }

        return false
    }

    override fun onPageFinished(view: IWebView, url: String?) {
        NativeBridge.inject(view)
    }
}