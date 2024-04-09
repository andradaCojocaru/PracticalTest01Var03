package ro.pub.cs.systems.eim.practicaltest01var03;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.TextView;

// PracticalTest01Var03BroadcastReceiver.java
public class PracticalTest01Var03BroadcastReceiver extends BroadcastReceiver {
    private TextView messageTextView;

    public PracticalTest01Var03BroadcastReceiver(TextView messageTextView) {
        this.messageTextView = messageTextView;
    }
    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        int result_sum = intent.getIntExtra("result_sum", -1);
        int result_dif = intent.getIntExtra("result_dif", -1);
        if (messageTextView != null) {
            messageTextView.setText(messageTextView.getText().toString() + "\n" + "resultsum" + result_sum + "resultdif:" + result_dif);
        }
        Log.d(" am trimis mesajul - > PracticalTest01Var03", action + ": " + messageTextView.getText().toString());
    }
}
