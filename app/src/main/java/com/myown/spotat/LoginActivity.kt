package com.myown.spotat

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.hbb20.CountryCodePicker
import com.myown.spotat.utils.AppGlobals
import com.myown.spotat.utils.LogClass

class LoginActivity : AppCompatActivity() {

    val TAG = "LoginActivity";

    var context : Context? = null
    var appGlobals : AppGlobals ?= null

    var countryCodeUI : CountryCodePicker? = null
    var mobileUI : EditText? = null
    var loginUI : Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_activity)

        initializeWidgets()
        setClickListener()
    }

    fun initializeWidgets() {
        context = applicationContext
        appGlobals = AppGlobals.getInstance(context)

        countryCodeUI = findViewById(R.id.country_code) as CountryCodePicker
        mobileUI = findViewById(R.id.mobile) as EditText;
        loginUI = findViewById(R.id.login_btn) as Button;
    }

    fun setClickListener() {
        loginUI?.setOnClickListener({
            if(appGlobals?.connectionDetector?.isConnectingToInternet ?: false) {
                appGlobals?.logClass?.setLogMsg("", "Reached 2", LogClass.DEBUG_MSG)
                var countryCode = countryCodeUI?.selectedCountryCode
                var mobile = mobileUI?.text

                val activityIntent = Intent(context, MainActivity::class.java)
                startActivity(activityIntent)

            } else {
                appGlobals?.logClass?.setLogMsg("", "Reached 3", LogClass.DEBUG_MSG)
                appGlobals?.toastMsg(context, resources?.getString(R.string.no_network), Toast.LENGTH_LONG)
            }
        })
    }
}
