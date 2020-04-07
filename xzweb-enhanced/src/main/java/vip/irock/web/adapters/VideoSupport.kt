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

    private val COVER_SCREEN_PARAMS = FrameLayout.LayoutParams(
        ViewGroup.LayoutParams.MATCH_PARENT,
        ViewGroup.LayoutParams.MATCH_PARENT
    )

    fun onShowCustomView(
        view: View,
        callback: IWebChromeClient.ICustomViewCallback,
        requestedOrientation: Int = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
    ) {
        val activity = mActivity
        if (activity.isFinishing) {
            return
        }
        if (mMovieView != null) {
            callback.onCustomViewHidden()
            return
        }
        mCallback = callback
        mMovieView = view

        mActivityOrientation = activity.requestedOrientation

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
        val decorView = activity.window.decorView as FrameLayout
        mMovieParentView = FrameLayout(activity)
        mMovieParentView?.addView(mMovieView, COVER_SCREEN_PARAMS)
        decorView.addView(mMovieParentView, COVER_SCREEN_PARAMS)
        activity.requestedOrientation = requestedOrientation
    }

    fun onHideCustomView() {
        val activity = mActivity
        if (activity.isFinishing) {
            return
        }

        if (mFlags.isNotEmpty()) {
            for ((first, second) in mFlags) {
                activity.window.setFlags(second, first)
            }
            mFlags.clear()
        }
        val decorView = activity.window.decorView as FrameLayout
        decorView.removeView(mMovieParentView)
        mMovieView = null
        mCallback?.onCustomViewHidden()
        activity.requestedOrientation = mActivityOrientation
    }
}