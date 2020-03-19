package vip.irock.web.bridge

import android.content.Context
import android.net.Uri
import vip.irock.web.protocol.IWebView
import org.json.JSONObject


object NativeBridge {

    private lateinit var mContext: Context

    private val mHandlerMap = HashMap<String, Class<out JsHandler>>()

    init {
        mHandlerMap["showToast"] = ShowToastHandler::class.java
    }

    fun init(context: Context) {
        mContext = context
    }

    fun inject(view: IWebView) {
        val jsContent =
            "window.WebViewJavascriptBridge && window.WebViewJavascriptBridge.ready && window.WebViewJavascriptBridge.ready();"
        view.evaluateJavascript(jsContent, null)
    }

    fun registerHandler(name: String, clazz: Class<out JsHandler>) {
        if (mHandlerMap.containsKey(name))
            throw IllegalArgumentException("method named $name already exist")
        mHandlerMap[name] = clazz
    }

    fun unregisterHandler(name: String) {
        mHandlerMap.remove(name)
    }

    fun hasHandler(name: String): Boolean {
        return mHandlerMap.containsKey(name)
    }

    fun handleMessage(view: IWebView, message: String): Boolean {
        if (!message.startsWith("nb://nativebridge"))
            return false
        val uri = Uri.parse(message)
        val method = uri.getQueryParameter("method") ?: return false
        val id = uri.getQueryParameter("callbackId")
        val callback: BridgeCallback? = id?.let { BridgeCallback(view, it) }
        val handler = try {
            val clazz = mHandlerMap[method] ?: DefaultHandler::class.java
            clazz.getConstructor().newInstance()
        } catch (e: Exception) {
            DefaultHandler()
        }
        handler.handleMessage(mContext, JSONObject(uri.getQueryParameter("args")), callback)
        return true
    }
}