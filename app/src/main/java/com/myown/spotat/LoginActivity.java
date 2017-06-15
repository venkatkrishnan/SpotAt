package com.myown.spotat;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.hbb20.CountryCodePicker;
import com.myown.spotat.utils.AppGlobals;

/**
 * Created by Admin on 6/2/2017.
 */
public class LoginActivity extends Activity {

    private final String TAG = "LoginActivity";

    Context context = null;
    AppGlobals appGlobals = null;
    boolean loginOtpMode = false;
    View.OnClickListener clickListener = null;

    CountryCodePicker countryCodeUI = null;
    Button loginUI = null, continueUI = null;
    RelativeLayout loginLayoutUI = null, otpLayoutUI = null;
    EditText mobileUI = null, otp1UI = null, otp2UI = null, otp3UI = null, otp4UI = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);

        initializeWidgets();
        setClickListener();
    }

    private void initializeWidgets() {
        context = getApplicationContext();
        appGlobals = AppGlobals.getInstance(context);

        countryCodeUI = (CountryCodePicker) findViewById(R.id.country_code);
        mobileUI = (EditText) findViewById(R.id.mobile);
        otp1UI = (EditText) findViewById(R.id.otp1);
        otp2UI = (EditText) findViewById(R.id.otp2);
        otp3UI = (EditText) findViewById(R.id.otp3);
        otp4UI = (EditText) findViewById(R.id.otp4);

        loginUI = (Button) findViewById(R.id.login_btn);
        continueUI = (Button) findViewById(R.id.continue_btn);

        loginLayoutUI = (RelativeLayout) findViewById(R.id.login_layout);
        otpLayoutUI = (RelativeLayout) findViewById(R.id.otp_layout);
    }

    @Override
    protected void onResume() {
        super.onResume();

        reloadLayout();
    }

    private void setClickListener() {
        clickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.login_btn:
                        if(appGlobals.checkNetworkConnection(context)) {
                            if(!loginOtpMode) {
                                login();
                            } else {
                                otp();
                            }
                        }
                        break;
                }
            }
        };
        loginUI.setOnClickListener(clickListener);
    }

    private void login() {
        String countryCode = countryCodeUI.getSelectedCountryCode();
        String mobile = mobileUI.getText().toString();

        if(TextUtils.isEmpty(countryCode)) {
            appGlobals.customToast(context, getString(R.string.invalid_country_code), Toast.LENGTH_LONG);
            return;
        } else if(TextUtils.isEmpty(mobile)) {
            appGlobals.customToast(context, countryCode + "-" + mobile, Toast.LENGTH_LONG);
            return;
        }

        appGlobals.customToast(context, countryCode + "-" + mobile, Toast.LENGTH_LONG);
//        appGlobals.sharedPref.setLoginOtpMode(true);
        reloadLayout();
    }

    private void otp() {

        String otp1 = otp1UI.getText().toString();
        String otp2 = otp2UI.getText().toString();
        String otp3 = otp3UI.getText().toString();
        String otp4 = otp4UI.getText().toString();

        if(TextUtils.isEmpty(otp1) || TextUtils.isEmpty(otp2) || TextUtils.isEmpty(otp3) ||
                TextUtils.isEmpty(otp4)) {
            appGlobals.customToast(context, getString(R.string.invalid_otp), Toast.LENGTH_LONG);
            return;
        }
    }

    private void reloadLayout() {
        loginOtpMode = appGlobals.sharedPref.getLoginOtpMode();

        if(!loginOtpMode) {
            loginLayoutUI.setVisibility(View.VISIBLE);
            otpLayoutUI.setVisibility(View.GONE);
        } else {
            loginLayoutUI.setVisibility(View.GONE);
            otpLayoutUI.setVisibility(View.VISIBLE);
        }
    }
}
