package vip.irock.web.protocol

interface DownloadListener {
    fun onDownloadStart(url: String?, userAgent: String?, contentDisposition: String?, mimetype: String?, contentLength: Long)

    fun onDownloadStart(
        var1: String,
        var2: String,
        var3: ByteArray,
        var4: String,
        var5: String,
        var6: String,
        var7: Long,
        var9: String,
        var10: String
    )

    fun onDownloadVideo(var1: String, var2: Long, var4: Int)
}