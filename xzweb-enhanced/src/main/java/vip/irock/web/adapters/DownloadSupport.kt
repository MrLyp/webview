package vip.irock.web.adapters

import android.content.Intent
import android.net.Uri
import vip.irock.web.protocol.DownloadListener


class DownloadSupport(private val mViewHost: IViewHost) : DownloadListener {
    override fun onDownloadStart(
        url: String?,
        userAgent: String?,
        contentDisposition: String?,
        mimetype: String?,
        contentLength: Long
    ) {
        val uri: Uri = Uri.parse(url)
        val intent = Intent(Intent.ACTION_VIEW, uri)
        mViewHost.getActivity().startActivity(intent)
    }
}