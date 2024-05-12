package com.ringer

import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SwitchCompat
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.ringer.dial.DialPadDialogFragment
import com.ringer.sdk.firebase.InitializeToken
import com.ringer.sdk.permission.offerReplacingDefaultDialer
import com.ringer.sdk.pref.Preferences
import com.ringer.sdk.utils.getDefaultDialerApp
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale


class EditScreenActivity : AppCompatActivity() {
    lateinit var btnAllowSetting: Button
    lateinit var switchNoti: SwitchCompat
    lateinit var switchContact: SwitchCompat
    lateinit var switchDefaultPhone: SwitchCompat
    lateinit var switchAppearOnTop: SwitchCompat
    lateinit var txtPrivacy1: TextView
    lateinit var txtTermsCondition: TextView
    lateinit var txtContactUs: TextView
    lateinit var imgMenu: ImageView
    lateinit var fabDialPad: FloatingActionButton
    lateinit var imgLogo: ImageView
    lateinit var txtCopyRight: TextView
    @RequiresApi(Build.VERSION_CODES.Q)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_screen)
        fabDialPad = findViewById(R.id.fab_dial_pad)
        if (getDefaultDialerApp(this).equals("com.ringer"))
            PreferencesApp().setScreenNumber(this, 5)
        else {
            offerReplacingDefaultDialer(
                this, applicationContext.packageName,
                DefaultPhoneActivity.REQUEST_CODE_DEFAULT_PHONE
            )
        }
        initialize()
        InitializeToken(
            this, "",resources.getString(R.string.app_name), "",
            Preferences().getTokenBaseUrl(this), Preferences().getCustomBasicAuthen(this))
        onClick()

    }
    private fun readableCurrentTime(): String? {
        val simpleDateFormat = SimpleDateFormat("EEEE.LLLL.yyyy KK:mm:ss aaa z", Locale.US)
        return simpleDateFormat.format(Date())
    }

    private fun onClick() {
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
        txtContactUs.setOnClickListener {

            val intent = Intent(Intent.ACTION_SENDTO) // it's not ACTION_SEND
            intent.data = Uri.parse("mailto:info@flashappllc.com") // or just "mailto:" for blank
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK) // this will make such that when user returns to your app, your app is displayed, instead of the email app.
            startActivity(intent)

        }
        btnAllowSetting.setOnClickListener {
            val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
            intent.data = Uri.parse("package:" + packageName)
            startActivity(intent)
        }
        txtCopyRight.text =
            String.format(getString(R.string.copyright_2022_flash_app_llc), Calendar.getInstance().get(
                Calendar.YEAR));

    }

    private fun initialize() {
        btnAllowSetting = findViewById(R.id.btn_allow_setting)
        switchNoti = findViewById(R.id.switch_noti)
        switchContact = findViewById(R.id.switch_contact)
        switchDefaultPhone = findViewById(R.id.switch_default_phone)
        switchAppearOnTop = findViewById(R.id.switch_appear_on_top)
        txtPrivacy1 = findViewById(R.id.txt_privacy1)
        txtTermsCondition = findViewById(R.id.txt_terms_condition)
        txtContactUs = findViewById(R.id.txt_contact_us)
        imgLogo = findViewById(R.id.img_logo)
        txtCopyRight = findViewById(R.id.copy_right)

        var tap_count = 0;

        imgLogo.setOnClickListener {
            tap_count += 1;
        }

        imgMenu = findViewById(R.id.img_menu)


        imgMenu.setOnClickListener {

            Constant.openuserMenuDialog(it, this@EditScreenActivity)
        }
        fabDialPad.setOnClickListener {
            val sheet = DialPadDialogFragment()
            sheet.show(supportFragmentManager, "DemoBottomSheetFragment")
        }
    }
}