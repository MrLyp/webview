package vip.irock.web

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewStub
import com.xzweb.android.R
import okhttp3.OkHttpClient
import vip.irock.web.adapters.WebDelegate
import vip.irock.web.adapters.XZRuntime
import vip.irock.web.cache.CacheConfig
import vip.irock.web.cache.WebResourceCache
import vip.irock.web.protocol.IWebView
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {

    private lateinit var mWebView: IWebView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val viewStub: ViewStub = findViewById(R.id.view_stub)
        if (XZRuntime.isX5Enabled()) {
            viewStub.layoutResource = R.layout.widget_x5
        } else {
            viewStub.layoutResource = R.layout.widget_webkit
        }
        mWebView = viewStub.inflate() as IWebView
        WebResourceCache.setCacheConfig(CacheConfig.build(this){

        })
        WebResourceCache.setOkHttpBuilder(OkHttpClient.Builder().connectTimeout(30, TimeUnit.SECONDS).readTimeout(30, TimeUnit.SECONDS))
        WebDelegate.takeOver(this, mWebView)
//        mWebView.loadUrl("file:///android_asset/demo.html")
        mWebView.loadUrl("https://www.jianshu.com/")
    }

    override fun onResume() {
        super.onResume()
        mWebView.onResume()
    }

    override fun onPause() {
        super.onPause()
        mWebView.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
        mWebView.destroy()
    }

    override fun onBackPressed() {
        if (mWebView.canGoBack())
            mWebView.goBack()
        else
            super.onBackPressed()
    }
}
