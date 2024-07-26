package com.example.unitconverter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {


    EditText inputValue;
    Button convertButton;
    TextView resultValue;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        inputValue = findViewById(R.id.editText);
        convertButton = findViewById(R.id.convertButton);
        resultValue = findViewById(R.id.textView);

        convertButton.setOnClickListener(v -> {
            String value = getInputValue();
            if (!value.isEmpty() &&  !value.contains(",")) {
                double result = convertToPounds(Double.parseDouble(value));
                value = String.valueOf(result);
            } else {
                value = "";
            }
            resultValue.setText(value);
            // close the keyword
            closeKeyboard();
        });


    }

    private void closeKeyboard() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    private String getInputValue() {
        String inputVal = inputValue.getText().toString();
        if (inputVal.isEmpty()) {
            Toast.makeText(this, "Please enter a number", Toast.LENGTH_SHORT).show();
        } else if (inputVal.contains(",")) {
            Toast.makeText(this, "Please enter a valid number", Toast.LENGTH_SHORT).show();
        } else if (Double.parseDouble(inputVal) < 0) {
            Toast.makeText(this, "Please enter a positive number", Toast.LENGTH_SHORT).show();
        }
        return inputVal;
    }

    public double convertToPounds(double valueKg) {
        return valueKg * 2.205;
    }
}