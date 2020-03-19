package vip.irock.web.webkit

import android.webkit.JsResult
import vip.irock.web.protocol.IJsResult

class WebkitJsResult(private val result: JsResult) :
    IJsResult {
    override fun cancel() {
        result.cancel()
    }

    override fun confirm() {
        result.confirm()
    }
}