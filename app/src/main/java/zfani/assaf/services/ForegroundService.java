package zfani.assaf.services;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;

/*A foreground service is a service that should have the same priority as an active activity and therefore
should not be killed by the Android system, even if the system is low on memory.
A foreground service must provide a notification for the status bar, which is placed under the "Ongoing" heading,
which means that the notification cannot be dismissed unless the service is either stopped or removed from the foreground.*/

public class ForegroundService extends Service {

    private static final String CHANNEL_ID = "ForegroundServiceChannel";

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        Toast.makeText(this, "Foreground Service was Created", Toast.LENGTH_SHORT).show();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        String input = intent.getStringExtra("inputExtra");
        createNotificationChannel();
        Notification notification = getNotification(input);
        startForeground(1, notification);
        Toast.makeText(this, "Foreground Service Started", Toast.LENGTH_SHORT).show();

        /*Service is not restarted. Used for services which are periodically triggered anyway.
        The service is only restarted if the runtime has pending startService() calls since the service termination.*/
        return START_NOT_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Toast.makeText(this, "Foreground Service Stopped", Toast.LENGTH_SHORT).show();
    }

    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel serviceChannel = new NotificationChannel(CHANNEL_ID, "Foreground Service Channel", NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(serviceChannel);
        }
    }

    private Notification getNotification(String input) {
        Intent notificationIntent = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, notificationIntent, 0);

        return new NotificationCompat.Builder(this, CHANNEL_ID)
                .setContentTitle("Foreground Service")
                .setContentText(input)
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setContentIntent(pendingIntent)
                .build();
    }
}