package bzzzzz.buzzfeed.team3.bzzzzz;

import android.app.AlarmManager;
import android.app.FragmentTransaction;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TimePicker;

import java.util.Calendar;


public class AlarmClockActivity extends ActionBarActivity {
    private static AlarmClockActivity inst;
    private AlarmClock alarm;

    @Override
    public void onStart(){
        super.onStart();
        inst = this;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm_clock);
        Button submit = (Button) findViewById(R.id.createtime);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alarm = new AlarmClock();
                TimePicker time = (TimePicker) findViewById(R.id.time);
                alarm.setHour(time.getCurrentHour());
                alarm.setMinute(time.getCurrentMinute());
                alarm.set = true;
                setAlarm();

            }
        });
    }

    private void setAlarm(){
        if(alarm != null){
            AlarmManager manager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.HOUR_OF_DAY, alarm.hour);
            calendar.set(Calendar.MINUTE, alarm.minute);
            Intent intent = new Intent(AlarmClockActivity.this, AlarmHelper.class);
            PendingIntent pending = PendingIntent.getBroadcast(AlarmClockActivity.this, 0, intent, 0);
            manager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pending);
            Log.d("Alarm", "has been set");
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_alarm_clock, menu);
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

    public static AlarmClockActivity instance(){
        return inst;
    }

}
