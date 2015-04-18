package bzzzzz.buzzfeed.team3.bzzzzz;

import android.app.Activity;
import android.content.ClipData;
import android.content.Intent;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

import java.util.ArrayList;
import java.util.List;


public class PickPrefActivity extends Activity {
    public String pref;
    static public ArrayList<CheckBox> listBox = new ArrayList<CheckBox>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pick_pref);


        final List<String> listStrings = new ArrayList<String> (){
            {add( new String("article"));
                add( new String("video"));
                add( new String("list"));
                add( new String("quiz"));}
        };

//        Button submit = (Button) findViewById(R.id.Done);
//        submit.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                PreferenceManager.getDefaultSharedPreferences(PickPrefActivity.this).edit().putString(LauncherActivity.PREFERENCE, pref).commit();
//                Log.d("activity", pref);
//                Intent intent = new Intent(PickPrefActivity.this, AlarmClockActivity.class);
//                startActivity(intent);
//            }
//        });

        CheckBox article = (CheckBox) findViewById(R.id.checkArticle);
        CheckBox video = (CheckBox) findViewById(R.id.checkVideo);
        CheckBox list = (CheckBox) findViewById(R.id.checkList);
        CheckBox quiz = (CheckBox) findViewById(R.id.checkQuiz);
        listBox.add(article);
        listBox.add(video);
        listBox.add(list);
        listBox.add(quiz);
        for (final CheckBox check : listBox) {
            check.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ((CheckBox) v).setChecked(true);

                    pref = listStrings.get(listBox.indexOf(check));

                    Log.d("activity", pref);
                    for(CheckBox i: listBox){
                        if(i != (CheckBox) v){
                            i.setChecked(false);

                        }
                    }
                    PreferenceManager.getDefaultSharedPreferences(PickPrefActivity.this).edit().putString(LauncherActivity.PREFERENCE, pref).commit();
                    Log.d("activity", pref);
                    Intent intent = new Intent(PickPrefActivity.this, AlarmClockActivity.class);
                    startActivity(intent);
                }
            });

        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.action_settings:
                Intent intent = new Intent(this, PickPrefActivity.class);
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    @Override
    protected void onStop() {
        super.onPause();
        finish();
    }

}



