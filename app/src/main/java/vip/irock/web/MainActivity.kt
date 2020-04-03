package vip.irock.web

import android.content.Intent
import android.os.Bundle
import android.view.inputmethod.EditorInfo
import android.widget.AutoCompleteTextView
import androidx.appcompat.app.AppCompatActivity
import com.xzweb.android.R
import kotlinx.android.synthetic.main.activity_main.*
import vip.irock.web.adapters.WebViewHost
import vip.irock.web.cache.WebResourceCache

class MainActivity : AppCompatActivity() {

    private lateinit var mWebViewHost: WebViewHost

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val autoCompleteTextView = findViewById<AutoCompleteTextView>(R.id.autotext)
        autoCompleteTextView.setOnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_GO) {
                val url = autoCompleteTextView.text.toString()
                mWebViewHost.getWebView().loadUrl(url)
                true
            }
            false
        }
        mWebViewHost = findViewById(R.id.webViewHost)

        redirect.setOnClickListener {
            val url = autoCompleteTextView.text.toString()
            mWebViewHost.getWebView().loadUrl(url)
        }

//        mWebView.loadUrl("file:///android_asset/demo.html")
        mWebViewHost.getWebView().loadUrl("https://www.jianshu.com/")
        WebResourceCache.preload(this, "https://github.com")
    }

    override fun onResume() {
        super.onResume()
        mWebViewHost.getWebView().onResume()
    }

    override fun onPause() {
        super.onPause()
        mWebViewHost.getWebView().onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
        mWebViewHost.release()
    }

    override fun onBackPressed() {
        if (mWebViewHost.onBackPressed().not())
            super.onBackPressed()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        mWebViewHost.onFileChooserResult(requestCode, resultCode, data)
    }
}
