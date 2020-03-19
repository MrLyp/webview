package vip.irock.web.x5web

import android.os.Build
import androidx.annotation.RequiresApi
import com.tencent.smtt.sdk.WebSettings
import vip.irock.web.protocol.IWebSettings

class X5WebSettings(val webSettingsProxy: WebSettings) :
    IWebSettings {

    private var allowUniversalAccessFromFileURLs:Boolean = false

    override fun setBuiltInZoomControls(enabled: Boolean) {
        webSettingsProxy.builtInZoomControls = enabled
    }

    override fun setDisplayZoomControls(enabled: Boolean) {
        webSettingsProxy.displayZoomControls = enabled
    }

    override fun setMediaPlaybackRequiresUserGesture(require: Boolean) {
        webSettingsProxy.mediaPlaybackRequiresUserGesture = require
    }

    override fun setAllowFileAccess(allow: Boolean) {
        webSettingsProxy.allowFileAccess = allow
    }

    override fun setAllowContentAccess(allow: Boolean) {
        webSettingsProxy.allowContentAccess = allow
    }

    override fun setLoadWithOverviewMode(overview: Boolean) {
        webSettingsProxy.loadWithOverviewMode = overview
    }

    override fun setSaveFormData(save: Boolean) {
        webSettingsProxy.saveFormData = save
    }

    override fun setSavePassword(save: Boolean) {
        webSettingsProxy.savePassword = save
    }

    override fun setTextZoom(textZoom: Int) {
        webSettingsProxy.textZoom = textZoom
    }

    override fun setDefaultZoom(zoom: IWebSettings.ZoomDensity) {
        webSettingsProxy.defaultZoom = when (zoom) {
            IWebSettings.ZoomDensity.CLOSE ->
                WebSettings.ZoomDensity.CLOSE
            IWebSettings.ZoomDensity.MEDIUM ->
                WebSettings.ZoomDensity.MEDIUM
            else ->
                WebSettings.ZoomDensity.FAR
        }
    }

    override fun setLightTouchEnabled(enabled: Boolean) {
        webSettingsProxy.lightTouchEnabled = enabled
    }

    override fun setUseWideViewPort(use: Boolean) {
        webSettingsProxy.useWideViewPort = use
    }

    override fun setLayoutAlgorithm(l: IWebSettings.LayoutAlgorithm) {
        webSettingsProxy.layoutAlgorithm = when (l) {
            IWebSettings.LayoutAlgorithm.NARROW_COLUMNS ->
                WebSettings.LayoutAlgorithm.NARROW_COLUMNS
            IWebSettings.LayoutAlgorithm.SINGLE_COLUMN ->
                WebSettings.LayoutAlgorithm.SINGLE_COLUMN
            else ->
                WebSettings.LayoutAlgorithm.NORMAL
        }
    }

    override fun setStandardFontFamily(font: String) {
        webSettingsProxy.standardFontFamily = font
    }

    override fun setFixedFontFamily(font: String) {
        webSettingsProxy.fixedFontFamily = font
    }

    override fun setSansSerifFontFamily(font: String) {
        webSettingsProxy.sansSerifFontFamily = font
    }

    override fun setSerifFontFamily(font: String) {
        webSettingsProxy.serifFontFamily = font
    }

    override fun setCursiveFontFamily(font: String) {
        webSettingsProxy.cursiveFontFamily = font
    }

    override fun setFantasyFontFamily(font: String) {
        webSettingsProxy.fantasyFontFamily = font
    }

    override fun setMinimumFontSize(size: Int) {
        webSettingsProxy.minimumFontSize = size
    }

    override fun setMinimumLogicalFontSize(size: Int) {
        webSettingsProxy.minimumLogicalFontSize = size
    }

    override fun setDefaultFontSize(size: Int) {
        webSettingsProxy.defaultFontSize = size
    }

    override fun setDefaultFixedFontSize(size: Int) {
        webSettingsProxy.defaultFixedFontSize = size
    }

    override fun setLoadsImagesAutomatically(flag: Boolean) {
        webSettingsProxy.loadsImagesAutomatically = flag
    }

    override fun setBlockNetworkImage(flag: Boolean) {
        webSettingsProxy.blockNetworkImage = flag
    }

    override fun setBlockNetworkLoads(flag: Boolean) {
        webSettingsProxy.blockNetworkLoads = flag
    }

    override fun setJavaScriptEnabled(flag: Boolean) {
        webSettingsProxy.javaScriptEnabled = flag
    }

    override fun setPluginState(state: IWebSettings.PluginState) {
        webSettingsProxy.pluginState = when (state) {
            IWebSettings.PluginState.ON ->
                WebSettings.PluginState.ON
            IWebSettings.PluginState.OFF ->
                WebSettings.PluginState.OFF
            else ->
                WebSettings.PluginState.ON_DEMAND
        }
    }

    override fun setDatabasePath(databasePath: String) {
        webSettingsProxy.databasePath = databasePath
    }

    override fun setDatabaseEnabled(flag: Boolean) {
        webSettingsProxy.databaseEnabled = flag
    }

    override fun setDomStorageEnabled(flag: Boolean) {
        webSettingsProxy.domStorageEnabled = flag
    }

    override fun getAllowUniversalAccessFromFileURLs(): Boolean {
        return allowUniversalAccessFromFileURLs
    }

    override fun setJavaScriptCanOpenWindowsAutomatically(flag: Boolean) {
        webSettingsProxy.javaScriptCanOpenWindowsAutomatically = flag
    }

    override fun setDefaultTextEncodingName(encoding: String) {
        webSettingsProxy.defaultTextEncodingName = encoding
    }

    override fun setUserAgentString(ua: String) {
        webSettingsProxy.userAgentString = ua
    }

    override fun setCacheMode(mode: Int) {
        webSettingsProxy.cacheMode = mode
    }

    override fun setMixedContentMode(mode: Int) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            webSettingsProxy.mixedContentMode = mode
        }
    }

    override fun getDatabaseEnabled(): Boolean {
        return webSettingsProxy.databaseEnabled
    }

    override fun getDatabasePath(): String {
        return webSettingsProxy.databasePath
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun getMixedContentMode(): Int {
        return webSettingsProxy.mixedContentMode
    }

    override fun getUserAgent(): String {
        return webSettingsProxy.userAgentString
    }

    override fun getPluginState(): IWebSettings.PluginState {
        return when (webSettingsProxy.pluginState) {
            WebSettings.PluginState.ON ->
                IWebSettings.PluginState.ON
            WebSettings.PluginState.OFF ->
                IWebSettings.PluginState.OFF
            else ->
                IWebSettings.PluginState.ON_DEMAND
        }
    }

    override fun getTextSize(): IWebSettings.TextSize {
        return when (webSettingsProxy.textSize) {
            WebSettings.TextSize.SMALLEST ->
                IWebSettings.TextSize.SMALLEST
            WebSettings.TextSize.SMALLER ->
                IWebSettings.TextSize.SMALLER
            WebSettings.TextSize.NORMAL ->
                IWebSettings.TextSize.NORMAL
            WebSettings.TextSize.LARGER ->
                IWebSettings.TextSize.LARGER
            else ->
                IWebSettings.TextSize.LARGEST
        }
    }

    override fun getUserAgentString(): String {
        return webSettingsProxy.userAgentString
    }

    override fun getAllowContentAccess(): Boolean {
        return webSettingsProxy.allowContentAccess
    }

    override fun getAllowFileAccess(): Boolean {
        return webSettingsProxy.allowFileAccess
    }

    override fun getBuiltInZoomControls(): Boolean {
        return webSettingsProxy.builtInZoomControls
    }

    override fun getDefaultFixedFontSize(): Int {
        return webSettingsProxy.defaultFixedFontSize
    }

    override fun getCursiveFontFamily(): String {
        return webSettingsProxy.cursiveFontFamily
    }

    override fun getDefaultFontSize(): Int {
        return webSettingsProxy.defaultFontSize
    }

    override fun getDefaultTextEncodingName(): String {
        return webSettingsProxy.defaultTextEncodingName
    }

    override fun getFantasyFontFamily(): String {
        return webSettingsProxy.fantasyFontFamily
    }

    override fun getFixedFontFamily(): String {
        return webSettingsProxy.fixedFontFamily
    }

    override fun getJavaScriptCanOpenWindowsAutomatically(): Boolean {
        return webSettingsProxy.javaScriptCanOpenWindowsAutomatically
    }

    override fun getJavaScriptEnabled(): Boolean {
        return webSettingsProxy.javaScriptEnabled
    }

    override fun getLayoutAlgorithm(): IWebSettings.LayoutAlgorithm {
        return when (webSettingsProxy.layoutAlgorithm) {
            WebSettings.LayoutAlgorithm.NARROW_COLUMNS ->
                IWebSettings.LayoutAlgorithm.NARROW_COLUMNS
            WebSettings.LayoutAlgorithm.SINGLE_COLUMN ->
                IWebSettings.LayoutAlgorithm.SINGLE_COLUMN
            else ->
                IWebSettings.LayoutAlgorithm.NORMAL
        }
    }

    override fun getLightTouchEnabled(): Boolean {
        return webSettingsProxy.lightTouchEnabled
    }

    override fun getMinimumFontSize(): Int {
        return webSettingsProxy.minimumFontSize
    }

    override fun getMinimumLogicalFontSize(): Int {
        return webSettingsProxy.minimumLogicalFontSize
    }

//    override fun getNavDump(): Boolean {
//        return webSettingsProxy.na
//    }

    override fun getPluginsEnabled(): Boolean {
        return webSettingsProxy.pluginState == WebSettings.PluginState.ON
    }

    override fun getPluginsPath(): String {
        return ""
    }

    override fun getSansSerifFontFamily(): String {
        return webSettingsProxy.sansSerifFontFamily
    }

    override fun getSaveFormData(): Boolean {
        return webSettingsProxy.saveFormData
    }

    override fun getSavePassword(): Boolean {
        return webSettingsProxy.savePassword
    }

    override fun getSerifFontFamily(): String {
        return webSettingsProxy.serifFontFamily
    }

    override fun getStandardFontFamily(): String {
        return webSettingsProxy.standardFontFamily
    }

    override fun getUseWideViewPort(): Boolean {
        return webSettingsProxy.useWideViewPort
    }

    override fun getLoadsImagesAutomatically(): Boolean {
        return webSettingsProxy.loadsImagesAutomatically
    }

    override fun getCacheMode(): Int {
        return webSettingsProxy.cacheMode
    }

    override fun getBlockNetworkImage(): Boolean {
        return webSettingsProxy.blockNetworkImage
    }

    override fun getBlockNetworkLoads(): Boolean {
        return webSettingsProxy.blockNetworkLoads
    }

    override fun getDisplayZoomControls(): Boolean {
        return webSettingsProxy.displayZoomControls
    }

    override fun getLoadWithOverviewMode(): Boolean {
        return webSettingsProxy.loadWithOverviewMode
    }

    override fun getUseWebViewBackgroundForOverscrollBackground(): Boolean {
        return false
    }

    override fun getTextZoom(): Int {
        return webSettingsProxy.textZoom
    }

    override fun getDefaultZoom(): IWebSettings.ZoomDensity {
        return when (webSettingsProxy.defaultZoom) {
            WebSettings.ZoomDensity.CLOSE ->
                IWebSettings.ZoomDensity.CLOSE
            WebSettings.ZoomDensity.MEDIUM ->
                IWebSettings.ZoomDensity.MEDIUM
            else ->
                IWebSettings.ZoomDensity.FAR
        }
    }

    override fun getDomStorageEnabled(): Boolean {
        return webSettingsProxy.domStorageEnabled
    }

    override fun getMediaPlaybackRequiresUserGesture(): Boolean {
        return webSettingsProxy.mediaPlaybackRequiresUserGesture
    }

    override fun setUserAgent(var1: String, var2: Boolean) {
        webSettingsProxy.userAgentString = var1
    }

    override fun setSupportZoom(var1: Boolean) {
        webSettingsProxy.setSupportZoom(var1)
    }

    override fun setSupportMultipleWindows(var1: Boolean) {
        webSettingsProxy.setSupportMultipleWindows(var1)
    }

    override fun setAppCacheEnabled(var1: Boolean) {
        webSettingsProxy.setAppCacheEnabled(var1)
    }

    override fun setAppCacheMaxSize(var1: Long) {
        webSettingsProxy.setAppCacheMaxSize(var1)
    }

    override fun setAppCachePath(var1: String) {
        webSettingsProxy.setAppCachePath(var1)
    }

    override fun setDefaultDatabasePath(var1: Boolean) {

    }

    override fun setGeolocationEnabled(var1: Boolean) {
        webSettingsProxy.setGeolocationEnabled(var1)
    }

    override fun setGeolocationDatabasePath(var1: String) {
        webSettingsProxy.setGeolocationDatabasePath(var1)
    }

    override fun setNeedInitialFocus(var1: Boolean) {
        webSettingsProxy.setNeedInitialFocus(var1)
    }

    override fun setAllowUniversalAccessFromFileURLs(var1: Boolean) {
        webSettingsProxy.setAllowFileAccessFromFileURLs(var1)
        allowUniversalAccessFromFileURLs = var1
    }

    override fun setAllowFileAccessFromFileURLs(var1: Boolean) {
        webSettingsProxy.setAllowFileAccessFromFileURLs(var1)
    }

    override fun setPluginEnabled(var1: Boolean) {
        if (var1) {
            webSettingsProxy.pluginState = WebSettings.PluginState.ON
        } else {
            webSettingsProxy.pluginState = WebSettings.PluginState.OFF
        }
    }

    override fun setRenderPriority(var1: IWebSettings.RenderPriority) {
        when (var1) {
            IWebSettings.RenderPriority.LOW ->
                webSettingsProxy.setRenderPriority(WebSettings.RenderPriority.LOW)
            IWebSettings.RenderPriority.NORMAL ->
                webSettingsProxy.setRenderPriority(WebSettings.RenderPriority.NORMAL)
            else ->
                webSettingsProxy.setRenderPriority(WebSettings.RenderPriority.HIGH)
        }
    }

    override fun supportMultipleWindows(): Boolean {
        return webSettingsProxy.supportMultipleWindows()
    }

    override fun supportZoom(): Boolean {
        return webSettingsProxy.supportZoom()
    }

    override fun setEnableSmoothTransition(var1: Boolean) {
        webSettingsProxy.setEnableSmoothTransition(var1)
    }

    override fun enableSmoothTransition(): Boolean {
        return webSettingsProxy.enableSmoothTransition()
    }
}