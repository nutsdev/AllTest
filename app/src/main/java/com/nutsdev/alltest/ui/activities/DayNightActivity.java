package com.nutsdev.alltest.ui.activities;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AppCompatDelegate;

import com.nutsdev.alltest.R;

import org.androidannotations.annotations.EActivity;

@EActivity(R.layout.activity_day_night)
public class DayNightActivity extends BaseActivity {

    // use for the whole app
 /*   static {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_AUTO);
    } */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState == null) {
            // Set the local night mode to some value
            getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_AUTO);
            // Now recreate for it to take effect
            recreate();
        }

        int currentNightMode = getResources().getConfiguration().uiMode & Configuration.UI_MODE_NIGHT_MASK;

        switch (currentNightMode) {
            case Configuration.UI_MODE_NIGHT_NO:
                // Night mode is not active, we're in day time
                break;
            case Configuration.UI_MODE_NIGHT_YES:
                // Night mode is active, we're at night!
                break;
            case Configuration.UI_MODE_NIGHT_UNDEFINED:
                // We don't know what mode we're in, assume notnight
                break;
        }
    }

}
