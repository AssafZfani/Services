package zfani.assaf.services;

import android.content.Intent;
import android.widget.Toast;

import androidx.annotation.Nullable;

/*The IntentService is used to perform a certain task in the background.
Once done, the instance of IntentService terminates itself automatically.
An example for its usage would be downloading certain resources from the internet.*/

public class IntentService extends android.app.IntentService {

    public IntentService() {
        super("IntentService");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Toast.makeText(this, "Intent Service was Created", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        // Mocking an image file downloading process
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Toast.makeText(this, "Intent Service Stopped", Toast.LENGTH_SHORT).show();
    }
}
