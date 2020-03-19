package vip.irock.web.x5web

import com.tencent.smtt.export.external.interfaces.JsResult
import vip.irock.web.protocol.IJsResult

class X5JsResult(private val result: JsResult) :
    IJsResult {
    override fun cancel() {
        result.cancel()
    }

    override fun confirm() {
        result.confirm()
    }
}