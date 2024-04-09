package ro.pub.cs.systems.eim.practicaltest01var03;

import android.os.Bundle;
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

                int number1, number2;
                try {
                    number1 = Integer.parseInt(number1Text);
                    number2 = Integer.parseInt(number2Text);
                } catch (NumberFormatException e) {
                    Toast.makeText(PracticalTest01Var03MainActivity.this, "Please enter valid integers", Toast.LENGTH_SHORT).show();
                    return;
                }

                int result;
                String operation;
                if (v.getId() == R.id.addButton) {
                    result = number1 + number2;
                    operation = "+";
                } else {
                    result = number1 - number2;
                    operation = "-";
                }

                resultLabel.setText(number1 + " " + operation + " " + number2 + " = " + result);
            }
        };

        addButton.setOnClickListener(buttonClickListener);
        subtractButton.setOnClickListener(buttonClickListener);
    }
}