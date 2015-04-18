package bzzzzz.buzzfeed.team3.bzzzzz;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.ColorDrawable;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;


public class AlarmScreen extends Activity {
    Ringtone ringtone;

    //
    final AnimationDrawable drawable = new AnimationDrawable();
    LinearLayout layout;

    @Override
    public void onStart(){
        super.onStart();

        if (getIntent() != null) {
            Log.d("activity", ""+getIntent().getBooleanExtra("key", false));
            if(getIntent().getBooleanExtra("key", false)) {

            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm_screen);

        //custom font
        SpannableString s = new SpannableString(" BzZzZz");
        s.setSpan(new TypefaceSpan(this, "ProximaNova-Semibold.otf"), 0, s.length(),
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        // Update the action bar title with the TypefaceSpan instance
        ActionBar actionBar = getActionBar();
        actionBar.setTitle(s);

        ImageButton buzzButton = (ImageButton) findViewById(R.id.buzzButton);
        TextView time = (TextView) findViewById(R.id.timeDisplay);
        TextView wakeup = (TextView) findViewById(R.id.tv_getup);

        //setfont
        Typeface tf = Typeface.createFromAsset(getAssets(),"fonts/ProximaNova-Semibold.otf");
        time.setTypeface(tf);
        wakeup.setTypeface(tf);


        DateFormat formatter = new SimpleDateFormat("hh:mm a");
        time.setText(formatter.format(System.currentTimeMillis()));
        Uri alarmUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
        if (alarmUri == null){
            alarmUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        }
        ringtone = RingtoneManager.getRingtone(getApplicationContext(), alarmUri);
        ringtone.play();


        //set frames
        drawable.addFrame(new ColorDrawable(Color.RED), 100);
        drawable.addFrame(new ColorDrawable(Color.BLUE), 100);
        drawable.addFrame(new ColorDrawable(Color.YELLOW), 100);
        drawable.addFrame(new ColorDrawable(Color.CYAN), 100);
        drawable.addFrame(new ColorDrawable(Color.GREEN), 100);
        drawable.setOneShot(false);

        layout  = (LinearLayout) findViewById(R.id.alarmscreenbg);
        layout.setBackgroundDrawable(drawable);
        drawable.start();

        buzzButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ringtone.stop();
                AlarmManager am = (AlarmManager) getSystemService(AlarmClockActivity.instance().ALARM_SERVICE);
                Intent i = new Intent(AlarmClockActivity.instance(), AlarmHelper.class);
                PendingIntent pendingIntent = PendingIntent.getBroadcast(AlarmClockActivity.instance(), 0, i, 0);
                am.cancel(pendingIntent);
                Intent intent = new Intent(AlarmScreen.this, MainActivity.class);
                Log.d("activity", "intent Made");
                startActivity(intent);
                Log.d("activity", "created");
            }
        });
    }

    @Override
    protected void onStop() {
        super.onPause();
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_alarm_screen, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}
