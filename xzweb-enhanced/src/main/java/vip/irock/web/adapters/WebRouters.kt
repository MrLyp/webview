package vip.irock.web.adapters

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.content.pm.ResolveInfo
import android.net.Uri


object WebRouters {

    fun processIntentUrl(context: Context, url: String) {
        try {
            val packageManager = context.packageManager
            val intent: Intent = Intent.parseUri(url, Intent.URI_INTENT_SCHEME)
            val info =
                packageManager.resolveActivity(intent, PackageManager.MATCH_DEFAULT_ONLY)
            if (info != null) {
                context.startActivity(intent)
            }
        } catch (ignore: Throwable) {

        }
    }

    fun processCommonLink(context: Context, url: String) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        val packageManager = context.packageManager
        val info =
            packageManager.resolveActivity(intent, PackageManager.MATCH_DEFAULT_ONLY)
                ?: return
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        context.startActivity(intent)
    }
}