package com.myown.spotat.utils

import android.content.SharedPreferences
import android.content.Context
import com.myown.spotat.R

/**
 * Created by Admin on 7/30/2017.
 */
class SharedPref {

    lateinit var sharedPref: SharedPreferences
    lateinit var editor: SharedPreferences.Editor

    constructor(context : Context) {
        sharedPref = context.getSharedPreferences(context.getString(R.string.app_name), Context.MODE_PRIVATE)
        editor = sharedPref.edit()
    }

    val DEBUG_MODE : String = "debugMode"
    val LOGIN_OTP_MODE : String = "loginOtpMode"

    fun clearAllPref() {
        editor.clear()
        editor.commit()
    }

    fun setDebugMode(mode : Boolean) {
        editor.putBoolean(DEBUG_MODE, mode)
        editor.commit()
    }

    fun setLoginOtpMode(loginOtp : Boolean) {
        editor.putBoolean(LOGIN_OTP_MODE, loginOtp)
        editor.commit()
    }

    fun getDebugMode() : Boolean {
        return sharedPref.getBoolean(DEBUG_MODE, false)
    }

    fun getLoginOtpMode() : Boolean {
        return sharedPref.getBoolean(LOGIN_OTP_MODE, false)
    }
}
