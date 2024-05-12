package com.ringer

import android.Manifest
import android.app.Dialog
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.os.Bundle
import android.view.Window
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.ringer.sdk.permission.RingerInteractive
import java.util.Calendar

class AccessContactActivity : AppCompatActivity() {

    lateinit var btnAllowContact: Button
    lateinit var txtPrivacy1: TextView
    lateinit var txtPrivacy2: TextView
    lateinit var txtTermsCondition: TextView
    lateinit var btnNo: Button
    lateinit var txtCopyRight: TextView

    val PERMISSIONS_REQUEST_READ_CONTACTS = 100

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_access_contact)
        initialize()
        onClick()

    }

    private fun onClick() {
        btnAllowContact.setOnClickListener {
            val dialog = Dialog(this)
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
            dialog.setCancelable(false)
            dialog.setContentView(R.layout.contact_access_popup)
            dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            val dialogBtn_no = dialog.findViewById<TextView>(R.id.btn_popup_access_contact_no)
            dialogBtn_no.setOnClickListener {
                dialog.dismiss()
            }
            val dialogBtn_yes = dialog.findViewById<TextView>(R.id.btn_popup_access_contact_yes)
            dialogBtn_yes.setOnClickListener {
                //Request Permission to Continue
                if (ContextCompat.checkSelfPermission(
                        this,
                        Manifest.permission.READ_CONTACTS
                    ) != PackageManager.PERMISSION_GRANTED
                ) {
                    ActivityCompat.requestPermissions(
                        this,
                        arrayOf(
                            Manifest.permission.READ_CONTACTS,
                            Manifest.permission.WRITE_CONTACTS,
                            Manifest.permission.READ_CALL_LOG,
                            Manifest.permission.WRITE_CALL_LOG

                        ),
                        PERMISSIONS_REQUEST_READ_CONTACTS
                    )

                } else {
                    PreferencesApp().setScreenNumber(this, 2)
                    startActivity(
                        Intent(
                            this@AccessContactActivity,
                            AppearOnTopActivity::class.java
                        )
                    )
                    finish()
                }
                dialog.dismiss()
            }
            dialog.show()
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
        txtPrivacy2.setOnClickListener {
            val browserIntent =
                Intent(Intent.ACTION_VIEW, Uri.parse(resources.getString(R.string.privacy_url)))
            startActivity(browserIntent)
        }
        txtCopyRight.text =
            String.format(getString(R.string.copyright_2022_flash_app_llc), Calendar.getInstance().get(Calendar.YEAR));
    }

    private fun initialize() {
        btnAllowContact = findViewById(R.id.btn_allow_contact)
        txtPrivacy1 = findViewById(R.id.txt_privacy1)
        txtPrivacy2 = findViewById(R.id.txt_privacy2)
        txtTermsCondition = findViewById(R.id.txt_terms_condition)
        btnNo = findViewById(R.id.btn_no)
        txtCopyRight = findViewById(R.id.copy_right)
    }
    //Permission
    override fun onRequestPermissionsResult(
        requestCode: Int, permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        RingerInteractive().onRequestPermissionsResult(requestCode, permissions, grantResults, this)
        if (requestCode == PERMISSIONS_REQUEST_READ_CONTACTS) {

            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                PreferencesApp().setScreenNumber(this, 2)

                startActivity(
                    Intent(
                        this@AccessContactActivity,
                        AppearOnTopActivity::class.java
                    )
                )
                finish()
            }

        }

    }

}