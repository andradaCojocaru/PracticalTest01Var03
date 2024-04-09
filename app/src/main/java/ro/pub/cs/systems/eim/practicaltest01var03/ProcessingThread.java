package ro.pub.cs.systems.eim.practicaltest01var03;

import android.content.Context;
import android.content.Intent;

// ProcessingThread.java
// ProcessingThread.java
public class ProcessingThread extends Thread {
    private Context context;
    private boolean isRunning = true;
    private int number1;
    private int number2;

    public ProcessingThread(Context context, int number1, int number2) {
        this.context = context;
        this.number1 = number1;
        this.number2 = number2;
    }

    @Override
    public void run() {
        while (isRunning) {
            sendMessage();
            sleep();
        }
    }

    private void sendMessage() {
        Intent intent = new Intent();
        intent.setAction("ro.pub.cs.systems.eim.practicaltest01var03.sum");
        intent.putExtra("result_sum", number1 + number2);
        context.sendBroadcast(intent);

        intent.setAction("ro.pub.cs.systems.eim.practicaltest01var03.difference");
        intent.putExtra("result_dif", number1 - number2);
        context.sendBroadcast(intent);
    }

    private void sleep() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void stopThread() {
        isRunning = false;
    }
}
