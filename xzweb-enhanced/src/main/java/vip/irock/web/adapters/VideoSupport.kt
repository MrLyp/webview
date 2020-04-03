package vip.irock.web.adapters

import android.app.Activity
import android.content.pm.ActivityInfo
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.view.WindowManager
import android.widget.FrameLayout
import vip.irock.web.protocol.IWebChromeClient


class VideoSupport(private val mActivity: Activity) {

    private var mFlags: MutableSet<Pair<Int, Int>> = HashSet()
    private var mMovieView: View? = null
    private var mMovieParentView: ViewGroup? = null
    private var mCallback: IWebChromeClient.ICustomViewCallback? = null
    private var mActivityOrientation: Int = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

    fun onShowCustomView(
        view: View,
        callback: IWebChromeClient.ICustomViewCallback,
        requestedOrientation: Int = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
    ) {
        val activity = mActivity
        if (activity.isFinishing) {
            return
        }
        mCallback = callback
        mMovieView = view

        mActivityOrientation = activity.requestedOrientation
        activity.requestedOrientation = requestedOrientation

        val activityWindow: Window = activity.window
        var flagPair: Pair<Int, Int>
        // 保存当前屏幕的状态
        val flags = activityWindow.attributes.flags
        if (flags and WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON == 0) {
            flagPair = Pair(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON, 0)
            activityWindow.setFlags(
                WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON,
                WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON
            )
            mFlags.add(flagPair)
        }
        if (flags and WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED == 0) {
            flagPair = Pair(WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED, 0)
            activityWindow.setFlags(
                WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED,
                WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED
            )
            mFlags.add(flagPair)
        }
        if (flags and WindowManager.LayoutParams.FLAG_FULLSCREEN == 0) {
            flagPair = Pair(WindowManager.LayoutParams.FLAG_FULLSCREEN, 0)
            activityWindow.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
            )
            mFlags.add(flagPair)
        }
        if (mMovieView != null) {
            callback.onCustomViewHidden()
            return
        }
        if (mMovieParentView == null) {
            val mDecorView = activity.window.decorView as FrameLayout
            mMovieParentView = FrameLayout(activity)
            mDecorView.addView(mMovieParentView)
        }
        mMovieParentView?.addView(mMovieView)
        mMovieParentView?.visibility = View.VISIBLE
    }

    fun onHideCustomView() {
        val movieView = mMovieView ?: return
        val activity = mActivity
        if (activity.isFinishing) {
            return
        }
        activity.requestedOrientation = mActivityOrientation
        if (mFlags.isNotEmpty()) {
            for ((first, second) in mFlags) {
                activity.window.setFlags(second, first)
            }
            mFlags.clear()
        }
        movieView.visibility = View.GONE
        mMovieParentView?.removeView(movieView)
        mMovieParentView?.visibility = View.GONE
        mCallback?.onCustomViewHidden()
        mMovieView = null
    }
}