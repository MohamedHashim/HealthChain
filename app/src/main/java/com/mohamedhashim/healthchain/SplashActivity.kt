package com.mohamedhashim.healthchain

import android.content.Intent
import android.graphics.Typeface
import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import android.widget.TextView

class SplashActivity : AppCompatActivity() {
    private lateinit var tvAppName: TextView
    private lateinit var tvAppInfo: TextView
    private val SPLASH_TIME_OUT = 3000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        tvAppName = findViewById<TextView>(R.id.tv_app_name)
        tvAppInfo = findViewById<TextView>(R.id.tv_app_info)
        val cera_round_font = Typeface.createFromAsset(assets, "fonts/cera_round_pro_demo_medium.otf")
        tvAppName.typeface = cera_round_font
        tvAppInfo.typeface = cera_round_font

        Handler().postDelayed(Runnable {
            val i = Intent(this@SplashActivity, LoginActivity::class.java)
            startActivity(i)
            finish()
        }, SPLASH_TIME_OUT.toLong())
    }
}
