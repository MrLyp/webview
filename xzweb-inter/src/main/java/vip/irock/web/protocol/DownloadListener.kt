package vip.irock.web.protocol

interface DownloadListener {
    fun onDownloadStart(url: String?, userAgent: String?, contentDisposition: String?, mimetype: String?, contentLength: Long)
}