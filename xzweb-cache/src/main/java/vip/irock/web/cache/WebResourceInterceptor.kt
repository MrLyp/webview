package vip.irock.web.cache

import vip.irock.web.protocol.IWebResourceRequest
import vip.irock.web.protocol.IWebResourceResponse

interface WebResourceInterceptor {

    fun interceptRequest(request: IWebResourceRequest) : IWebResourceResponse?
}