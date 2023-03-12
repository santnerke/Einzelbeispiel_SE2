package com.example.einzelbeispiel_se2;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class TcpConnection extends AsyncTask<String, Void, String> {
    private static final String SERVER_IP_ADDRESS = "se2-isys.aau.at";
    private static final int SERVER_PORT = 53212;
    private MainActivity activity;

    public TcpConnection(MainActivity activity) {
        this.activity = activity;
    }

    @Override
    protected String doInBackground(String... params) {
        String response = "";
        try {
            Socket socket = new Socket(SERVER_IP_ADDRESS, SERVER_PORT);

            OutputStream outputStream = socket.getOutputStream();
            PrintWriter writer = new PrintWriter(outputStream, true);

            String message = params[0];
            writer.println(message);

            InputStream inputStream = socket.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

            response = reader.readLine();

            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
        activity.updateTextField(result);
    }
}
