package vip.irock.web.webkit

import android.os.Build
import android.webkit.WebResourceError
import androidx.annotation.RequiresApi
import vip.irock.web.protocol.IWebResourceError

class WebkitWebResourceError(private val error: WebResourceError) :
    IWebResourceError {
    @RequiresApi(Build.VERSION_CODES.M)
    override fun getErrorCode(): Int {
        return error.errorCode
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun getDescription(): CharSequence {
        return error.description
    }
}