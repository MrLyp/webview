package vip.irock.web.protocol

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Picture
import android.graphics.Point
import android.net.http.SslCertificate
import android.os.Bundle
import android.os.Message
import android.view.View
import java.io.BufferedWriter
import java.io.File

interface IWebView {

    fun isPrivateBrowsingEnable(): Boolean


    @Deprecated("")
    fun getZoomControls(): View

    fun getOriginalUrl(): String

    fun getCertificate(): SslCertificate?

    fun getSettings(): IWebSettings

    fun getFavicon(): Bitmap

    fun getProgress(): Int

    fun getTitle(): String

    fun getUrl(): String


    @Deprecated("")
    fun getScale(): Float

    fun getContentHeight(): Int

    fun getHitTestResult(): HitTestResult

    fun getView(): View

    fun getVisibleTitleHeight(): Int

    fun getContentWidth(): Int

    fun setHorizontalScrollbarOverlay(var1: Boolean)

    fun setVerticalScrollbarOverlay(var1: Boolean)

    fun overlayHorizontalScrollbar(): Boolean

    fun overlayVerticalScrollbar(): Boolean


    @Deprecated("")
    fun savePicture(var1: Bundle, var2: File): Boolean


    @Deprecated("")
    fun restorePicture(var1: Bundle, var2: File): Boolean


    @Deprecated("")
    fun savePassword(var1: String, var2: String, var3: String)

    fun loadDataWithBaseURL(
        baseUrl: String,
        data: String,
        mimeType: String,
        encoding: String,
        failUrl: String
    )


    @Deprecated("")
    fun clearView()

    fun setInitialScale(var1: Int)

    fun invokeZoomPicker()

    fun requestFocusNodeHref(var1: Message)

    fun requestImageRef(var1: Message)

    fun clearFormData()

    fun clearSslPreferences()

    fun documentHasImages(var1: Message)


    @Deprecated("")
    fun canZoomIn(): Boolean

    fun zoomIn(): Boolean


    @Deprecated("")
    fun canZoomOut(): Boolean

    fun zoomOut(): Boolean


    @Deprecated("")
    fun setMapTrackballToArrowKeys(var1: Boolean)

    fun setNetworkAvailable(var1: Boolean)

    fun flingScroll(var1: Int, var2: Int)

    fun findNext(var1: Boolean)


    @Deprecated("")
    fun findAll(var1: String): Int

    fun findAllAsync(var1: String)

    fun clearMatches()

    fun copyBackForwardList(): IWebBackForwardList

    fun addJavascriptInterface(var1: Any, var2: String)

    fun removeJavascriptInterface(var1: String)

    fun setPictureListener(listener: PictureListener)

    fun canGoBack(): Boolean

    fun canGoForward(): Boolean

    fun clearCache(var1: Boolean)

    fun destroy()

    fun getHttpAuthUsernamePassword(var1: String, var2: String): Array<String>

    fun goBack()

    fun goBackOrForward(var1: Int)

    fun goForward()

    fun loadData(data: String, mimeType: String, encoding: String)

    fun loadUrl(var1: String)

    fun loadUrl(var1: String, var2: Map<String, String>)

    fun pageDown(var1: Boolean, var2: Int): Boolean

    fun pageUp(var1: Boolean, var2: Int): Boolean

    fun reload()

    fun setDownloadListener(downloadListener: DownloadListener)

    fun setHttpAuthUsernamePassword(host: String, realm: String, username: String, password: String)

    fun setWebViewClient(client: IWebViewClient?)

    fun setWebChromeClient(client: IWebChromeClient?)

    fun stopLoading()

    fun canGoBackOrForward(var1: Int): Boolean

    fun capturePicture(): Picture

    fun createPrintDocumentAdapter(var1: String): Any

    fun pauseTimers()

    fun resumeTimers()

    fun clearHistory()

    fun onPause()

    fun onResume()

    fun postUrl(var1: String, var2: ByteArray)

    fun restoreState(var1: Bundle): IWebBackForwardList

    fun saveState(var1: Bundle): IWebBackForwardList

    fun freeMemory()

    fun saveWebArchive(var1: String)

    fun saveWebArchive(var1: String, var2: Boolean, var3: IValueCallback<String>)


    @Deprecated("")
    fun showFindDialog(var1: String, var2: Boolean): Boolean

    fun setFindListener(var1: FindListener)

    fun dumpViewHierarchyWithProperties(var1: BufferedWriter, var2: Int)

    fun findHierarchyView(var1: String, var2: Int): View

    fun refreshPlugins(var1: Boolean)

    fun computeScroll()

    fun setBackgroundColor(var1: Int)

    fun evaluateJavascript(var1:String, var2: IValueCallback<String>?)

    fun setWebContentsDebuggingEnabled(enable: Boolean) {

    }

    fun getContext() : Context

    class WebViewTransport {
        @get:Synchronized
        @set:Synchronized
        var webView: IWebView? = null
    }


    @Deprecated("")
    interface PictureListener {

        @Deprecated("")
        fun onNewPicture(var1: IWebView, var2: Picture?, var3: Boolean)

        fun onNewPictureIfHaveContent(var1: IWebView, var2: Picture)
    }

    interface FindListener {
        fun onFindResultReceived(var1: Int, var2: Int, var3: Boolean)
    }

    class ImageInfo {
        var picUrl: String? = null
        var picSize: Long = 0
        var isGif: Boolean = false
    }

    class HitTestResult {
        var type = 0
        var isFromSinglePress = false
        var data: Any? = null
        var hitTestPoint: Point? = null
            get() = Point(field)
        var extra: String? = null

        protected val bitmapData: Bitmap?
            get() = null

        inner class EditableData {
            var mEditableText: String? = null
            var mIsPassword: Boolean = false
        }

        inner class AnchorData {
            var mAnchorUrl: String? = null
            var mAnchorTitle: String? = null
        }

        inner class ImageAnchorData {
            var mPicUrl: String? = null
            var mAHref: String? = null
            var mBmp: Bitmap? = null
            var mRawDataSize: Long = 0

            val bitmap: Bitmap?
                get() = this@HitTestResult.bitmapData
        }

        inner class ImageData {
            var mPicUrl: String? = null
            var mBmp: Bitmap? = null
            var mRawDataSize: Long = 0
            var mImgWidth: Int = 0
            var mImgHeight: Int = 0

            val bitmap: Bitmap?
                get() = this@HitTestResult.bitmapData
        }

        companion object {
            val UNKNOWN_TYPE = 0

            @Deprecated("")
            val ANCHOR_TYPE = 1
            val PHONE_TYPE = 2
            val GEO_TYPE = 3
            val EMAIL_TYPE = 4
            val IMAGE_TYPE = 5

            @Deprecated("")
            val IMAGE_ANCHOR_TYPE = 6
            val SRC_ANCHOR_TYPE = 7
            val SRC_IMAGE_ANCHOR_TYPE = 8
            val EDIT_TEXT_TYPE = 9
            val BUTTON_TYPE = 10
        }
    }

    companion object {
        val OVER_SCROLL_ALWAYS = 0
        val OVER_SCROLL_IF_CONTENT_SCROLLS = 1
        val OVER_SCROLL_NEVER = 2
    }
}