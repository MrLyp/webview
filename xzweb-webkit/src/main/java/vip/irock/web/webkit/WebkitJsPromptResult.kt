package vip.irock.web.webkit

import android.webkit.JsPromptResult
import vip.irock.web.protocol.IJsPromptResult

class WebkitJsPromptResult(private val result: JsPromptResult) :
    IJsPromptResult {
    override fun confirm(var1: String) {
        result.confirm(var1)
    }

    override fun cancel() {
        result.cancel()
    }
}