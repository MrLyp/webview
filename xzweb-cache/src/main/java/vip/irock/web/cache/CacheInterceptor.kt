package vip.irock.web.cache

import android.net.Uri
import android.text.TextUtils
import android.util.Log
import android.webkit.MimeTypeMap
import android.webkit.URLUtil
import okhttp3.*
import vip.irock.web.protocol.IWebResourceRequest
import vip.irock.web.protocol.IWebResourceResponse
import java.io.File
import java.lang.Exception
import java.nio.charset.Charset
import java.util.*
import java.util.concurrent.TimeUnit


class CacheInterceptor(
    private val cacheConfig: CacheConfig,
    private val okHttpBuilder: OkHttpClient.Builder
) : WebResourceInterceptor {

    private val mHttpClient: OkHttpClient by lazy { buildOkHttpClient() }

    private fun buildOkHttpClient(): OkHttpClient {
        val cache = Cache(File(cacheConfig.cacheDir), cacheConfig.cacheSize)
        return okHttpBuilder.cache(cache).build()
    }

    override fun interceptRequest(request: IWebResourceRequest): IWebResourceResponse? {
        val uri = request.getUrl()
        if (!URLUtil.isNetworkUrl(uri.toString()))
            return null
        if (checkValidFileType(uri).not())
            return null
        return try {
            executeRequest(request)
        } catch (e: Exception) {
            null
        }
    }

    private fun checkValidFileType(uri: Uri): Boolean {
        val extension = MimeTypeMap.getFileExtensionFromUrl(uri.toString())
        return cacheConfig.canCache(extension)
    }

    private fun executeRequest(request: IWebResourceRequest): IWebResourceResponse? {
        val url: String = request.getUrl().toString()
        val reqBuilder: Request.Builder = Request.Builder()
            .url(url)
        request.getRequestHeaders().forEach {
            reqBuilder.removeHeader(it.key)
            reqBuilder.addHeader(it.key, it.value)
        }
        val cacheControl = CacheControl.Builder().maxStale(Int.MAX_VALUE, TimeUnit.SECONDS).build()

        val httpRequest: Request = reqBuilder.cacheControl(cacheControl).build()
        val response: Response = mHttpClient.newCall(httpRequest).execute()
        val cacheRes: Response? = response.cacheResponse
        val data = response.body?.byteStream() ?: return null
        val contentType: MediaType? = response.body?.contentType()

        val mimeType: String = contentType?.toString()
            ?: MimeTypeMap.getSingleton()
                .getMimeTypeFromExtension(MimeTypeMap.getFileExtensionFromUrl(url))
            ?: return null

        val encoding: String = contentType?.charset(Charset.forName("UTF-8"))?.toString() ?: "UTF-8"

        val headers = HashMap<String, String>()
        response.headers.toMultimap().forEach { it ->
            val sb = StringBuilder()
            for (v in it.value)
                sb.append(v).append(";")
            if (sb.isNotEmpty()) {
                sb.deleteCharAt(sb.length - 1)
            }
            headers[it.key] = sb.toString()
        }

        var message: String = response.message
        if (TextUtils.isEmpty(message)) {
            message = "OK"
        }
        return WebResourceResponseImpl(
            mimeType,
            encoding,
            response.code,
            message,
            headers,
            data
        )
    }
}