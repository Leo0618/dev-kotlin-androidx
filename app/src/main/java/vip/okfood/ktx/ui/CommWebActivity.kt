package vip.okfood.ktx.ui

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.webkit.WebChromeClient
import android.webkit.WebSettings
import android.webkit.WebView
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_commweb.*
import kotlinx.android.synthetic.main.layout_titlebar.*
import vip.okfood.ktx.R

/**
 * function:
 *
 * <p></p>
 * Created by Leo on 2019/5/24.
 */
class CommWebActivity: AppCompatActivity() {
    private lateinit var mWebView: WebView
    private lateinit var mLoadingProgressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_commweb)
        buttonTitleBack.setOnClickListener { onBackPressed() }
        mWebView = findViewById(R.id.wv)
        mLoadingProgressBar = findViewById(R.id.pb_loading)
        initWebView()
        mWebView.webChromeClient = object: WebChromeClient() {
            override fun onProgressChanged(view: WebView, newProgress: Int) {
                if (0 == newProgress || newProgress>=90) {
                    mLoadingProgressBar.visibility = View.GONE
                    return
                }
                mLoadingProgressBar.visibility = View.VISIBLE
                mLoadingProgressBar.progress = newProgress
            }

            override fun onReceivedTitle(view: WebView, title: String) {
                if (!TextUtils.isEmpty(title)) textTitle.text = title
            }
        }
        textTitle.text = intent.getStringExtra("title")
        mWebView.loadUrl(intent.getStringExtra("url"))
    }

    @SuppressLint("SetJavaScriptEnabled")
    fun initWebView() {
        val webSettings: WebSettings = wv.settings
        webSettings.domStorageEnabled = true //设置DOM Storage缓存
        webSettings.databaseEnabled = (true)//设置可使用数据库
        webSettings.javaScriptEnabled = (true)//支持js脚本
        webSettings.useWideViewPort = (true)//将图片调整到适合webview的大小
        webSettings.setSupportZoom(false)//支持缩放
        webSettings.builtInZoomControls = (false)//支持缩放
        webSettings.layoutAlgorithm = (WebSettings.LayoutAlgorithm.NORMAL)//支持内容从新布局
        webSettings.setSupportMultipleWindows(false)//多窗口
        webSettings.cacheMode = (WebSettings.LOAD_CACHE_ELSE_NETWORK)//关闭webview中缓存
        webSettings.allowFileAccess = (true)//设置可以访问文件
        webSettings.setNeedInitialFocus(true)//当webview调用requestFocus时为webview设置节点
        webSettings.javaScriptCanOpenWindowsAutomatically = (true)//支持通过JS打开新窗口
        webSettings.loadsImagesAutomatically = (true)//支持自动加载图片
        webSettings.setGeolocationEnabled(true)//启用地理定位
        webSettings.allowFileAccessFromFileURLs = (true)//使用允许访问文件的urls
        webSettings.allowUniversalAccessFromFileURLs = (true)//使用允许访问文件的urls
        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.JELLY_BEAN_MR1) {//媒体自动播放
            webSettings.mediaPlaybackRequiresUserGesture = (false)
        }
    }

    override fun onBackPressed() {
        if (wv.canGoBack()) {
            wv.goBack()
        } else {
            super.onBackPressed()
        }
    }

}