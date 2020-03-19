package vip.irock.web.bridge

import android.content.Context
import android.widget.Toast
import org.json.JSONObject

class ShowToastHandler: JsHandler {

    override fun handleMessage(context: Context, args: JSONObject?, callback: BridgeCallback?) {
        if (args == null || !args.has("message"))
            return
        val message:String = args["message"] as String
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
        val jsonObject = JSONObject()
        jsonObject.put("status", "success")
        jsonObject.put("result", "complete")
        callback?.call(jsonObject)
    }
}