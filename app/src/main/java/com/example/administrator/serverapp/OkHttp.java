package com.example.administrator.serverapp;

import java.io.IOException;
import java.net.ConnectException;
import java.net.NoRouteToHostException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLHandshakeException;
import javax.net.ssl.SSLSession;

import okhttp3.Call;
import okhttp3.CertificatePinner;
import okhttp3.Credentials;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.internal.http2.StreamResetException;

public class OkHttp {

    public String getURL(String url, String username, String password, String token) throws IOException {

        String result;
        OkHttpClient client = new OkHttpClient();

        CertificatePinner cp = new CertificatePinner.Builder()
                .build();
        OkHttpClient b = client.newBuilder()
                .certificatePinner(cp)
                .hostnameVerifier(new HostnameVerifier() {
                    @Override
                    public boolean verify(String hostname, SSLSession session) {
                        return true;
                    }
                }).build();
        client.certificatePinner();
        b.certificatePinner();
        client.hostnameVerifier();

        Request request = new Request.Builder()
                .url("https://" + url)
                .header("Authorization", Credentials.basic(username, password))
                .header("Authorization", token)
                .build();

        Call call = client.newCall(request);
        try {
            Response response = call.execute();
            b.newCall(request).execute();
            result = response.body().string();
        } catch (ConnectException e) {
            return url + " can't be reached";
        } catch (NoRouteToHostException e) {
            return "No route to " + url;
        } catch (SocketTimeoutException e) {
            return url + " timed out";
        } catch (StreamResetException e) {
            return url + " had a " + String.valueOf(e.errorCode);
        } catch (SSLHandshakeException e) {
            return url + "'s connection has been closed";
        } catch (UnknownHostException e) {
            return "Unable to resolve host " + url;
        }
        return result;
    }

}
