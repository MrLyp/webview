package vip.irock.web.protocol

import java.io.InputStream

interface IWebResourceResponse {

    fun setMimeType(var1: String)

    fun getMimeType(): String

    fun setEncoding(var1: String)

    fun getEncoding(): String

    fun setStatusCodeAndReasonPhrase(var1: Int, var2: String)

    fun getStatusCode(): Int

    fun getReasonPhrase(): String

    fun setResponseHeaders(var1: Map<String, String>)

    fun getResponseHeaders(): Map<String, String>

    fun setData(var1: InputStream)

    fun getData(): InputStream
}