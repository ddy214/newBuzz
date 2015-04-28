package bzzzzz.buzzfeed.team3.bzzzzz;

/**
 * Created by derekyu on 4/18/15.
 */

import android.media.RingtoneManager;
import android.net.Uri;

/**
 * Created by derekyu on 4/18/15.
 */
public class AlarmClock {
    public int hour;
    public int minute;
    public boolean set;
    public Uri alarmtone = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
    void setHour(int hour){
        this.hour = hour;
    }

    void setMinute(int minute){
        this.minute = minute;
    }

    void setRingtone(Uri ringtone){
        this.alarmtone = ringtone;
    }
}
