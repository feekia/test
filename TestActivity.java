package com.example.vivo.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.database.ContentObserver;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemProperties;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class TestActivity extends Activity {
    private String TAG = "TestActivity";
    private Button mShow = null;
    private Button mHide = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
    }

    private void initView() {
        mShow = (Button) findViewById(R.id.show);
        mShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "register");
                if (0 == SystemProperties.getInt("qemu.hw.mainkeys", 0))
                Settings.System.putInt(getApplicationContext().getContentResolver(), "navigationbar_display", 1);
                if (0 == SystemProperties.getInt("qemu.hw.statusbar", 0)){
                    Settings.System.putInt(getApplicationContext().getContentResolver(), "statusbar_display", 1);
                }
            }
        });
        mHide = (Button) findViewById(R.id.hide);
        mHide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "unregister");
                if (1 == SystemProperties.getInt("qemu.hw.mainkeys", 0))
                    Settings.System.putInt(getApplicationContext().getContentResolver(), "navigationbar_display", 0);
                if (1 == SystemProperties.getInt("qemu.hw.statusbar", 0)){
                    Settings.System.putInt(getApplicationContext().getContentResolver(), "statusbar_display", 0);
                }
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}