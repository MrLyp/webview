package vip.irock.web.adapters

import android.app.Activity
import android.content.Intent
import vip.irock.web.protocol.IWebView

interface IViewHost {

    fun getActivity(): Activity

    fun onReceivedTitle(title: String)

    fun onPageStarted()

    fun onPageFinished(url: String?)

    fun onProgressChanged(progress: Int)

    fun getWebView(): IWebView

    fun release()

    fun onBackPressed(): Boolean

    fun onFileChooserResult(requestCode: Int, resultCode: Int, data: Intent?)
}