package bzzzzz.buzzfeed.team3.bzzzzz;

import android.app.Activity;
import android.content.Intent;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;


public class LauncherActivity extends Activity {
    static public String PREFERENCE = "pref";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String tempPref = PreferenceManager.getDefaultSharedPreferences(this).getString(PREFERENCE, null);
        if(tempPref == null){
            Intent intent = new Intent(LauncherActivity.this, PickPrefActivity.class);
            startActivity(intent);
        }
        else{
            Intent intent = new Intent(LauncherActivity.this, AlarmClockActivity.class);
            startActivity(intent);
        }
    }



}
