package vip.irock.web.cache

import android.content.Context
import android.net.Uri
import android.util.Log
import android.view.View
import okhttp3.OkHttpClient
import vip.irock.web.protocol.*
import java.util.HashMap

object WebResourceCache {

    private val mRequestChain: WebResourceChain by lazy(LazyThreadSafetyMode.SYNCHRONIZED) { buildRequestChain() }

    private lateinit var mCacheConfig: CacheConfig

    private lateinit var mOkHttpBuilder: OkHttpClient.Builder

    fun setCacheConfig(config: CacheConfig) {
        mCacheConfig = config
    }

    fun setOkHttpBuilder(builder: OkHttpClient.Builder) {
        mOkHttpBuilder = builder
    }

    fun interceptRequest(request: IWebResourceRequest): IWebResourceResponse? {
        return mRequestChain.process(request)
    }

    fun interceptRequest(url: String): IWebResourceResponse? {
        return mRequestChain.process(object : IWebResourceRequest {
            override fun getUrl(): Uri {
                return Uri.parse(url)
            }

            override fun isForMainFrame(): Boolean {
                return true
            }

            override fun isRedirect(): Boolean {
                return false
            }

            override fun getMethod(): String {
                return "GET"
            }

            override fun getRequestHeaders(): Map<String, String> {
                return HashMap()
            }

            override fun hasGesture(): Boolean {
                return false
            }

        })
    }

    private fun buildRequestChain(): WebResourceChain {
        val chain = WebResourceChain()
        chain.addInterceptor(RemoteCacheInterceptor(mCacheConfig, mOkHttpBuilder))
        chain.addInterceptor(LocalAssetsInterceptor())
        chain.addInterceptor(LocalResourceInterceptor())
        return chain
    }

    /**
     * 了解下来发现预加载是一个比较棘手的事情。当Web Beacon存在时，预加载会导致页面PV数量翻倍。
     * 当开启JavaScript时预加载会导致页面JS代码被执行,PV也可能都会翻倍。
     * 所以可能最好是zip下载资源内置？
     *
     * @param context
     * @param url
     */
    fun preload(context: Context, url: String) {
        val webView = WebViewPool.acquire(context)
        webView.setWebViewClient(object : IWebViewClient {
            override fun shouldInterceptRequest(
                view: IWebView,
                request: IWebResourceRequest
            ): IWebResourceResponse? {
                return interceptRequest(request)
            }

            override fun shouldInterceptRequest(
                view: IWebView,
                url: String
            ): IWebResourceResponse? {
                return interceptRequest(url)
            }

            override fun onPageFinished(view: IWebView, url: String?) {
                super.onPageFinished(view, url)
                (view as View).post {
                    WebViewPool.release(webView)
                }
            }
        })
        // during preCache, javascript should be disabled
        webView.getSettings().setJavaScriptEnabled(false)
        webView.loadUrl(url)
    }
}