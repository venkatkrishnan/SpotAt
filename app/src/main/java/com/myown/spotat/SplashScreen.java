package com.myown.spotat;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.myown.spotat.utils.AppGlobals;
import com.myown.spotat.utils.LogClass;

/**
 * Created by Admin on 6/2/2017.
 */
public class SplashScreen extends Activity {

    private final String TAG = "SplashScreen";

    Context context = null;
    AppGlobals appGlobals = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);

        initializeWidgets();
    }

    private void initializeWidgets() {
        context = getApplicationContext();
        appGlobals = AppGlobals.getInstance(context);

        appGlobals.sharedPref.setDebugMode(true);

        Intent loginActivity = new Intent(SplashScreen.this, LoginActivity.class);
        startActivity(loginActivity);
        this.finish();
    }
}
