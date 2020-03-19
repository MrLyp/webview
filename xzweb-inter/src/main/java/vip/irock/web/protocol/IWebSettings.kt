package vip.irock.web.protocol

interface IWebSettings {

    fun getDatabaseEnabled(): Boolean

    fun getDatabasePath(): String

    fun getMixedContentMode(): Int

    fun getUserAgent(): String

    fun getPluginState(): PluginState

    fun getTextSize(): TextSize

    fun getUserAgentString(): String

    fun getAllowContentAccess(): Boolean

    fun getAllowFileAccess(): Boolean

    fun getBuiltInZoomControls(): Boolean

    fun getDefaultFixedFontSize(): Int

    fun getCursiveFontFamily(): String

    fun getDefaultFontSize(): Int

    fun getDefaultTextEncodingName(): String

    fun getFantasyFontFamily(): String

    fun getFixedFontFamily(): String

    fun getJavaScriptCanOpenWindowsAutomatically(): Boolean

    fun getJavaScriptEnabled(): Boolean

    fun getLayoutAlgorithm(): LayoutAlgorithm

    fun getLightTouchEnabled(): Boolean

    fun getMinimumFontSize(): Int

    fun getMinimumLogicalFontSize(): Int

//    fun getNavDump(): Boolean

    fun getPluginsEnabled(): Boolean

    fun getPluginsPath(): String

    fun getSansSerifFontFamily(): String

    fun getSaveFormData(): Boolean

    fun getSavePassword(): Boolean

    fun getSerifFontFamily(): String

    fun getStandardFontFamily(): String

    fun getUseWideViewPort(): Boolean

    fun getLoadsImagesAutomatically(): Boolean

    fun getCacheMode(): Int

    fun getBlockNetworkImage(): Boolean

    fun getBlockNetworkLoads(): Boolean

    fun getDisplayZoomControls(): Boolean

    fun getLoadWithOverviewMode(): Boolean

    fun getUseWebViewBackgroundForOverscrollBackground(): Boolean

    fun getTextZoom(): Int

    fun getDefaultZoom(): ZoomDensity

    fun getDomStorageEnabled(): Boolean

    fun getMediaPlaybackRequiresUserGesture(): Boolean

    fun setUserAgent(var1: String, var2: Boolean)

    fun setSupportZoom(var1: Boolean)

    fun setSupportMultipleWindows(var1: Boolean)

    fun setAppCacheEnabled(var1: Boolean)

    fun setAppCacheMaxSize(var1: Long)

    fun setAppCachePath(var1: String)

    fun setDefaultDatabasePath(var1: Boolean)

    fun setGeolocationEnabled(var1: Boolean)

    fun setGeolocationDatabasePath(var1: String)

    fun setNeedInitialFocus(var1: Boolean)

    fun setAllowUniversalAccessFromFileURLs(var1: Boolean)

    fun setAllowFileAccessFromFileURLs(var1: Boolean)

    fun setPluginEnabled(var1: Boolean)

    fun setRenderPriority(var1: RenderPriority)

    fun supportMultipleWindows(): Boolean

    fun supportZoom(): Boolean

    fun setEnableSmoothTransition(var1: Boolean)

    fun enableSmoothTransition(): Boolean

    fun setMediaPlaybackRequiresUserGesture(require: Boolean)

    fun setBuiltInZoomControls(enabled: Boolean)


    fun setDisplayZoomControls(enabled: Boolean)


    fun setAllowFileAccess(allow: Boolean)

    fun setAllowContentAccess(allow: Boolean)


    fun setLoadWithOverviewMode(overview: Boolean)

    fun setSaveFormData(save: Boolean)

    fun setSavePassword(save: Boolean)

    fun setTextZoom(textZoom: Int)

    fun setDefaultZoom(zoom: ZoomDensity)

    fun setLightTouchEnabled(enabled: Boolean)

    fun setUseWideViewPort(use: Boolean)

    fun setLayoutAlgorithm(l: LayoutAlgorithm)

    fun setStandardFontFamily(font: String)

    fun setFixedFontFamily(font: String)

    fun setSansSerifFontFamily(font: String)

    fun setSerifFontFamily(font: String)

    fun setCursiveFontFamily(font: String)

    fun setFantasyFontFamily(font: String)

    fun setMinimumFontSize(size: Int)

    fun setMinimumLogicalFontSize(size: Int)

    fun setDefaultFontSize(size: Int)

    fun setDefaultFixedFontSize(size: Int)

    fun setLoadsImagesAutomatically(flag: Boolean)

    fun setBlockNetworkImage(flag: Boolean)


    fun setBlockNetworkLoads(flag: Boolean)

    fun setJavaScriptEnabled(flag: Boolean)

    fun setPluginState(state: PluginState)

    fun setDatabasePath(databasePath: String)

    fun setDatabaseEnabled(flag: Boolean)

    fun setDomStorageEnabled(flag: Boolean)

    fun getAllowUniversalAccessFromFileURLs(): Boolean

    fun setJavaScriptCanOpenWindowsAutomatically(flag: Boolean)

    fun setDefaultTextEncodingName(encoding: String)

    fun setUserAgentString(ua: String)
    fun setCacheMode(mode: Int)

    fun setMixedContentMode(mode: Int)


    enum class ZoomDensity private constructor(internal var value: Int) {
        FAR(150),
        MEDIUM(100),
        CLOSE(75)
    }

    enum class TextSize private constructor(internal var value: Int) {
        SMALLEST(50),
        SMALLER(75),
        NORMAL(100),
        LARGER(125),
        LARGEST(150)
    }

    enum class RenderPriority private constructor() {
        NORMAL,
        HIGH,
        LOW
    }

    enum class PluginState private constructor() {
        ON,
        ON_DEMAND,
        OFF
    }

    enum class LayoutAlgorithm private constructor() {
        NORMAL,
        SINGLE_COLUMN,
        NARROW_COLUMNS
    }

    companion object {
        val LOAD_DEFAULT = -1
        val LOAD_NORMAL = 0
        val LOAD_CACHE_ELSE_NETWORK = 1
        val LOAD_NO_CACHE = 2
        val LOAD_CACHE_ONLY = 3
        val LOAD_CACHE_AD = 100
        val DEFAULT_CACHE_CAPACITY = 15
    }
}