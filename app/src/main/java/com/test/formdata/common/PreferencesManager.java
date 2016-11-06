package com.test.formdata.common;


import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import com.test.formdata.R;


public class PreferencesManager {
	private static String PREF_FILE_NAME;
	
	
	private static PreferencesManager sInstance;
	private SharedPreferences mPreferences;
	private Editor mEditor;
	private static Context mCtx;
	
	private static class Keys {
 		static final String ISLOGGED_IN = "is_logged_in";
	 }
	
	public static PreferencesManager instance(Context ctx) {
		if (sInstance == null){
			PREF_FILE_NAME = ctx.getResources().getString(R.string.app_name);
			sInstance = new PreferencesManager(ctx);
		}

		mCtx=ctx;
		return sInstance;
	}

	public PreferencesManager(Context ctx) {
		mPreferences = ctx.getSharedPreferences(PREF_FILE_NAME,	Context.MODE_PRIVATE);
		mEditor = mPreferences.edit();
		mCtx=ctx;
	}


	
	public boolean isLoggedIn(){
		return mPreferences.getBoolean(Keys.ISLOGGED_IN , false);
	}
	
	public void setLoggedIn(Boolean isLoggedIn){
		mEditor.putBoolean(Keys.ISLOGGED_IN, isLoggedIn).commit();
	}


}
