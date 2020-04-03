package vip.irock.web.adapters

import android.graphics.Bitmap
import android.net.Uri
import android.util.Log
import android.view.View
import android.webkit.ValueCallback
import vip.irock.web.bridge.NativeBridge
import vip.irock.web.protocol.*

class WebChromeClientImpl(private val viewHost: IViewHost) : IWebChromeClient {

    private val mVideoSupport = VideoSupport(viewHost.getActivity())

    private val mSecuritySupport = SecuritySupport(viewHost)

    override fun getDefaultVideoPoster(): Bitmap? {
        return Bitmap.createBitmap(10, 10, Bitmap.Config.ARGB_8888)
    }

    override fun onJsPrompt(
        view: IWebView,
        url: String,
        message: String,
        defaultValue: String,
        result: IJsPromptResult
    ): Boolean {
        if (NativeBridge.handleMessage(view, message)) {
            result.cancel()
            return true
        }
        return false
    }

    override fun onConsoleMessage(var1: IConsoleMessage): Boolean {
        val formatMessage = "line ${var1.lineNumber()},${var1.message()}"
        when (var1.messageLevel()) {
            IConsoleMessage.MessageLevel.TIP -> {
                Log.i(var1.sourceId(), formatMessage)
            }
            IConsoleMessage.MessageLevel.WARNING -> {
                Log.w(var1.sourceId(), formatMessage)
            }
            IConsoleMessage.MessageLevel.ERROR -> {
                Log.e(var1.sourceId(), formatMessage)
            }
            else -> {
                Log.d(var1.sourceId(), formatMessage)
            }
        }
        return true
    }

    override fun onConsoleMessage(var1: String, var2: Int, var3: String) {
        super.onConsoleMessage(var1, var2, var3)
        Log.d(var1, "line $var2,$var3")
    }

    override fun onShowCustomView(view: View, callback: IWebChromeClient.ICustomViewCallback) {
        super.onShowCustomView(view, callback)
        mVideoSupport.onShowCustomView(view, callback)
    }

    override fun onShowCustomView(
        view: View,
        requestedOrientation: Int,
        callback: IWebChromeClient.ICustomViewCallback
    ) {
        super.onShowCustomView(view, requestedOrientation, callback)
        mVideoSupport.onShowCustomView(view, callback, requestedOrientation)
    }

    override fun onHideCustomView() {
        super.onHideCustomView()
        mVideoSupport.onHideCustomView()
    }

    override fun onProgressChanged(var1: IWebView, progress: Int) {
        super.onProgressChanged(var1, progress)
        viewHost.onProgressChanged(progress)
    }

    override fun onReceivedTitle(var1: IWebView, title: String) {
        super.onReceivedTitle(var1, title)
        viewHost.onReceivedTitle(title)
    }

    override fun onGeolocationPermissionsHidePrompt() {
        super.onGeolocationPermissionsHidePrompt()
        mSecuritySupport.onGeolocationPermissionsHidePrompt()
    }

    override fun onGeolocationPermissionsShowPrompt(
        origin: String,
        callback: GeolocationPermissionsCallback
    ) {
        super.onGeolocationPermissionsShowPrompt(origin, callback)
        mSecuritySupport.onGeolocationPermissionsShowPrompt(origin, callback)
    }

    override fun onShowFileChooser(
        webView: IWebView,
        filePathCallback: ValueCallback<Array<Uri>>,
        fileChooserParams: IWebChromeClient.FileChooserParams
    ): Boolean {
        return FileChooserSupport.onShowFileChooser(viewHost, filePathCallback, fileChooserParams)
    }
}