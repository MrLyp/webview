package vip.irock.web.x5web

import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Message
import android.view.View
import com.tencent.smtt.export.external.interfaces.ConsoleMessage
import com.tencent.smtt.export.external.interfaces.IX5WebChromeClient
import com.tencent.smtt.export.external.interfaces.JsPromptResult
import com.tencent.smtt.export.external.interfaces.JsResult
import com.tencent.smtt.sdk.ValueCallback
import com.tencent.smtt.sdk.WebChromeClient
import com.tencent.smtt.sdk.WebView
import vip.irock.web.protocol.GeolocationPermissionsCallback
import vip.irock.web.protocol.IWebChromeClient
import vip.irock.web.protocol.IWebView

class X5WebChromeClient(
    private val webViewProxy: IWebView,
    private val webChromeClientDelegate: IWebChromeClient
) :
    WebChromeClient() {


    override fun onProgressChanged(view: WebView, newProgress: Int) {
        webChromeClientDelegate.onProgressChanged(webViewProxy, newProgress)
    }


    override fun onReceivedTitle(view: WebView, title: String) {
        webChromeClientDelegate.onReceivedTitle(webViewProxy, title)
    }

    override fun onReceivedIcon(view: WebView, icon: Bitmap) {
        webChromeClientDelegate.onReceivedIcon(webViewProxy, icon)
    }

    override fun onReceivedTouchIconUrl(
        view: WebView, url: String,
        precomposed: Boolean
    ) {
        webChromeClientDelegate.onReceivedTouchIconUrl(webViewProxy, url, precomposed)
    }

    override fun onShowCustomView(view: View, callback: IX5WebChromeClient.CustomViewCallback) {
        webChromeClientDelegate.onShowCustomView(
            view,
            object : IWebChromeClient.ICustomViewCallback {
                override fun onCustomViewHidden() {
                    callback.onCustomViewHidden()
                }
            })
    }

    override fun onShowCustomView(
        view: View, requestedOrientation: Int,
        callback: IX5WebChromeClient.CustomViewCallback
    ) {
        webChromeClientDelegate.onShowCustomView(
            view,
            requestedOrientation,
            object : IWebChromeClient.ICustomViewCallback {
                override fun onCustomViewHidden() {
                    callback.onCustomViewHidden()
                }
            })
    }

    override fun onHideCustomView() {
        webChromeClientDelegate.onHideCustomView()
    }

    override fun onCreateWindow(
        view: WebView, isDialog: Boolean,
        isUserGesture: Boolean, resultMsg: Message
    ): Boolean {
        return webChromeClientDelegate.onCreateWindow(
            webViewProxy,
            isDialog,
            isUserGesture,
            resultMsg
        )
    }

    override fun onRequestFocus(view: WebView) {
        webChromeClientDelegate.onRequestFocus(webViewProxy)
    }

    override fun onCloseWindow(window: WebView) {
        webChromeClientDelegate.onCloseWindow(webViewProxy)
    }

    override fun onJsAlert(
        view: WebView, url: String, message: String,
        result: JsResult
    ): Boolean {
        return webChromeClientDelegate.onJsAlert(webViewProxy, url, message,
            X5JsResult(result)
        )
    }

    override fun onJsConfirm(
        view: WebView, url: String, message: String,
        result: JsResult
    ): Boolean {
        return webChromeClientDelegate.onJsConfirm(
            webViewProxy,
            url,
            message,
            X5JsResult(result)
        )
    }

    override fun onJsPrompt(
        view: WebView, url: String, message: String,
        defaultValue: String, result: JsPromptResult
    ): Boolean {
        return webChromeClientDelegate.onJsPrompt(
            webViewProxy,
            url,
            message,
            defaultValue,
            X5JsPromptResult(result)
        )
    }

    override fun onJsBeforeUnload(
        view: WebView, url: String, message: String,
        result: JsResult
    ): Boolean {
        return webChromeClientDelegate.onJsBeforeUnload(
            webViewProxy,
            url,
            message,
            X5JsResult(result)
        )
    }

    override fun onGeolocationPermissionsShowPrompt(
        origin: String,
        callback: com.tencent.smtt.export.external.interfaces.GeolocationPermissionsCallback
    ) {
        webChromeClientDelegate.onGeolocationPermissionsShowPrompt(
            origin,
            object : GeolocationPermissionsCallback {
                override fun invoke(var1: String, var2: Boolean, var3: Boolean) {
                    callback.invoke(var1, var2, var3)
                }
            })
    }

    override fun onGeolocationPermissionsHidePrompt() {
        webChromeClientDelegate.onGeolocationPermissionsHidePrompt()
    }

    override fun onJsTimeout(): Boolean {
        return webChromeClientDelegate.onJsTimeout()
    }

    override fun onConsoleMessage(consoleMessage: ConsoleMessage): Boolean {
        return webChromeClientDelegate.onConsoleMessage(
            X5ConsoleMessage(
                consoleMessage
            )
        )
    }

    override fun getDefaultVideoPoster(): Bitmap? {
        return webChromeClientDelegate.getDefaultVideoPoster()
    }

    override fun getVisitedHistory(callback: ValueCallback<Array<String>>) {
        webChromeClientDelegate.getVisitedHistory(callback)
    }

    override fun openFileChooser(p0: ValueCallback<Uri>?, p1: String?, p2: String?) {
        webChromeClientDelegate.openFileChooser(p0, p1, p2)
    }

    override fun onShowFileChooser(
        webView: WebView, filePathCallback: ValueCallback<Array<Uri>>,
        fileChooserParams: FileChooserParams
    ): Boolean {
        return webChromeClientDelegate.onShowFileChooser(webViewProxy, filePathCallback, object :
            IWebChromeClient.FileChooserParams() {
            override fun getMode(): Int {
                return fileChooserParams.mode
            }

            override fun getAcceptTypes(): Array<String> {
                return fileChooserParams.acceptTypes
            }

            override fun isCaptureEnabled(): Boolean {
                return fileChooserParams.isCaptureEnabled
            }

            override fun getTitle(): CharSequence {
                return fileChooserParams.title
            }

            override fun getFilenameHint(): String {
                return fileChooserParams.filenameHint
            }

            override fun createIntent(): Intent {
                return fileChooserParams.createIntent()
            }

        })
    }
}