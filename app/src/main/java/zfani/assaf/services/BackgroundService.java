package zfani.assaf.services;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.provider.Settings;
import android.widget.Toast;

/*A service is a component which runs in the background without direct interaction with the user.
As the service has no user interface, it is not bound to the lifecycle of an activity.
Services are used for repetitive and potentially long running operations, i.e., Internet downloads, checking for new data,
data processing, updating content providers and the like.*/

public class BackgroundService extends Service {

    private MediaPlayer player;

    @Override
    public IBinder onBind(Intent intent) {
        return  null;
    }

    @Override
    public void onCreate() {
        Toast.makeText(this, "Background Service was Created", Toast.LENGTH_SHORT).show();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        player = MediaPlayer.create(this, Settings.System.DEFAULT_RINGTONE_URI);
        // This will play the ringtone continuously until we stop the service.
        player.setLooping(true);
        // It will start the player
        player.start();
        Toast.makeText(this, "Background Service Started", Toast.LENGTH_SHORT).show();

        /*Service is restarted if it gets terminated. Intent data passed to the onStartCommand method is null.
        Used for services which manages their own state and do not depend on the Intent data.*/
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        // Stopping the player when service is destroyed
        player.stop();
        Toast.makeText(this, "Background Service Stopped", Toast.LENGTH_SHORT).show();
    }
}
