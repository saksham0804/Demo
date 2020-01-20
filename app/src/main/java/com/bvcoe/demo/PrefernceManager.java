package com.bvcoe.demo;

import android.content.Context;
import android.content.SharedPreferences;

public class PrefernceManager {

    private Context mCtx;
    private SharedPreferences sharedPreferences;

    public PrefernceManager(Context mCtx) {
        this.mCtx = mCtx;
        getsharedPreference();
    }

    private void getsharedPreference(){
        sharedPreferences=mCtx.getSharedPreferences(mCtx.getString(R.string.my_preference),mCtx.MODE_PRIVATE);

    }
    public void writePrefernce(){
        SharedPreferences.Editor editor= sharedPreferences.edit();
        editor.putString(mCtx.getString(R.string.my_preference_key),"INIT_OK").apply();

    }
    public boolean checkPrefernce(){
        boolean status = false;

        if(sharedPreferences.getString(mCtx.getString(R.string.my_preference_key),"null").equals("null"))
            status=false;
        else
            status=true;

        return status;
    }
    public  void clearPrefernce(){
        sharedPreferences.edit().clear().apply();
    }
}
