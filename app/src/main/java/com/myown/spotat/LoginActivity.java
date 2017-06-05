package com.myown.spotat;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.hbb20.CountryCodePicker;
import com.myown.spotat.utils.AppGlobals;
import com.myown.spotat.utils.LogClass;

/**
 * Created by Admin on 6/2/2017.
 */
public class LoginActivity extends Activity {

    private final String TAG = "LoginActivity";

    Context context = null;
    AppGlobals appGlobals = null;

    CountryCodePicker countryCodeUI = null;
    EditText mobileUI = null;
    Button loginUI = null;
    View.OnClickListener clickListener = null;

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
        loginUI = (Button) findViewById(R.id.login_btn);
    }

    private void setClickListener() {
        clickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.login_btn:
                        Log.d(TAG, "Reached 1");
                        if(appGlobals.connectionDetector.isConnectingToInternet()) {
                            appGlobals.logClass.setLogMsg(TAG, "Reached 2", LogClass.DEBUG_MSG);
                            String countryCode = countryCodeUI.getSelectedCountryCode();
                            String mobile = mobileUI.getText().toString();
                            appGlobals.toastMsg(context, countryCode + "-" + mobile, Toast.LENGTH_LONG);
                        } else {
                            appGlobals.logClass.setLogMsg(TAG, "Reached 3", LogClass.DEBUG_MSG);
                            appGlobals.toastMsg(context, getString(R.string.no_network), Toast.LENGTH_LONG);
                        }

                        break;
                }
            }
        };
        loginUI.setOnClickListener(clickListener);
    }
}
