package ro.pub.cs.systems.eim.practicaltest01var03;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import androidx.annotation.Nullable;

// PracticalTest01Var03Service.java
public class PracticalTest01Var03Service extends Service {
    private ProcessingThread processingThread = null;

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        int number1 = intent.getIntExtra("number1", -1);
        int number2 = intent.getIntExtra("number2", -1);
        processingThread = new ProcessingThread(this, number1, number2);
        processingThread.start();
        return Service.START_REDELIVER_INTENT;
    }

    @Override
    public void onDestroy() {
        if (processingThread != null) {
            processingThread.interrupt();
            processingThread = null;
        }
        super.onDestroy();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}