package vip.irock.web

import android.app.Application
import com.tencent.smtt.sdk.QbSdk

class MApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        QbSdk.preInit(this, null)
    }
}