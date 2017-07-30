package com.myown.spotat

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import com.myown.spotat.utils.AppGlobals
import com.myown.spotat.utils.LogClass

class SplashScreen : Activity() {

    val TAG : String = "SplashScreen"

    lateinit var context : Context
    lateinit var appGlobals : AppGlobals

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash_screen)

        initializeWidgets()
    }

    fun initializeWidgets() {
        context = applicationContext
        appGlobals = AppGlobals.getInstance(context)

        setLog("Reached initializeWidgets", LogClass.DEBUG_MSG)

        appGlobals.sharedPref.setDebugMode(true)

        setLog("Completed initializeWidgets", LogClass.DEBUG_MSG)

        loadNextActivity()
    }

    fun loadNextActivity() {
        setLog("Reached loadNextActivity", LogClass.DEBUG_MSG)
        this.finish()

        var activityIntent : Intent

        if(appGlobals.sharedPref.getLoggedIn())
            activityIntent = Intent(context, MainActivity::class.java)
        else
            activityIntent = Intent(context, LoginActivity::class.java)

        startActivity(activityIntent)
    }

    fun setLog(msg : String, msgType : Int) {
        appGlobals.logClass.setLogMsg(TAG, msg, msgType)
    }
}
