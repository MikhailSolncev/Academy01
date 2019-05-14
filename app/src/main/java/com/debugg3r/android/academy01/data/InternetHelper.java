package com.debugg3r.android.academy01.data;

import android.util.Log;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;

public class InternetHelper {
    private static String mBaseUrl = "storage.yandexcloud.net";
    private static String mAddress = "devfestapi/db.json";
    private static String LOG_TAG = "INTERNET_HELPER";

    public static Map<String, Object> readJson(String json) {
        Map result = new HashMap<String, List>();

        Gson gson = new Gson();
//        Type typeToken = new TypeToken<Map<String, Map>>() {}.getType();
//        Map<String, Object> data = gson.fromJson(json, typeToken);
        Map<String, Object> data = gson.fromJson(json, Map.class);

        for (Map.Entry<String, Object> entry: data.entrySet()) {
            switch (entry.getKey().toLowerCase()) {
                case "speakers":
                    result.put(entry.getKey(), readListSpeakers((List)entry.getValue()));
                    break;

                case "schedule":
                    result.put(entry.getKey(), readMapSchedule((Map)entry.getValue()));
                    break;
            }
        }

        return result;
    }

    public static Map<String, List> readMapSchedule(Map<String, List> value) {
        Map<String, List> result = new HashMap<>();
        for (Map.Entry<String, List> entry : value.entrySet())
            result.put(entry.getKey(), readListActivity(entry.getValue()));
        return result;
    }

    public static List<Activity> readListActivity(List<Map<String, Object>> value) {
        List<Activity> result = new ArrayList();

        for (Map<String, Object> entry : value) {
            Activity activity;
            if (entry.containsKey("room") || entry.containsKey("description"))
                activity = new Talk();
            else
                activity = new Activity();

            if (entry.containsKey("title")) activity.title = (String) entry.get("title");
            if (entry.containsKey("time")) activity.title = (String) entry.get("time");
            if (entry.containsKey("description")) ((Talk)activity).title = (String) entry.get("description");
            if (entry.containsKey("room")) ((Talk)activity).room = (byte)(int)((double) entry.get("room"));
            if (entry.containsKey("track")) ((Talk)activity).track = (String) entry.get("track");
            if (entry.containsKey("speaker")) ((Talk)activity).speaker = (String) entry.get("speaker");

            result.add(activity);
        }
        return result;
    }

    public static List<Speaker> readListSpeakers(List<Map> value) {
        List result = new ArrayList();
        for (Map<String, Object> entry : value) {
            if (!entry.containsKey("id")) continue;

            Speaker speaker = new Speaker();
            /*
            "id": "alex-efremenkov",
            "firstName": "Александр",
            "lastName": "Ефременков",
            "location": "Москва, Россия",
            "jobTitle": "System Android developer",
            "company": "Яндекс.Такси",
            "about": "Пишу код, смотрю в Android.",
            "photo": "https://devfest-spb.com/img/speakers/AlexanderEfremenkov.jpg",
            "flagImage": "russia",
            /**/
            if (entry.containsKey("id")) speaker.id = (String) entry.get("id");
            if (entry.containsKey("firstName")) speaker.firstName = (String) entry.get("firstName");
            if (entry.containsKey("lastName")) speaker.lastName = (String) entry.get("lastName");
            if (entry.containsKey("location")) speaker.location = (String) entry.get("location");
            if (entry.containsKey("jobTitle")) speaker.jobTitle = (String) entry.get("jobTitle");
            if (entry.containsKey("company")) speaker.company = (String) entry.get("company");
            if (entry.containsKey("about")) speaker.about = (String) entry.get("about");
            if (entry.containsKey("photo")) speaker.photo = (String) entry.get("photo");
            if (entry.containsKey("flagImage")) speaker.flagImage = (String) entry.get("flagImage");
            if (entry.containsKey("links"))
                for (Map.Entry<String, String> link : ((Map<String, String>)entry.get("links")).entrySet())
                    speaker.links.put(link.getKey(), link.getValue());

            result.add(speaker);
        }
        return result;
    }

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
