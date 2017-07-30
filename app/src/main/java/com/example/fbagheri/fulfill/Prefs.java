package com.example.fbagheri.fulfill;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;

/**
 * Created by F.Bagheri on 23/01/2017.
 */
public class Prefs extends PreferenceActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);





      /*  int id = prefs.getInt("id",0);;
        int happiness = prefs.getInt("happiness",5);

        int satisfaction = prefs.getInt("satisfaction",30);
        int  health = prefs.getInt("health",30);
        int  Experience = prefs.getInt("experience" ,20) ;
        int heart = prefs.getInt("heart" ,0);
        int coin = prefs.getInt("coin",0);
        int status =prefs.getInt("status" ,0);
        int age = prefs.getInt("age" ,0);
        String name = prefs.getString("name","star");*/

       // editor.commit();
    }
}
