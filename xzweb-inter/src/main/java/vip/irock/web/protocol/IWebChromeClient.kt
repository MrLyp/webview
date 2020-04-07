package vip.irock.web.protocol

import android.content.Intent
import android.graphics.Bitmap
import android.location.Location
import android.net.Uri
import android.os.Bundle
import android.os.Message
import android.view.View
import android.webkit.PermissionRequest
import android.webkit.ValueCallback

interface IWebChromeClient {

    fun getDefaultVideoPoster(): Bitmap? {
        return null
    }

    @Deprecated("")
    fun onConsoleMessage(var1: String, var2: Int, var3: String) {

    }

    fun onConsoleMessage(var1: IConsoleMessage): Boolean {
        return false
    }

    fun onCreateWindow(var1: IWebView, var2: Boolean, var3: Boolean, var4: Message): Boolean {
        return false
    }

    fun onGeolocationPermissionsHidePrompt() {

    }

    fun onGeolocationPermissionsShowPrompt(origin: String, callback: GeolocationPermissionsCallback) {

    }

    fun onHideCustomView() {

    }

    fun onJsAlert(view: IWebView, url: String, message: String, result: IJsResult): Boolean {
        return false
    }

    fun onJsConfirm(view: IWebView, url: String, message: String, result: IJsResult): Boolean {
        return false
    }

    fun onJsPrompt(
        view: IWebView,
        url: String,
        message: String,
        defaultValue: String,
        result: IJsPromptResult
    ): Boolean {
        return false
    }

    fun onJsBeforeUnload(var1: IWebView, var2: String, var3: String, var4: IJsResult): Boolean {
        return false
    }

    fun onJsTimeout(): Boolean {
        return false
    }

    fun onProgressChanged(var1: IWebView, progress: Int) {

    }

//    fun onReachedMaxAppCacheSize(var1: Long, var3: Long, var5: QuotaUpdater)

    fun onReceivedIcon(var1: IWebView, var2: Bitmap) {

    }

    fun onReceivedTouchIconUrl(var1: IWebView, var2: String, var3: Boolean) {

    }

    fun onReceivedTitle(var1: IWebView, title: String) {

    }

    fun onRequestFocus(var1: IWebView) {

    }

    fun onShowCustomView(view: View, callback: ICustomViewCallback) {

    }

    fun onShowCustomView(view: View, requestedOrientation: Int, callback: ICustomViewCallback) {

    }

    fun onCloseWindow(var1: IWebView) {

    }

    fun getVisitedHistory(var1: ValueCallback<Array<String>>) {

    }

    fun openFileChooser(var1: ValueCallback<Uri>?, var2: String?, var3: String?) {

    }

    fun onShowFileChooser(
        webView: IWebView,
        filePathCallback: ValueCallback<Array<Uri>>,
        fileChooserParams: FileChooserParams
    ): Boolean {
        return false
    }

    fun onPermissionRequest(request: PermissionRequest) {
        request.deny()
    }

    fun onPermissionRequestCanceled(request: PermissionRequest?) {}


    abstract class FileChooserParams {

        abstract fun getMode(): Int

        abstract fun getAcceptTypes(): Array<String>

        abstract fun isCaptureEnabled(): Boolean

        abstract fun getTitle(): CharSequence

        abstract fun getFilenameHint(): String

        abstract fun createIntent(): Intent

        companion object {
            val MODE_OPEN = 0
            val MODE_OPEN_MULTIPLE = 1
            val MODE_OPEN_FOLDER = 2
            val MODE_SAVE = 3

            fun parseResult(var0: Int, var1: Intent): Array<Uri>? {
                return null
            }
        }
    }

    interface ICustomViewCallback {
        fun onCustomViewHidden()
    }
}