package hitesh.asimplegame;

import android.app.Activity;
import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.util.Log;

import androidx.preference.PreferenceManager;

public class Bgm extends Service {
    public static final String MESSAGE_KEY = "";

    private static boolean isStarted = false;
    MediaPlayer mediaPlayer;

    public Bgm() {

    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        boolean isMessage = intent.getExtras().getBoolean(Bgm.MESSAGE_KEY);

        if (!isStarted && isMessage) { // not start and start bgm
            mediaPlayer = MediaPlayer.create(this, R.raw.default_bgm);
            mediaPlayer.start();

            isStarted = true;
        } else if (isStarted && isMessage) {
            isStarted = false;
            Log.d("TAG", "Called");
        } else {
            mediaPlayer.stop();
            mediaPlayer.release();
        }


//      if(isMessage) {
//            mediaPlayer = MediaPlayer.create(this, R.raw.default_bgm);
//            mediaPlayer.setLooping(true);
//            mediaPlayer.start();
//        } else {
//            mediaPlayer.stop();
//            mediaPlayer.release();
//        }
//
//
//        return START_NOT_STICKY;
    }


}
