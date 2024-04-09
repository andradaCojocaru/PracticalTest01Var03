package ro.pub.cs.systems.eim.practicaltest01var03;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class PracticalTest01Var03MainActivity extends AppCompatActivity {
    private IntentFilter intentFilter = new IntentFilter();
    private Intent serviceIntent = null;
    private PracticalTest01Var03BroadcastReceiver messageBroadcastReceiver;
    private TextView messageTextView;
    private int number1;
    private int number2;
    private String operation;
    int result;


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                Toast.makeText(this, "Correct", Toast.LENGTH_SHORT).show();
            } else if (resultCode == RESULT_CANCELED) {
                Toast.makeText(this, "Incorrect", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onRestart() {
        super.onRestart();
        Log.d("practical", "onRestart() method was invoked");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d("practical", "onStart() method was invoked");
    }

    @Override
    public void onResume() {
        super.onResume();
        registerReceiver(messageBroadcastReceiver, intentFilter);
        Log.d("practical", "onResume() method was invoked");
    }

    @Override
    public void onPause() {
        super.onPause();
        unregisterReceiver(messageBroadcastReceiver);
        Log.d("practical", "onPause() method was invoked");

    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d("practical", "onStop() method was invoked");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("practical", "onDestroy() method was invoked");
        if (serviceIntent != null) {
            stopService(serviceIntent);
            serviceIntent = null;
        }
    }


    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        EditText number1EditText = findViewById(R.id.number1);
        EditText number2EditText = findViewById(R.id.number2);
        savedInstanceState.putString("number1", number1EditText.getText().toString());
        savedInstanceState.putString("number2", number2EditText.getText().toString());
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        EditText number1EditText = findViewById(R.id.number1);
        EditText number2EditText = findViewById(R.id.number2);
        String number1 = savedInstanceState.getString("number1");
        String number2 = savedInstanceState.getString("number2");
        number1EditText.setText(number1);
        number2EditText.setText(number2);
        Toast.makeText(this, "Restored values: " + number1 + ", " + number2, Toast.LENGTH_SHORT).show();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_practical_tes01_var03_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        EditText number1EditText = findViewById(R.id.number1);
        EditText number2EditText = findViewById(R.id.number2);
        TextView resultLabel = findViewById(R.id.resultLabel);
        Button addButton = findViewById(R.id.addButton);
        Button subtractButton = findViewById(R.id.subtractButton);

        View.OnClickListener buttonClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String number1Text = number1EditText.getText().toString();
                String number2Text = number2EditText.getText().toString();

                try {
                    number1 = Integer.parseInt(number1Text);
                    number2 = Integer.parseInt(number2Text);
                } catch (NumberFormatException e) {
                    Toast.makeText(PracticalTest01Var03MainActivity.this, "Please enter valid integers", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (v.getId() == R.id.addButton) {
                    result = number1 + number2;
                    operation = "+";
                } else {
                    result = number1 - number2;
                    operation = "-";
                }

                resultLabel.setText(number1 + " " + operation + " " + number2 + " = " + result);
                Intent intent = new Intent(getApplicationContext(), PracticalTest01Var03Service.class);
                intent.putExtra("number1", number1);
                intent.putExtra("number2", number2);
                startService(intent);
            }

        };

        addButton.setOnClickListener(buttonClickListener);
        subtractButton.setOnClickListener(buttonClickListener);
        Button switchActivityButton = findViewById(R.id.switchActivityButton);
        switchActivityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PracticalTest01Var03MainActivity.this, PracticalTest01Var03SecondaryActivity.class);
                intent.putExtra("result", number1 + " " + operation + " " + number2 + " = " + result);
                startService(intent);
            }
        });
        messageTextView = (TextView)findViewById(R.id.message_text_view);
        messageTextView.setMovementMethod(new ScrollingMovementMethod());

        messageBroadcastReceiver = new PracticalTest01Var03BroadcastReceiver(messageTextView);

        for (int index = 0; index < 2; index++) {
            intentFilter.addAction(Constant.actions[index]);
        }

    }
}