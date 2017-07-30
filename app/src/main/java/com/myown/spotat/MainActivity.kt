package com.myown.spotat

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.widget.Toast
import com.myown.spotat.utils.AppGlobals
import com.myown.spotat.utils.LogClass

class MainActivity : AppCompatActivity() {

    val TAG : String = "MainActivity"

    lateinit var toolBar : Toolbar
    lateinit var context : Context
    lateinit var appGlobals : AppGlobals

    var debugMode : Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initializeWidgets()
        setClickListener()
    }

    fun initializeWidgets() {
        context = applicationContext
        appGlobals = AppGlobals.getInstance(context)

        setLog("Reached initializeWidgets", LogClass.DEBUG_MSG)

        toolBar = findViewById(R.id.tool_bar) as Toolbar
        setSupportActionBar(toolBar)

        debugMode = appGlobals.debugMode?:false

        appGlobals.customToast(context, "Debug " + debugMode, Toast.LENGTH_LONG)
        setLog("Completed initializeWidgets", LogClass.DEBUG_MSG)
    }

    fun setClickListener() {

    }

    fun setLog(msg : String, msgType : Int) {
        appGlobals.logClass.setLogMsg(TAG, msg, msgType)
    }
}
