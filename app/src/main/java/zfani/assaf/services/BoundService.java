package zfani.assaf.services;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.widget.Toast;

import java.util.Random;

/*Bound Services is a great way to perform a long running work while you make a smooth responsive UI.
When it comes to Bound Services, you can bind this working component with the activity and then dispatch the results
on to foreground (The Ui). This also refer to the client-server interface.*/

public class BoundService extends Service {

    public BoundService() {
    }

    //Instance of inner class created to provide access to public methods in this class
    private final IBinder localBinder = new ServiceBinder();

    @Override
    public IBinder onBind(Intent intent) {
        Toast.makeText(this, "Service Bounded", Toast.LENGTH_SHORT).show();
        return localBinder;
    }

    /*
     * This method is  Called when activity have disconnected from a particular interface published by the service.
     * Note: Default implementation of the  method just  return false
     */
    @Override
    public boolean onUnbind(Intent intent) {
        Toast.makeText(this, "Service Unbounded", Toast.LENGTH_SHORT).show();
        return super.onUnbind(intent);
    }

    /**
     * Called when an activity is connected to the service, after it had
     * previously been notified that all had disconnected in its onUnbind method.
     * This will only be called by system if the implementation of onUnbind method was overridden to return true.
     */
    @Override
    public void onRebind(Intent intent) {
        super.onRebind(intent);
    }

    /**
     * Called by the system to notify a Service that it is no longer used and is being removed.
     * The service should clean up any resources it holds (threads, registered, receivers, etc) at this point.
     * Upon return, there will be no more calls in to this Service object and it is effectively dead.
     */
    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    /* This method creates a random number and return it*/

    public int randomGenerator() {
        return new Random().nextInt();
    }

    public class ServiceBinder extends Binder {

        public BoundService getService() {
            return BoundService.this;
        }
    }
}
