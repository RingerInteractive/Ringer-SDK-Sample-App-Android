package com.ringer

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.util.Calendar

class NotificationActivity : AppCompatActivity() {

    lateinit var btnAllowNotifications: Button
    lateinit var txtPrivacy1 : TextView
    lateinit var txtTermsCondition : TextView
    lateinit var txtCopyRight: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notification)

        initialize()

        onClick()
    }

    private fun onClick() {
        btnAllowNotifications.setOnClickListener {

            PreferencesApp().setScreenNumber(this, 4)
            startActivity(
                    Intent(
                        this@NotificationActivity,
                        EditScreenActivity::class.java
                    )
                )
                finish()
        }
        txtTermsCondition.setOnClickListener {

            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(resources.getString(R.string.terms_url)))
            startActivity(browserIntent)

        }
        txtPrivacy1.setOnClickListener {
            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(resources.getString(R.string.privacy_url)))
            startActivity(browserIntent)
        }
        txtCopyRight.text =
            String.format(getString(R.string.copyright_2022_flash_app_llc), Calendar.getInstance().get(
                Calendar.YEAR));
    }

    private fun initialize() {
        btnAllowNotifications = findViewById(R.id.btn_allow_notifications)
        txtPrivacy1 = findViewById(R.id.txt_privacy1)
        txtTermsCondition = findViewById(R.id.txt_terms_condition)
        txtCopyRight = findViewById(R.id.copy_right)
    }
}