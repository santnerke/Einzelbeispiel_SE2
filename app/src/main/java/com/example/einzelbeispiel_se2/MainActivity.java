package com.example.einzelbeispiel_se2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

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

    public void updateTextField(String result) {
        tvResponse.setText(result);
    }
}