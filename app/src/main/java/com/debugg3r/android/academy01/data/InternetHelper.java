package com.debugg3r.android.academy01.data;

import android.util.Log;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.nio.CharBuffer;

import javax.net.ssl.HttpsURLConnection;

public class InternetHelper {
    private static String mBaseUrl = "storage.yandexcloud.net";
    private static String mAddress = "devfestapi/db.json";
    private static String LOG_TAG = "INTERNET_HELPER";

    public static String getDataHttp() {
        String result = "[]";

        //  create url
        URL url;
        try {
            url = new URL("https://" + mBaseUrl + "/" + mAddress);
        } catch (MalformedURLException e) {
            e.printStackTrace();
            Log.e(LOG_TAG, "Error creating URL");
            System.out.println(e.getMessage());
            return result;
        }

        //  create connection
        HttpsURLConnection connection;
        try {
            connection = (HttpsURLConnection) url.openConnection();
        } catch (IOException e) {
            e.printStackTrace();
            Log.e(LOG_TAG, "Error creating connection");
            System.out.println(e.getMessage());
            return result;
        }

        //  set parameters
        connection.setReadTimeout(10000);
        connection.setConnectTimeout(15000);
        connection.setDoInput(true);
        try {
            connection.setRequestMethod("GET");
        } catch (ProtocolException e) {
            e.printStackTrace();
            Log.e(LOG_TAG, "Error request method");
            System.out.println(e.getMessage());
            return result;
        }

        //  Do connect
        try {
            connection.connect();
        } catch (IOException e) {
            e.printStackTrace();
            Log.e(LOG_TAG, "Error connecting");
            System.out.println(e.getMessage());
            return result;
        } finally {
            //connection.disconnect();
        }

        InputStream stream;
        try {
            //stream = new BufferedInputStream(connection.getInputStream());
            stream = connection.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
            Log.e(LOG_TAG, "Error getting input stream");
            System.out.println(e.getMessage());
            return result;
        } finally {
            //connection.disconnect();
        }

        BufferedReader in = new BufferedReader(new InputStreamReader(stream));
        try {
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            result = response.toString();

        } catch (IOException e) {
            e.printStackTrace();
            Log.e(LOG_TAG, "Error reading input stream");
            System.out.println(e.getMessage());
            return result;
        } finally {
            //connection.disconnect();
        }

        return result;
    }

    static String getDataOk() {
        return "";
    }

    static String getDataRetrofit() {
        return "";
    }
}
