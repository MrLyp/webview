package vip.irock.web.cache

import okhttp3.OkHttpClient
import vip.irock.web.protocol.IWebResourceRequest
import vip.irock.web.protocol.IWebResourceResponse

object WebResourceCache {

    private val mRequestChain: WebResourceChain by lazy { buildRequestChain() }

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
        chain.addInterceptor(CacheInterceptor(mCacheConfig, mOkHttpBuilder))
        return chain
    }
}