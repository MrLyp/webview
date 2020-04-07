package vip.irock.web.webkit

import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Build
import android.os.Message
import android.view.View
import android.webkit.*
import androidx.annotation.RequiresApi
import vip.irock.web.protocol.GeolocationPermissionsCallback
import vip.irock.web.protocol.IWebChromeClient
import vip.irock.web.protocol.IWebView

class WebkitWebChromeClient(
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

    override fun onShowCustomView(view: View, callback: CustomViewCallback) {
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
        callback: CustomViewCallback
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
            WebkitJsResult(result)
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
            WebkitJsResult(result)
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
            WebkitJsPromptResult(result)
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
            WebkitJsResult(result)
        )
    }

    override fun onGeolocationPermissionsShowPrompt(
        origin: String,
        callback: GeolocationPermissions.Callback
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

    override fun onPermissionRequest(request: PermissionRequest) {
        webChromeClientDelegate.onPermissionRequest(request)
    }

    override fun onPermissionRequestCanceled(request: PermissionRequest) {
        webChromeClientDelegate.onPermissionRequestCanceled(request)
    }

    override fun onJsTimeout(): Boolean {
        return webChromeClientDelegate.onJsTimeout()
    }

    override fun onConsoleMessage(message: String, lineNumber: Int, sourceID: String) {
        webChromeClientDelegate.onConsoleMessage(message, lineNumber, sourceID)
    }

    override fun onConsoleMessage(consoleMessage: ConsoleMessage): Boolean {
        return webChromeClientDelegate.onConsoleMessage(
            WebkitConsoleMessage(
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

    fun openFileChooser(uploadFile: ValueCallback<Uri>, acceptType: String, capture: String) {
        webChromeClientDelegate.openFileChooser(uploadFile, acceptType, capture)
    }

    override fun onShowFileChooser(
        webView: WebView, filePathCallback: ValueCallback<Array<Uri>>,
        fileChooserParams: FileChooserParams
    ): Boolean {
        return webChromeClientDelegate.onShowFileChooser(webViewProxy, filePathCallback, object :
            IWebChromeClient.FileChooserParams() {
            @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
            override fun getMode(): Int {
                return fileChooserParams.mode
            }

            @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
            override fun getAcceptTypes(): Array<String> {
                return fileChooserParams.acceptTypes
            }

            @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
            override fun isCaptureEnabled(): Boolean {
                return fileChooserParams.isCaptureEnabled
            }

            @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
            override fun getTitle(): CharSequence {
                return fileChooserParams.title
            }

            @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
            override fun getFilenameHint(): String {
                return fileChooserParams.filenameHint
            }

            @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
            override fun createIntent(): Intent {
                return fileChooserParams.createIntent()
            }

        })
    }
}