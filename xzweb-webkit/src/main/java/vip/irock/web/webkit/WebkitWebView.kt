package vip.irock.web.webkit

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Bitmap
import android.graphics.Picture
import android.net.http.SslCertificate
import android.os.Build
import android.os.Bundle
import android.os.Message
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.webkit.WebView
import android.widget.FrameLayout
import androidx.annotation.RequiresApi
import com.xzweb.android.webkit.R
import vip.irock.web.protocol.*
import java.io.BufferedWriter
import java.io.File

class WebkitWebView : FrameLayout, IWebView {

    private lateinit var webViewProxy: WebView

    constructor(webView: WebView) : super(webView.context) {
        webViewProxy = webView
    }

    constructor(context: Context) : super(context) {
        init(context, null, 0)
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        init(context, attrs, 0)
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        init(context, attrs, defStyleAttr)
    }

    private fun init(context: Context, attrs: AttributeSet?, defStyleAttr: Int) {
        LayoutInflater.from(context).inflate(R.layout.widget_webkit_webview, this)
        webViewProxy = findViewById(R.id.webkit_webview)
    }

    override fun isPrivateBrowsingEnable(): Boolean {
        return webViewProxy.isPrivateBrowsingEnabled
    }

    override fun getZoomControls(): View {
        throw UnsupportedOperationException("getZoomControl method is not impl")
    }

    override fun getOriginalUrl(): String {
        return webViewProxy.originalUrl
    }

    override fun getCertificate(): SslCertificate? {
        return webViewProxy.certificate
    }

    override fun getSettings(): IWebSettings {
        return WebkitWebSettings(webViewProxy.settings)
    }

    override fun getFavicon(): Bitmap {
        return webViewProxy.favicon
    }

    override fun getProgress(): Int {
        return webViewProxy.progress
    }

    override fun getTitle(): String {
        return webViewProxy.title
    }

    override fun getUrl(): String {
        return webViewProxy.url
    }

    override fun getScale(): Float {
        return webViewProxy.scale
    }

    override fun getContentHeight(): Int {
        return webViewProxy.contentHeight
    }

    override fun getHitTestResult(): IWebView.HitTestResult {
        throw UnsupportedOperationException("getHitTestResult method is not impl")
    }

    override fun getView(): View {
        return webViewProxy
    }

    override fun getVisibleTitleHeight(): Int {
        return -1
    }

    override fun getContentWidth(): Int {
        return webViewProxy.width
    }

    override fun setHorizontalScrollbarOverlay(var1: Boolean) {
        webViewProxy.setHorizontalScrollbarOverlay(var1)
    }

    override fun setVerticalScrollbarOverlay(var1: Boolean) {
        webViewProxy.setVerticalScrollbarOverlay(var1)
    }

    override fun overlayHorizontalScrollbar(): Boolean {
        return webViewProxy.overlayHorizontalScrollbar()
    }

    override fun overlayVerticalScrollbar(): Boolean {
        return webViewProxy.overlayVerticalScrollbar()
    }

    override fun savePicture(var1: Bundle, var2: File): Boolean {
        return false
    }

    override fun restorePicture(var1: Bundle, var2: File): Boolean {
        return false
    }

    override fun savePassword(var1: String, var2: String, var3: String) {
        return webViewProxy.savePassword(var1, var2, var3)
    }

    override fun loadDataWithBaseURL(
        baseUrl: String,
        data: String,
        mimeType: String,
        encoding: String,
        failUrl: String
    ) {
        webViewProxy.loadDataWithBaseURL(baseUrl, data, mimeType, encoding, failUrl)
    }

    override fun clearView() {
        webViewProxy.clearView()
    }

    override fun setInitialScale(var1: Int) {
        webViewProxy.setInitialScale(var1)
    }

    override fun invokeZoomPicker() {
        webViewProxy.invokeZoomPicker()
    }

    override fun requestFocusNodeHref(var1: Message) {
        webViewProxy.requestFocusNodeHref(var1)
    }

    override fun requestImageRef(var1: Message) {
        webViewProxy.requestImageRef(var1)
    }

    override fun clearFormData() {
        webViewProxy.clearFormData()
    }

    override fun clearSslPreferences() {
        webViewProxy.clearSslPreferences()
    }

    override fun documentHasImages(var1: Message) {
        webViewProxy.documentHasImages(var1)
    }

    override fun canZoomIn(): Boolean {
        return webViewProxy.canZoomIn()
    }

    override fun zoomIn(): Boolean {
        return webViewProxy.zoomIn()
    }

    override fun canZoomOut(): Boolean {
        return webViewProxy.canZoomOut()
    }

    override fun zoomOut(): Boolean {
        return webViewProxy.zoomOut()
    }

    override fun setMapTrackballToArrowKeys(var1: Boolean) {
        webViewProxy.setMapTrackballToArrowKeys(var1)
    }

    override fun setNetworkAvailable(var1: Boolean) {
        webViewProxy.setNetworkAvailable(var1)
    }

    override fun flingScroll(var1: Int, var2: Int) {
        webViewProxy.flingScroll(var1, var2)
    }

    override fun findNext(var1: Boolean) {
        webViewProxy.findNext(var1)
    }

    override fun findAll(var1: String): Int {
        return webViewProxy.findAll(var1)
    }

    override fun findAllAsync(var1: String) {
        return webViewProxy.findAllAsync(var1)
    }

    override fun clearMatches() {
        return webViewProxy.clearMatches()
    }

    override fun copyBackForwardList(): IWebBackForwardList {
        return WebkitWebBackForwardList(webViewProxy.copyBackForwardList())
    }

    @SuppressLint("JavascriptInterface")
    override fun addJavascriptInterface(var1: Any, var2: String) {
        webViewProxy.addJavascriptInterface(var1, var2)
    }

    override fun removeJavascriptInterface(var1: String) {
        webViewProxy.removeJavascriptInterface(var1)
    }

    override fun setPictureListener(listener: IWebView.PictureListener) {
        webViewProxy.setPictureListener { _, p1 ->
            listener.onNewPicture(
                this@WebkitWebView,
                p1,
                false
            )
        }
    }

    override fun canGoBack(): Boolean {
        return webViewProxy.canGoBack()
    }

    override fun canGoForward(): Boolean {
        return webViewProxy.canGoForward()
    }

    override fun clearCache(var1: Boolean) {
        webViewProxy.clearCache(var1)
    }

    override fun destroy() {
        webViewProxy.destroy()
    }

    override fun getHttpAuthUsernamePassword(var1: String, var2: String): Array<String> {
        return webViewProxy.getHttpAuthUsernamePassword(var1, var2) ?: Array<String>(0) { "" }
    }

    override fun goBack() {
        webViewProxy.goBack()
    }

    override fun goBackOrForward(var1: Int) {
        webViewProxy.goBackOrForward(var1)
    }

    override fun goForward() {
        webViewProxy.goForward()
    }

    override fun loadData(data: String, mimeType: String, encoding: String) {
        webViewProxy.loadData(data, mimeType, encoding)
    }

    override fun loadUrl(var1: String) {
        webViewProxy.loadUrl(var1)
    }

    override fun loadUrl(var1: String, var2: Map<String, String>) {
        webViewProxy.loadUrl(var1, var2)
    }

    override fun pageDown(var1: Boolean, var2: Int): Boolean {
        return webViewProxy.pageDown(var1)
    }

    override fun pageUp(var1: Boolean, var2: Int): Boolean {
        return webViewProxy.pageUp(var1)
    }

    override fun reload() {
        webViewProxy.reload()
    }

    override fun setDownloadListener(downloadListener: DownloadListener) {
        webViewProxy.setDownloadListener { url, userAgent, contentDisposition, mimetype, contentLength ->
            downloadListener.onDownloadStart(
                url,
                userAgent,
                contentDisposition,
                mimetype,
                contentLength
            )
        }
    }

    override fun setHttpAuthUsernamePassword(
        host: String,
        realm: String,
        username: String,
        password: String
    ) {
        webViewProxy.setHttpAuthUsernamePassword(host, realm, username, password)
    }

    override fun setWebViewClient(client: IWebViewClient?) {
        if (client != null) {
            webViewProxy.webViewClient =
                WebkitWebViewClient(this, client)
        } else {
            webViewProxy.webViewClient = null
        }
    }

    override fun setWebChromeClient(client: IWebChromeClient?) {
        if (client != null) {
            webViewProxy.webChromeClient =
                WebkitWebChromeClient(this, client)
        } else {
            webViewProxy.webChromeClient = null
        }
    }

    override fun stopLoading() {
        webViewProxy.stopLoading()
    }

    override fun canGoBackOrForward(var1: Int): Boolean {
        return webViewProxy.canGoBackOrForward(var1)
    }

    override fun capturePicture(): Picture {
        return webViewProxy.capturePicture()
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun createPrintDocumentAdapter(var1: String): Any {
        return webViewProxy.createPrintDocumentAdapter(var1)
    }

    override fun pauseTimers() {
        webViewProxy.pauseTimers()
    }

    override fun resumeTimers() {
        webViewProxy.resumeTimers()
    }

    override fun clearHistory() {
        webViewProxy.clearHistory()
    }

    override fun onPause() {
        webViewProxy.onPause()
    }

    override fun onResume() {
        webViewProxy.onResume()
    }

    override fun postUrl(var1: String, var2: ByteArray) {
        webViewProxy.postUrl(var1, var2)
    }

    override fun restoreState(var1: Bundle): IWebBackForwardList {
        return WebkitWebBackForwardList(
            webViewProxy.restoreState(
                var1
            )
        )
    }

    override fun saveState(var1: Bundle): IWebBackForwardList {
        return WebkitWebBackForwardList(
            webViewProxy.saveState(
                var1
            )
        )
    }

    override fun freeMemory() {
        webViewProxy.freeMemory()
    }

    override fun saveWebArchive(var1: String) {
        webViewProxy.saveWebArchive(var1)
    }

    override fun saveWebArchive(var1: String, var2: Boolean, var3: IValueCallback<String>) {
        webViewProxy.saveWebArchive(
            var1, var2
        ) { value -> var3.onReceiveValue(value) }
    }

    override fun showFindDialog(var1: String, var2: Boolean): Boolean {
        return webViewProxy.showFindDialog(var1, var2)
    }

    override fun setFindListener(var1: IWebView.FindListener) {
        webViewProxy.setFindListener(object : WebView.FindListener {
            override fun onFindResultReceived(p0: Int, p1: Int, p2: Boolean) {
                var1.onFindResultReceived(p0, p1, p2)
            }
        })
    }

    override fun dumpViewHierarchyWithProperties(var1: BufferedWriter, var2: Int) {

    }

    override fun findHierarchyView(var1: String, var2: Int): View {
        throw UnsupportedOperationException("findHierarchyView method is not impl")
    }

    override fun refreshPlugins(var1: Boolean) {

    }

    override fun computeScroll() {
        webViewProxy.computeScroll()
    }

    override fun setBackgroundColor(var1: Int) {
        webViewProxy.setBackgroundColor(var1)
    }

    override fun evaluateJavascript(var1: String, var2: IValueCallback<String>?) {
        webViewProxy.evaluateJavascript(
            var1
        ) { value -> var2?.onReceiveValue(value) }
    }

    override fun setWebContentsDebuggingEnabled(enable: Boolean) {
        WebView.setWebContentsDebuggingEnabled(enable)
    }
}