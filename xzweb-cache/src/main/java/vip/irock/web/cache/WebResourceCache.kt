package vip.irock.web.cache

import android.content.Context
import android.view.View
import okhttp3.OkHttpClient
import vip.irock.web.protocol.IWebResourceRequest
import vip.irock.web.protocol.IWebResourceResponse
import vip.irock.web.protocol.IWebView
import vip.irock.web.protocol.IWebViewClient

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

    private fun buildRequestChain(): WebResourceChain {
        val chain = WebResourceChain()
        chain.addInterceptor(RemoteCacheInterceptor(mCacheConfig, mOkHttpBuilder))
        chain.addInterceptor(LocalAssetsInterceptor())
        chain.addInterceptor(LocalResourceInterceptor())
        return chain
    }

    fun preload(context: Context, url: String) {
        val webView = WebViewPool.acquire(context)
        webView.setWebViewClient(object : IWebViewClient {
            override fun shouldInterceptRequest(
                view: IWebView,
                request: IWebResourceRequest
            ): IWebResourceResponse? {
                return interceptRequest(request)
            }

            override fun onPageFinished(view: IWebView, url: String?) {
                super.onPageFinished(view, url)
                (view as View).post {
                    recycle(webView)
                }
            }
        })
    }

    private fun recycle(webView: IWebView) {
        WebViewPool.release(webView)
    }
}