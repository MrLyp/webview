package vip.irock.web

import android.app.Application
import android.content.Context
import com.tencent.smtt.sdk.QbSdk
import okhttp3.OkHttpClient
import vip.irock.web.cache.CacheConfig
import vip.irock.web.cache.WebResourceCache
import vip.irock.web.cache.WebViewPool
import vip.irock.web.protocol.IWebView
import vip.irock.web.protocol.IWebViewFactory
import vip.irock.web.webkit.WebkitWebView
import java.util.concurrent.TimeUnit

class MApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        QbSdk.preInit(this, null)
        WebViewPool.setViewFactory(object : IWebViewFactory {
            override fun createWebView(context: Context): IWebView {
                return WebkitWebView(context)
            }
        })
        WebViewPool.prepare(this)
        WebResourceCache.setCacheConfig(CacheConfig.build(this){

        })
        WebResourceCache.setOkHttpBuilder(OkHttpClient.Builder().connectTimeout(30, TimeUnit.SECONDS).readTimeout(30, TimeUnit.SECONDS))
    }
}