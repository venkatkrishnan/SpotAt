package com.myown.spotat.utils

import android.content.Context
import android.widget.Toast
import com.myown.spotat.R

class AppGlobals {


    lateinit var logClass : LogClass
    lateinit var sharedPref : SharedPref
    lateinit var connectionDetector : ConnectionDetector
    var debugMode : Boolean ?= false

    val TAG : String = "AppGlobals";

    companion object {
        var appGlobals : AppGlobals ?= null
        lateinit var appContext : Context

        fun getInstance(context: Context): AppGlobals {
            if (appGlobals == null) {
                appGlobals = AppGlobals();
                appGlobals?.init(context);
            }
            if (appContext == null)
                appContext = context;
            return appGlobals!!;
        }
    }

    fun init(context : Context) : Boolean {
        try {
            appContext = context;
            logClass = LogClass();
            sharedPref = SharedPref(context);
            connectionDetector = ConnectionDetector(context);

            debugMode = sharedPref.getDebugMode();

            /*sqLiteDb = DBHelper.getInstance(context);
            if(sqLiteDb != null) {
                if (!sqLiteDb.init(context, appGlobals)) {
                    if(debugMode)
                        appGlobals.logClass.setLogMsg(TAG, "Error in DB Initialization", LogClass.ERROR_MSG);
                    return false;
                }
            }*/
        } catch(e : Exception) {
            if(debugMode?:false)
                appGlobals?.logClass?.setLogMsg(TAG, e.toString(), LogClass.ERROR_MSG);
            return false;
        }
        return true;
    }

    fun customToast(context : Context, toastMsg : String, duration : Int) {
        Toast.makeText(context, toastMsg, duration).show();
    }

    fun checkNetworkConnection(context : Context) : Boolean {
        if(!appGlobals?.connectionDetector!!.isConnectingToInternet()) {
            appGlobals?.customToast(context, context.getString(R.string.no_network), Toast.LENGTH_LONG);
            return false;
        }
        return true;
    }
}