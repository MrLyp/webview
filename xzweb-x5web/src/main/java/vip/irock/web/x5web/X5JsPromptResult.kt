package vip.irock.web.x5web

import com.tencent.smtt.export.external.interfaces.JsPromptResult
import vip.irock.web.protocol.IJsPromptResult

class X5JsPromptResult(private val result: JsPromptResult) :
    IJsPromptResult {
    override fun confirm(var1: String) {
        result.confirm(var1)
    }

    override fun cancel() {
        result.cancel()
    }
}