package vip.okfood.ktx.ui

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import vip.okfood.ktx.App
import vip.okfood.ktx.R

/**
 * function:SplashActivity
 *
 * <p></p>
 * Created by Leo on 2019/9/29.
 */
class SplashActivity: AppCompatActivity() {
    private val timeDelay: Long = 2000
    private var timeStart: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        timeStart = System.currentTimeMillis()

        val permissions: Array<String> = arrayOf(
                Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE
        )
        var permissioned = true
        permissions.forEach {
            if (PackageManager.PERMISSION_GRANTED != ActivityCompat.checkSelfPermission(this, it)) {
                permissioned = false
                return@forEach
            }
        }
        if (!permissioned) {
            AlertDialog.Builder(this@SplashActivity).apply {
                setTitle(getString(R.string.app_name))
                setCancelable(false)
                setMessage("确保应用正常使用前，请允许应用需要的权限")
                setNegativeButton("退出") { dialog, _ ->
                    dialog.cancel()
                    dialog.dismiss()
                    App.mainThreadHandler?.postDelayed({ finish() }, 200)
                }
                setPositiveButton("好的") { dialog, _ ->
                    dialog.cancel()
                    dialog.dismiss()
                    ActivityCompat.requestPermissions(this@SplashActivity, permissions, 1000)
                }
            }.show()
        } else {
            goMain()
        }
    }

    private fun goMain() {
        var lastTime: Long = timeDelay-(System.currentTimeMillis()-timeStart)
        if (lastTime<0) lastTime = 0
        App.mainThreadHandler!!.postDelayed({
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }, lastTime)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        if (requestCode != 1000) return
        var permissioned = true
        permissions.forEach {
            if (PackageManager.PERMISSION_GRANTED != ActivityCompat.checkSelfPermission(this, it)) {
                permissioned = false
                return@forEach
            }
        }
        if (permissioned) {
            goMain()
        } else {
            Toast.makeText(this, "使用应用前请允许必要的权限", Toast.LENGTH_SHORT).show()
            finish()
        }
    }
}
