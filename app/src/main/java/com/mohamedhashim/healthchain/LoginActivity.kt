package com.mohamedhashim.healthchain

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import br.com.simplepass.loading_button_lib.customViews.CircularProgressImageButton

/**
 * Created by Mohamed Hashim on 6/17/2018.
 */
class LoginActivity : AppCompatActivity() {

    private lateinit var login_btn: CircularProgressImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        login_btn = findViewById<CircularProgressImageButton>(R.id.btn_login)
        login_btn.setOnClickListener {
            var intent: Intent = Intent(this, DashboardActivity::class.java)
            startActivity(intent)
        }
    }
}