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
        toolBar = findViewById(R.id.tool_bar) as Toolbar
        setSupportActionBar(toolBar)

        context = applicationContext
        appGlobals = AppGlobals.getInstance(context)
        debugMode = appGlobals.debugMode?:false

        appGlobals.customToast(context, "Debug " + debugMode, Toast.LENGTH_LONG)
        appGlobals.logClass.setLogMsg(TAG, "Reached initializeWidgets", LogClass.DEBUG_MSG)
    }

    fun setClickListener() {

    }
}
