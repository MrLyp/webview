package vip.irock.web.adapters

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.webkit.WebSettings
import android.widget.FrameLayout
import android.widget.ProgressBar
import android.widget.TextView
import com.xzweb.android.enhanced.R
import vip.irock.web.bridge.NativeBridge
import vip.irock.web.cache.WebViewPool
import vip.irock.web.protocol.IWebSettings
import vip.irock.web.protocol.IWebView

class WebViewHost @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr), IViewHost {

    private val mWebView: IWebView

    private val mTitleTv: TextView

    private val mProgress: ProgressBar

    init {
        LayoutInflater.from(context).inflate(R.layout.widget_webview_host, this)
        mWebView = WebViewPool.acquire(context)
        findViewById<FrameLayout>(R.id.webView_container).addView(mWebView as View)
        mWebView.setWebChromeClient(WebChromeClientImpl(this))
        mWebView.setWebViewClient(WebViewClientImpl(this))
        mWebView.setDownloadListener(DownloadSupport(this))
        mWebView.setVerticalScrollbarOverlay(false)
        mWebView.setHorizontalScrollbarOverlay(false)

        setupWebSettings(context, mWebView.getSettings())
        NativeBridge.init(context)

        mTitleTv = findViewById(R.id.title)

        mProgress = findViewById(R.id.progress)

        findViewById<View>(R.id.btn_back).setOnClickListener {
            if (onBackPressed().not()) {
                getActivity().finish()
            }
        }

        findViewById<View>(R.id.btn_close).setOnClickListener {
            getActivity().finish()
        }
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun setupWebSettings(context: Context, webSetting: IWebSettings) {
        webSetting.setJavaScriptEnabled(true)
        webSetting.setJavaScriptCanOpenWindowsAutomatically(true)
        // 通过 file url 加载的 Javascript 读取其他的本地文件 .建议关闭
        webSetting.setAllowFileAccessFromFileURLs(true)
        // 允许通过 file url 加载的 Javascript 可以访问其他的源，包括其他的文件和 http，https 等其他的源
        webSetting.setAllowUniversalAccessFromFileURLs(true)
        // 允许加载本地文件html  file协议
        webSetting.setAllowFileAccess(true)
        webSetting.setLayoutAlgorithm(IWebSettings.LayoutAlgorithm.SINGLE_COLUMN)

        //设置自适应屏幕
        webSetting.setUseWideViewPort(true)
        // 缩放至屏幕的大小
        webSetting.setLoadWithOverviewMode(true)
        //设置是否支持缩放，false，默认为true。
        webSetting.setSupportZoom(false)
        //设置是否显示缩放工具，默认为false
        webSetting.setBuiltInZoomControls(false)
        //异步加载图片
        webSetting.setLoadsImagesAutomatically(true)
        // 是否阻塞加载网络图片  协议http or https
        webSetting.setBlockNetworkImage(false)
        //应用可以有数据库
        webSetting.setDatabaseEnabled(true)
        webSetting.setAppCacheEnabled(true)
        webSetting.setSupportMultipleWindows(false)
//        //设置数据库缓存路径
        webSetting.setDatabasePath(context.cacheDir.absolutePath)
        //设置  Application Caches 缓存目录
        webSetting.setAppCachePath(context.cacheDir.absolutePath)
        //设置定位的数据库路径
        webSetting.setGeolocationDatabasePath(context.cacheDir.absolutePath)
        //false 禁止webview上面控件获取焦点(黄色边框)
        webSetting.setNeedInitialFocus(true)
        webSetting.setDefaultTextEncodingName("utf-8")//设置编码格式
        webSetting.setDefaultFontSize(16)
        webSetting.setMinimumFontSize(12)//设置 WebView 支持的最小字体大小，默认为 8
        //启用地理定位
        webSetting.setGeolocationEnabled(true)
        //设置可以使用localStorage
        webSetting.setDomStorageEnabled(true)
        //关闭webview自动保存密码的功能
        webSetting.setSavePassword(false)
        webSetting.setCacheMode(IWebSettings.LOAD_CACHE_ELSE_NETWORK)
        webSetting.setMixedContentMode(WebSettings.MIXED_CONTENT_COMPATIBILITY_MODE)
    }

    override fun getActivity(): Activity {
        return context as Activity
    }

    override fun onReceivedTitle(title: String) {
        mTitleTv.text = title
    }

    override fun onPageStarted() {
        mProgress.visibility = View.VISIBLE
    }

    override fun onPageFinished(url: String?) {
        mProgress.visibility = View.INVISIBLE
    }

    override fun onProgressChanged(progress: Int) {
        mProgress.progress = progress.coerceAtLeast(1).coerceAtMost(99)
    }

    override fun getWebView(): IWebView {
        return mWebView
    }

    override fun release() {
        val view = mWebView as View
        findViewById<FrameLayout>(R.id.webView_container).removeView(view)
        WebViewPool.release(mWebView)
    }

    override fun onBackPressed(): Boolean {
        if (mWebView.canGoBack()) {
            findViewById<View>(R.id.btn_close).visibility = View.VISIBLE
            mWebView.goBack()
            return true
        }
        return false
    }

    override fun onFileChooserResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == FileChooserSupport.FILECHOOSER_RESULTCODE)
            FileChooserSupport.onFileChooserResult(resultCode, data)
    }
}