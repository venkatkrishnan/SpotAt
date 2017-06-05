package com.myown.spotat.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by Admin on 6/2/2017.
 */
public class AppGlobals {

    public static AppGlobals appGlobals;
    public LogClass logClass = null;
    public SharedPref sharedPref = null;
    public static Context appContext = null;
    public ConnectionDetector connectionDetector = null;
    public boolean debugMode = false;

    private static final String TAG = "AppGlobals";

    public static AppGlobals getInstance(Context context) {
        if(appGlobals == null) {
            appGlobals = new AppGlobals();
            appGlobals.init(context);
        }
        if(appContext == null)
            appContext = context;
        return appGlobals;
    }

    public boolean init(Context context) {
        try {
            appContext = context;
            logClass = new LogClass();
            sharedPref = new SharedPref(context);
            connectionDetector = new ConnectionDetector(context);

            debugMode = sharedPref.getDebugMode();

            /*sqLiteDb = DBHelper.getInstance(context);
            if(sqLiteDb != null) {
                if (!sqLiteDb.init(context, appGlobals)) {
                    if(debugMode)
                        appGlobals.logClass.setLogMsg(TAG, "Error in DB Initialization", LogClass.ERROR_MSG);
                    return false;
                }
            }*/
        } catch(Exception e) {
            if(debugMode)
                appGlobals.logClass.setLogMsg(TAG, e.toString(), LogClass.ERROR_MSG);
            return false;
        }
        return true;
    }

    public void toastMsg(Context context, String toastMsg, int duration) {
        Toast.makeText(context, toastMsg, duration).show();
    }
}
