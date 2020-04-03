package vip.irock.web.adapters

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.text.TextUtils
import android.webkit.ValueCallback
import vip.irock.web.protocol.IWebChromeClient


internal object FileChooserSupport {

    const val FILECHOOSER_RESULTCODE = 0xff00

    private var mUploadMessage: ValueCallback<Array<Uri>>? = null

    fun onShowFileChooser(
        viewHost: IViewHost,
        filePathCallback: ValueCallback<Array<Uri>>,
        fileChooserParams: IWebChromeClient.FileChooserParams
    ): Boolean {
        mUploadMessage?.onReceiveValue(null)
        mUploadMessage = filePathCallback
        val i = Intent(Intent.ACTION_OPEN_DOCUMENT)
        i.addCategory(Intent.CATEGORY_OPENABLE)
        i.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
        if (fileChooserParams.getAcceptTypes().isNotEmpty()
        ) {
            i.type = fileChooserParams.getAcceptTypes()[0]
        } else {
            i.type = "*/*"
        }
        viewHost.getActivity().startActivityForResult(
            Intent.createChooser(i, "File Chooser"),
            FILECHOOSER_RESULTCODE
        )
        return true
    }

    fun onFileChooserResult(resultCode: Int, data: Intent?) {
        if (null == mUploadMessage) return
        if (data == null || resultCode != Activity.RESULT_OK) {
            invokeCallback(null)
            return
        }
        invokeCallback(processData(data))
    }

    private fun processData(data: Intent?): Array<Uri>? {
        var datas: Array<Uri>? = null
        if (data == null) {
            return null
        }
        val target = data.dataString
        if (!TextUtils.isEmpty(target)) {
            return arrayOf(Uri.parse(target))
        }
        val mClipData = data.clipData
        if (mClipData != null && mClipData.itemCount > 0) {
            datas = Array<Uri>(mClipData.itemCount) {
                mClipData.getItemAt(it).uri
            }
        }
        return datas
    }

    private fun invokeCallback(result: Array<Uri>?) {
        mUploadMessage?.onReceiveValue(result)
        mUploadMessage = null
    }
}