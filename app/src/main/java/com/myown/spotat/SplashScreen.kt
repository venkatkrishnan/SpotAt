package com.myown.spotat

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.myown.spotat.utils.AppGlobals

/**
 * Created by Admin on 7/30/2017.
 */

class SplashScreen : Activity() {

    val TAG : String = "SplashScreen";

    var context : Context? = null;
    var appGlobals : AppGlobals? = null;

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);

        initializeWidgets();
    }

    fun initializeWidgets() {
        context = applicationContext
        appGlobals = AppGlobals.getInstance(context)

        appGlobals?.sharedPref?.debugMode = true

        val activityIntent = Intent(context, LoginActivity::class.java)
        startActivity(activityIntent)
    }
}
