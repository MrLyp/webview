package vip.irock.web.cache

import vip.irock.web.protocol.IWebResourceRequest
import vip.irock.web.protocol.IWebResourceResponse

class LocalResourceInterceptor : WebResourceInterceptor {
    override fun interceptRequest(request: IWebResourceRequest): IWebResourceResponse? {
        return null
    }
}