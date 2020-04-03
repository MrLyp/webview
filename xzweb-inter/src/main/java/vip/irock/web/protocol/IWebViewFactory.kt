package vip.irock.web.protocol

import android.content.Context

interface IWebViewFactory {

    fun createWebView(context: Context) : IWebView
}