package vip.irock.web.bridge

import android.content.Context
import org.json.JSONObject

interface JsHandler {

    fun handleMessage(context: Context, args: JSONObject?, callback: BridgeCallback?)
}