package vip.irock.web.bridge

import android.content.Context
import org.json.JSONException
import org.json.JSONObject


class DefaultHandler : JsHandler {
    override fun handleMessage(
        context: Context,
        args: JSONObject?,
        callback: BridgeCallback?
    ) {
        val jsonObject = JSONObject()
        try {
            jsonObject.put("status", "fail")
            jsonObject.put("errMsg", "ERR_NOT_IMPLEMENTED")
        } catch (e: JSONException) {
        }
        callback?.call(jsonObject)
    }
}