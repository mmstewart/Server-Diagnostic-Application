package com.example.administrator.serverapp;

import java.io.IOException;
import okhttp3.Credentials;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class OkHttp {

    public String getURL(String url, String username, String password, String token) throws IOException {
        Request request = new Request.Builder()
                .url("https://" + url)
                .header("Authorization", Credentials.basic(username, password))
                .header("Authorization", token)
                .build();

        OkHttpClient client = new OkHttpClient();
        Response response = client.newCall(request).execute();
        try {
            if (!response.isSuccessful()) throw new IOException("Unexpected code: " + response);
            return response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response.body().string();
    }
}
