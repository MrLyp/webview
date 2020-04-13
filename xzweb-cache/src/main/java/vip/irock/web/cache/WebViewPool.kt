package vip.irock.web.cache

import android.content.Context
import android.content.MutableContextWrapper
import android.os.Looper
import androidx.core.util.Pools
import vip.irock.web.protocol.IWebView
import vip.irock.web.protocol.IWebViewFactory


object WebViewPool {

    private const val MAX_POOL_SIZE = 2

    private val sPool: Pools.Pool<IWebView> = Pools.SimplePool<IWebView>(MAX_POOL_SIZE)

    private lateinit var mViewFactory: IWebViewFactory

    fun setViewFactory(factory: IWebViewFactory) {
        mViewFactory = factory
    }

    fun prepare(context: Context) {
        release(acquire(context))
    }

    fun acquire(context: Context): IWebView {
        checkThread()
        val view = sPool.acquire()
        if (view == null) {
            val wrapper = MutableContextWrapper(context)
            return mViewFactory.createWebView(wrapper)
        } else {
            val wrapper = view.getContext() as MutableContextWrapper
            wrapper.baseContext = context
            view.loadUrl("about:blank")
            return view
        }
    }

    private fun checkThread() {
        if (Looper.myLooper() != Looper.getMainLooper())
            throw IllegalThreadStateException("WebViewPool must be called from main thread")
    }

    fun release(webView: IWebView) {
        checkThread()
        webView.clearHistory()
        webView.stopLoading()
        webView.clearCache(true)
        val wrapper = webView.getContext() as MutableContextWrapper
        wrapper.baseContext = wrapper.applicationContext
        sPool.release(webView)
    }
}