package com.example.einzelbeispiel_se2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText tfNumber;
    private TextView tvResponse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getContent();
    }

    public void getContent() {
        tfNumber = findViewById(R.id.tfNumber);
        tvResponse = findViewById(R.id.tvResponse);
    }

    public void onSubmitButtonClick(View view){
        String matrikelNummer = tfNumber.getText().toString();
        new TcpConnection(this).execute(matrikelNummer);
        tfNumber.clearFocus();
    }

    public void onCalculateButtonClick(View view){
        String matrikelNummer = tfNumber.getText().toString();
        if (matrikelNummer.length() < 8) {
            Toast.makeText(this, "Bitte geben Sie eine 8-stellige Matrikelnummer ein", Toast.LENGTH_SHORT).show();
        } else {
            updateTextField(convertToASCII(matrikelNummer));
        }
    }

    private String convertToASCII(String matrikel) {
        StringBuilder ascii = new StringBuilder();
        for (int i = 0; i < matrikel.length(); i++) {
            char c = matrikel.charAt(i);
            if (i % 2 == 0) {
                ascii.append(c);
            } else {
                int asciiValue = (int) c + 48;
                ascii.append((char) asciiValue);
            }
        }

        return ascii.toString();
    }

    public void updateTextField(String result) {
        tvResponse.setText(result);
    }
}