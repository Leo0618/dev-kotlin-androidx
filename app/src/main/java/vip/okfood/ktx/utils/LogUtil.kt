@file:Suppress("unused", "MemberVisibilityCanBePrivate")

package vip.okfood.ktx.utils


import vip.okfood.ktx.BuildConfig

/**
 * function : 日志输出.
 *
 *
 *
 * Created by Leo on 2015/12/31.
 */
object LogUtil {
    var isLoggable = BuildConfig.DEBUG
        private set
    private const val TAG_DEFAULT = "TAG_DEFAULT"

    fun config(debug: Boolean) {
        isLoggable = debug
    }

    fun v(content: String) {
        v(TAG_DEFAULT, content)
    }

    fun v(tag: String, content: String?) {
        if (!isLoggable) return
        android.util.Log.v(tag, content ?: "null")
    }

    fun w(content: String) {
        w(TAG_DEFAULT, content)
    }

    fun w(tag: String, content: String?) {
        if (!isLoggable) return
        android.util.Log.w(tag, content ?: "null")
    }

    fun i(content: String) {
        i(TAG_DEFAULT, content)
    }

    fun i(tag: String, content: String?) {
        if (!isLoggable) return
        android.util.Log.i(tag, content ?: "null")
    }

    fun d(content: String) {
        d(TAG_DEFAULT, content)
    }

    fun d(tag: String, content: String?) {
        if (!isLoggable) return
        android.util.Log.d(tag, content ?: "null")
    }

    fun e(content: String) {
        e(TAG_DEFAULT, content)
    }

    fun e(tag: String, content: String?) {
        if (!isLoggable) return
        android.util.Log.e(tag, content ?: "null")
    }


}
