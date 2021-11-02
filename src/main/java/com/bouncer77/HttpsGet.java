package com.bouncer77;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;

public class HttpsGet {

    public static String connect(String urlme) throws IOException {

        // Create a trust manager that does not validate certificate chains
        TrustManager[] trustAllCerts = new TrustManager[]{
                new X509TrustManager() {
                    public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                        return null;
                    }

                    public void checkClientTrusted(
                            java.security.cert.X509Certificate[] certs, String authType) {
                    }

                    public void checkServerTrusted(
                            java.security.cert.X509Certificate[] certs, String authType) {
                    }
                }
        };

        // Install the all-trusting trust manager
        try {
            SSLContext sc = SSLContext.getInstance("SSL");
            sc.init(null, trustAllCerts, new java.security.SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Now you can access an https URL without having the certificate in the truststore
        URL url = null;
        try {
            url = new URL(urlme);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        assert url != null;
        HttpsURLConnection con = (HttpsURLConnection) url.openConnection();

        int responseCode = 0;
        try {
            con.setRequestMethod("GET");
            responseCode = con.getResponseCode();
        } catch (UnknownHostException e) {
            System.out.println("Ошибка: IP-адрес хоста не может быть определен.");
            System.out.println("Hint: Проверьте корректность url и наличие VPN соединения (при необходимости)");
            System.exit(3);
        }

        InputStream inputStream;
        if (responseCode == HttpURLConnection.HTTP_OK) {
            inputStream = con.getInputStream();
        } else {
            inputStream = con.getErrorStream();
        }

        // Process the response
        BufferedReader reader;
        String line = null;
        reader = new BufferedReader(new InputStreamReader(inputStream));
        String resp = null;
        while ((line = reader.readLine()) != null) {
            // System.out.println(line);
            resp = line;
        }
        inputStream.close();

        return resp;
    }
}
