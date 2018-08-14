package com.example.administrator.serverapp;

import java.io.IOException;
import java.net.ConnectException;
import java.net.SocketTimeoutException;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLHandshakeException;
import javax.net.ssl.SSLSession;

import okhttp3.Call;
import okhttp3.Credentials;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.internal.http2.StreamResetException;

public class OkHttp {

    public String getURL(String url, String username, String password, String token) throws IOException {

        String result;
        OkHttpClient client = new OkHttpClient();
        OkHttpClient.Builder builder = new OkHttpClient.Builder();

        builder.hostnameVerifier(new HostnameVerifier() {
            @Override
            public boolean verify(String s, SSLSession sslSession) {
                return true;
            }
        });
        client.hostnameVerifier();

        Request request = new Request.Builder()
                .url("https://" + url)
                .header("Authorization", Credentials.basic(username, password))
                .header("Authorization", token)
                .build();

        Call call = client.newCall(request);
        try {
            Response response = call.execute();
            result = response.body().string();
        } catch (ConnectException | SocketTimeoutException | SSLHandshakeException | StreamResetException e) {
            return "Failed to connect to https://" + url + ", try again.";
        }
        return result;
    }
}
