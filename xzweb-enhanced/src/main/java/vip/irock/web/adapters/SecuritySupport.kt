package vip.irock.web.adapters

import android.app.AlertDialog
import android.app.Dialog
import vip.irock.web.protocol.GeolocationPermissionsCallback
import vip.irock.web.protocol.ISslError
import vip.irock.web.protocol.ISslErrorHandler


class SecuritySupport(private val mViewHost: IViewHost) {

    private var mGeolocationDialog: Dialog? = null

    fun onGeolocationPermissionsHidePrompt() {
        mGeolocationDialog?.dismiss()
    }

    fun onGeolocationPermissionsShowPrompt(
        origin: String,
        callback: GeolocationPermissionsCallback
    ) {
        val builder =
            AlertDialog.Builder(mViewHost.getActivity())
        builder.setTitle("提示")
        builder.setMessage("是否允许" + origin + "使用您当前的位置？")

        builder.setPositiveButton(
            "允许"
        ) { _, _ -> callback.invoke(origin, var2 = true, var3 = false) }
        builder.setNegativeButton(
            "不允许"
        ) { _, _ -> callback.invoke(origin, var2 = false, var3 = false) }
        builder.setCancelable(true)

        mGeolocationDialog = builder.show()
    }

    fun onReceivedSslError(handler: ISslErrorHandler, error: ISslError) {
        val title = "安全警告"
        val message = "该网站的安全证书有问题。"

        val builder: AlertDialog.Builder = AlertDialog.Builder(mViewHost.getActivity())
        builder.setTitle(title)
            .setMessage(message)
            .setPositiveButton(
                "继续"
            ) { _, _ -> handler.proceed() }
            .setNegativeButton(
                "返回"
            ) { _, _ -> handler.cancel() }
        builder.setCancelable(false)
        builder.show()
    }
}