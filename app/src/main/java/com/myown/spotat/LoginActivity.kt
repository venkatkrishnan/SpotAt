package com.myown.spotat

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
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

        setLog("Reached initializeWidgets", LogClass.DEBUG_MSG)

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

        setLog("Completed initializeWidgets", LogClass.DEBUG_MSG)
    }

    fun setClickListener() {
        loginUI.setOnClickListener({
            if(appGlobals.checkNetworkConnection(context)) {
                login()
            } else {
                appGlobals.customToast(context, resources.getString(R.string.no_network), Toast.LENGTH_LONG)
            }
        })

        continueUI.setOnClickListener({
            if(appGlobals.checkNetworkConnection(context)) {
                otp()
            } else {
                appGlobals.customToast(context, resources.getString(R.string.no_network), Toast.LENGTH_LONG)
            }
        })
    }

    fun login() {

        setLog("Reached login", LogClass.DEBUG_MSG)

        val countryCode = countryCodeUI.selectedCountryCode
        val mobile = mobileUI.text

        if(TextUtils.isEmpty(countryCode)) {
            appGlobals.customToast(context, resources.getString(R.string.invalid_country_code), Toast.LENGTH_LONG)
            return
        } else if(TextUtils.isEmpty(mobile)) {
            appGlobals.customToast(context, resources.getString(R.string.invalid_mobile), Toast.LENGTH_LONG)
            return
        }

        setLog("login country code $countryCode mobile $mobile", LogClass.DEBUG_MSG)

        appGlobals.customToast(context, countryCode + "-" + mobile, Toast.LENGTH_LONG)
        appGlobals.sharedPref.setLoginOtpMode(true)

        setLog("Completed login", LogClass.DEBUG_MSG)

        reloadLayout()
    }

    fun otp() {

        setLog("Reached otp", LogClass.DEBUG_MSG)

        val otp1 = otp1UI.text
        val otp2 = otp2UI.text
        val otp3 = otp3UI.text
        val otp4 = otp4UI.text

        if(TextUtils.isEmpty(otp1) || TextUtils.isEmpty(otp2) || TextUtils.isEmpty(otp3) ||
                TextUtils.isEmpty(otp4)) {
            appGlobals.customToast(context, resources.getString(R.string.invalid_otp), Toast.LENGTH_LONG)
            return
        }

        setLog("otp $otp1 $otp2 $otp3 $otp4", LogClass.DEBUG_MSG)

        setLog("Completed otp", LogClass.DEBUG_MSG)
        loadNextActivity();
    }

    fun loadNextActivity() {
        setLog("Reached loadNextActivity", LogClass.DEBUG_MSG)
        appGlobals.sharedPref.setLoggedIn(true)
        this.finish()
        val activityIntent = Intent(context, MainActivity::class.java)
        startActivity(activityIntent)
    }

    fun reloadLayout() {
        setLog("Reached reloadLayout", LogClass.DEBUG_MSG)
        loginOtpMode = appGlobals.sharedPref.getLoginOtpMode()

        setLog("layout load status $loginOtpMode", LogClass.DEBUG_MSG)

        if(!loginOtpMode) {
            loginLayoutUI.visibility = View.VISIBLE
            otpLayoutUI.visibility = View.GONE
        } else {
            loginLayoutUI.visibility = View.GONE
            otpLayoutUI.visibility = View.VISIBLE
        }
        setLog("Completed reloadLayout", LogClass.DEBUG_MSG)
    }

    fun setLog(msg : String, msgType : Int) {
        appGlobals.logClass.setLogMsg(TAG, msg, msgType)
    }
}
