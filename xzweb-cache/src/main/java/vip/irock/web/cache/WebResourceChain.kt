package vip.irock.web.cache

import vip.irock.web.protocol.IWebResourceRequest
import vip.irock.web.protocol.IWebResourceResponse

class WebResourceChain {

    private val mInterceptors = ArrayList<WebResourceInterceptor>()

    private var mRequest: IWebResourceRequest? = null

    public fun process(request: IWebResourceRequest): IWebResourceResponse? {
        for (interceptor in mInterceptors) {
            val response = interceptor.interceptRequest(request)
            if (response != null)
                return response
        }
        return null
    }

    fun addInterceptor(interceptor: WebResourceInterceptor) {
        mInterceptors.add(interceptor)
    }

    fun getRequest(): IWebResourceRequest? {
        return mRequest
    }
}