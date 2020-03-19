package vip.irock.web.adapters

import android.util.Log
import vip.irock.web.bridge.NativeBridge
import vip.irock.web.protocol.IConsoleMessage
import vip.irock.web.protocol.IJsPromptResult
import vip.irock.web.protocol.IWebChromeClient
import vip.irock.web.protocol.IWebView

class WebChromeClientImpl() : IWebChromeClient {

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
        Log.d(var1.sourceId(), "line ${var1.lineNumber()},${var1.message()}")
        return true
    }
}