package com.ringer

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.ringer.sdk.permission.RingerCallLogDetail
import java.util.Calendar

class AppearOnTopActivity : AppCompatActivity() {

    lateinit var btnAppear: Button
    lateinit var txtPrivacy1: TextView
    lateinit var txtTermsCondition: TextView
    lateinit var txtCopyRight: TextView
    private val PERMISSIONS_REQUEST_CALL_LOG = 101
    private var isAskForPermission = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_appear_on_top)

        initialize()

        onClick()
    }

    private fun onClick() {
        btnAppear.setOnClickListener {

            if (ContextCompat.checkSelfPermission(
                    this,
                    Manifest.permission.SYSTEM_ALERT_WINDOW
                ) != PackageManager.PERMISSION_GRANTED
            ) {

                //Request Permission to Continue

                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(
                        Manifest.permission.SYSTEM_ALERT_WINDOW
                    ),
                    PERMISSIONS_REQUEST_CALL_LOG
                )
            } else {

                PreferencesApp().setScreenNumber(this, 3)
                startActivity(Intent(this@AppearOnTopActivity, NotificationActivity::class.java))
                finish()
            }


        }
        txtTermsCondition.setOnClickListener {
            val browserIntent =
                Intent(Intent.ACTION_VIEW, Uri.parse(resources.getString(R.string.terms_url)))
            startActivity(browserIntent)
        }

        txtPrivacy1.setOnClickListener {
            val browserIntent =
                Intent(Intent.ACTION_VIEW, Uri.parse(resources.getString(R.string.privacy_url)))
            startActivity(browserIntent)
        }
        txtCopyRight.text =
            String.format(getString(R.string.copyright_2022_flash_app_llc), Calendar.getInstance().get(
                Calendar.YEAR));
    }


    private fun initialize() {
        btnAppear = findViewById(R.id.btn_appear)
        txtPrivacy1 = findViewById(R.id.txt_privacy1)
        txtTermsCondition = findViewById(R.id.txt_terms_condition)
        txtCopyRight = findViewById(R.id.copy_right)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int, permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        RingerCallLogDetail().onRequestPermissionsResult(
            requestCode,
            permissions,
            grantResults,
            this
        )
        if (requestCode == PERMISSIONS_REQUEST_CALL_LOG) {

            Log.e("grantResult",grantResults[0].toString());
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                PreferencesApp().setScreenNumber(this, 3)
                isAskForPermission = true;
                startActivity(
                    Intent(
                        this@AppearOnTopActivity,
                        NotificationActivity::class.java
                    )
                )
                finish()

            }

        }

    }

    override fun onRestart() {
        super.onRestart()
            PreferencesApp().setScreenNumber(this, 3)
            startActivity(Intent(this@AppearOnTopActivity, NotificationActivity::class.java))
            finish()
    }

}