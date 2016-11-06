package com.test.formdata;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;

import com.test.formdata.common.PreferencesManager;

import utils.CrossFadeUtils;


public class OTPActivity extends Activity {
Activity mActivity;
    Context  mCtx;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity = this;
        mCtx= this;
        setContentView(R.layout.activity_otp);
        findViewById(R.id.confirm).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PreferencesManager preferencesManager = PreferencesManager.instance(mCtx);
                preferencesManager.setLoggedIn(true);
                CrossFadeUtils.crossFadeAnimation(mActivity, MainActivity.class, null, true, true);
            }
        });
    }


}
