package zfani.assaf.services;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    //BoundService class Object
    private BoundService boundService;

    //boolean variable to keep a check on service bind and unbind event
    private boolean isBound = false;

    private final ServiceConnection boundServiceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            BoundService.ServiceBinder binderBridge = (BoundService.ServiceBinder) service;
            boundService = binderBridge.getService();
            isBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            isBound = false;
            boundService = null;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.btnStartBackground).setOnClickListener(this);
        findViewById(R.id.btnStopBackground).setOnClickListener(this);
        findViewById(R.id.btnStartForeground).setOnClickListener(this);
        findViewById(R.id.btnStopForeground).setOnClickListener(this);
        findViewById(R.id.btnStartBound).setOnClickListener(this);
        findViewById(R.id.btnStopBound).setOnClickListener(this);
        findViewById(R.id.btnStartIntent).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnStartBackground:
                startService(new Intent(this, BackgroundService.class));
                break;
            case R.id.btnStopBackground:
                stopService(new Intent(this, BackgroundService.class));
                break;
            case R.id.btnStartForeground:
                ContextCompat.startForegroundService(this, new Intent(this, ForegroundService.class).putExtra("inputExtra", "Foreground Service Example in Android"));
                break;
            case R.id.btnStopForeground:
                stopService(new Intent(this, ForegroundService.class));
                break;
            case R.id.btnStartBound:
                Intent intent = new Intent(this, BoundService.class);
                startService(intent);
                bindService(intent, boundServiceConnection, BIND_AUTO_CREATE);
                //The Toast gets displayed after a delay of 3 seconds.
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(MainActivity.this, String.valueOf(boundService.randomGenerator()), Toast.LENGTH_SHORT).show();
                    }
                }, 3000);
                break;
            case R.id.btnStopBound:
                if (isBound) {
                    unbindService(boundServiceConnection);
                    isBound = false;
                }
                break;
            case R.id.btnStartIntent:
                startService(new Intent(this, IntentService.class));
                break;
        }
    }
}