package com.example.administrator.serverapp;

import java.io.IOException;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class OkHttp {

    private OkHttpClient client;
    private Request request;

    public OkHttp() {
    }

    public String getURL(OkHttpClient client, Request request) throws IOException {
        Response response = client.newCall(request).execute();
        return response.body().string();
    }

    public OkHttpClient getClient() {
        return client = new OkHttpClient();
    }

    public Request getRequest(String url) {
        return request = new Request.Builder()
                .url("https://" + url)
                .build();
    }
}

