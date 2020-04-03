package vip.irock.web.cache

import vip.irock.web.protocol.IWebResourceRequest
import vip.irock.web.protocol.IWebResourceResponse

class LocalAssetsInterceptor : WebResourceInterceptor {
    override fun interceptRequest(request: IWebResourceRequest): IWebResourceResponse? {
        return null
    }
}