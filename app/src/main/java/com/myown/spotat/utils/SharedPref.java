package com.myown.spotat.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.myown.spotat.R;

public class SharedPref {

    SharedPreferences pref;
    SharedPreferences.Editor editor;

    private final String DEBUG_MODE = "debugMode";
    private final String LOGIN_OTP_MODE = "loginOtpMode";

    public SharedPref(Context context) {
        pref = context.getSharedPreferences(context.getString(R.string.app_name), context.MODE_PRIVATE);
        editor= pref.edit();
    }

    public void clearAllPref() {
        editor.clear();
        editor.commit();
    }

    public void setDebugMode(boolean mode) {
        editor.putBoolean(DEBUG_MODE, mode);
        editor.commit();
    }

    public void setLoginOtpMode(boolean loginOtp) {
        editor.putBoolean(LOGIN_OTP_MODE, loginOtp);
        editor.commit();
    }

    public boolean getDebugMode() {
        return pref.getBoolean(LOGIN_OTP_MODE, false);
    }

    public boolean getLoginOtpMode() {
        return pref.getBoolean(LOGIN_OTP_MODE, false);
    }
}
