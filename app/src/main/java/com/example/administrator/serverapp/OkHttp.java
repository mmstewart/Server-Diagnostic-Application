package com.example.administrator.serverapp;

import java.io.IOException;
import java.net.ConnectException;
import java.net.SocketTimeoutException;
import okhttp3.Call;
import okhttp3.Credentials;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class OkHttp {

    public String getURL(String url, String username, String password, String token) throws IOException {

        String result;
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://" + url)
                .header("Authorization", Credentials.basic(username, password))
                .header("Authorization", token)
                .build();

        Call call = client.newCall(request);
        try {
            Response response = call.execute();
            result = response.body().string();
        } catch (ConnectException | SocketTimeoutException e) {
            return "Failed to connect to " + url;
        }
        return result;
    }
}
