package vip.okfood.ktx.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import vip.okfood.ktx.MyApp
import vip.okfood.ktx.R

/**
 * function:SplashActivity
 *
 * <p></p>
 * Created by Leo on ${DATE}.
 */
class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        MyApp.mainThreadHandler!!.postDelayed({
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }, 2000)
    }
}
