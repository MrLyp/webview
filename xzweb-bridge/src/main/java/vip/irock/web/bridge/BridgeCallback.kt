package vip.irock.web.bridge

import vip.irock.web.protocol.IWebView
import org.json.JSONObject

class BridgeCallback(private val view: IWebView, private val callbackId: String) {

    fun call(result: JSONObject?) {
        val data = result?.toString() ?: ""
        val jsString =
            "window.WebViewJavascriptBridge && window.WebViewJavascriptBridge.callback && window.WebViewJavascriptBridge.callback('$callbackId','$data');"
        postEvaluate(jsString)
    }

    private fun postEvaluate(script: String) {
        view.getView().post {
            view.evaluateJavascript(script, null)
        }
    }
}