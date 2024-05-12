package com.ringer

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.widget.Button
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.ringer.sdk.permission.offerReplacingDefaultDialer


class MainActivity : AppCompatActivity() {
    private lateinit var btnContinue: Button
    companion object {
        const val REQUEST_CODE_DEFAULT_PHONE = 2
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnContinue = findViewById(R.id.btn_continue)


        btnContinue.setOnClickListener {

            //SDK Default Dialer
            offerReplacingDefaultDialer(
                this, applicationContext.packageName,
                REQUEST_CODE_DEFAULT_PHONE
            )

        }

        val timer = object : CountDownTimer(3000, 1000) {
            override fun onTick(millisUntilFinished: Long) {}

            @RequiresApi(Build.VERSION_CODES.Q)
            override fun onFinish() {
                // 0 = Get Contact Number
                // 1 = Default Dialer Set
                // 2 = contact
                // 3 = Allow On Top
                // 4 = Notification
                // 5 = editscreen
                val screenNumber = PreferencesApp().getScreenNumber(this@MainActivity);
                Log.e("screenNumber", screenNumber.toString())
                when (screenNumber) {

                    1 -> startActivity(
                        Intent(
                            this@MainActivity,
                            DefaultPhoneActivity::class.java
                        )
                    )
                    2 -> startActivity(
                        Intent(
                            this@MainActivity,
                            AccessContactActivity::class.java
                        )
                    )
                    3 -> startActivity(
                        Intent(
                            this@MainActivity,
                            AppearOnTopActivity::class.java
                        )
                    )
                    4 -> startActivity(
                        Intent(
                            this@MainActivity,
                            NotificationActivity::class.java
                        )
                    )
                    5 -> startActivity(
                        Intent(
                            this@MainActivity,
                            EditScreenActivity::class.java
                        )
                    )
                    6 -> startActivity(
                        Intent(
                            this@MainActivity,
                            EditScreenActivity::class.java
                        )
                    )
                }
                finish()


            }
        }
        timer.start()


    }

}