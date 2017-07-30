package com.myown.spotat

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.RelativeLayout
import android.widget.Toast
import com.hbb20.CountryCodePicker
import com.myown.spotat.utils.AppGlobals
import com.myown.spotat.utils.LogClass

class LoginActivity : AppCompatActivity() {

    val TAG = "LoginActivity"

    lateinit var context : Context
    lateinit var appGlobals : AppGlobals

    lateinit var countryCodeUI : CountryCodePicker

    lateinit var loginUI : Button
    lateinit var continueUI : Button

    lateinit var mobileUI : EditText
    lateinit var otp1UI : EditText
    lateinit var otp2UI : EditText
    lateinit var otp3UI : EditText
    lateinit var otp4UI : EditText

    lateinit var loginLayoutUI : RelativeLayout
    lateinit var otpLayoutUI : RelativeLayout

    var loginOtpMode : Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_activity)

        initializeWidgets()
        setClickListener()
    }

    override fun onResume() {
        super.onResume()

        reloadLayout()
    }

    fun initializeWidgets() {
        context = applicationContext
        appGlobals = AppGlobals.getInstance(context)

        countryCodeUI = findViewById(R.id.country_code) as CountryCodePicker
        mobileUI = findViewById(R.id.mobile) as EditText
        otp1UI = findViewById(R.id.otp1) as EditText
        otp2UI = findViewById(R.id.otp2) as EditText
        otp3UI = findViewById(R.id.otp3) as EditText
        otp4UI = findViewById(R.id.otp4) as EditText

        loginUI = findViewById(R.id.login_btn) as Button
        continueUI = findViewById(R.id.continue_btn) as Button

        loginLayoutUI = findViewById(R.id.login_layout) as RelativeLayout
        otpLayoutUI = findViewById(R.id.otp_layout) as RelativeLayout
    }

    fun setClickListener() {
        loginUI.setOnClickListener({
            if(appGlobals.checkNetworkConnection(context)) {
                if(!loginOtpMode) {
                    login()
                } else {
                    otp()
                }
            } else {
                appGlobals.customToast(context, resources.getString(R.string.no_network), Toast.LENGTH_LONG)
            }
        })
    }


    fun login() {
        val countryCode = countryCodeUI.selectedCountryCode
        val mobile = mobileUI.text

        appGlobals.logClass.setLogMsg(TAG, "Reached login", LogClass.DEBUG_MSG)

        if(TextUtils.isEmpty(countryCode)) {
            appGlobals.customToast(context, resources.getString(R.string.invalid_country_code), Toast.LENGTH_LONG)
            return
        } else if(TextUtils.isEmpty(mobile)) {
            appGlobals.customToast(context, resources.getString(R.string.invalid_mobile), Toast.LENGTH_LONG)
            return
        }

        appGlobals.customToast(context, countryCode + "-" + mobile, Toast.LENGTH_LONG)
        appGlobals.sharedPref.setLoginOtpMode(true)
        reloadLayout()
    }

    fun otp() {

        appGlobals.logClass.setLogMsg(TAG, "Reached otp", LogClass.DEBUG_MSG)

        val otp1 = otp1UI.text
        val otp2 = otp2UI.text
        val otp3 = otp3UI.text
        val otp4 = otp4UI.text

        if(TextUtils.isEmpty(otp1) || TextUtils.isEmpty(otp2) || TextUtils.isEmpty(otp3) ||
                TextUtils.isEmpty(otp4)) {
            appGlobals.customToast(context, resources.getString(R.string.invalid_otp), Toast.LENGTH_LONG)
            return
        }
    }

    fun reloadLayout() {
        loginOtpMode = appGlobals.sharedPref.getLoginOtpMode()

        if(!loginOtpMode) {
            loginLayoutUI.visibility = View.VISIBLE
            otpLayoutUI.visibility = View.GONE
        } else {
            loginLayoutUI.visibility = View.GONE
            otpLayoutUI.visibility = View.VISIBLE
        }
    }
}
