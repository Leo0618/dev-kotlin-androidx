package vip.okfood.ktx

import android.app.Application
import android.content.Context
import androidx.multidex.MultiDex
import vip.okfood.ktx.net.RetrofitWrap

/**
 * function:App
 *
 * <p></p>
 * Created by Leo on 2019/5/24.
 */
class App : Application() {

    companion object {
        /**
         * 获取全局上下文
         *
         * @return the mContext
         */
        var application: App? = null
            private set
        /**
         * 获取主线程Handler
         *
         * @return the mMainThreadHandler
         */
        var mainThreadHandler: android.os.Handler? = null
            private set
        /**
         * 获取主线程ID
         *
         * @return the mMainThreadId
         */
        var mainThreadId: Int = 0
            private set
    }

    override fun onCreate() {
        super.onCreate()
        application = this
        mainThreadHandler = android.os.Handler(mainLooper)
        mainThreadId = android.os.Process.myTid()
        RetrofitWrap.init("https://okfood.vip/", false)
    }

    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(base)
        MultiDex.install(base)
    }

}