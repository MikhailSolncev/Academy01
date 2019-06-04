package com.debugg3r.android.academy01.data;

import android.os.Build;

import com.debugg3r.android.academy01.DataAdapter;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

public class DataProvider{
    private static DataProvider instance;
    private List<Activity> activities;
    private List<Speaker> speakers;
    private DataAdapter<Activity> adapter;

    private DataProvider(){
    }

    public static synchronized DataProvider getInstance() {
        if (instance == null) {
            instance = new DataProvider();
            instance.getActualData();
        }
        return instance;
    }

    public static List<Activity> getData(){
        List<Activity> result = new LinkedList<>();

        fillTestData(result);

        return result;
    }

    private void getActualData() {
        DevfestResponse response = InternetHelper.getDataRetrofit();

        activities = new ArrayList<>();
        activities.addAll(response.schedule.activities);
        activities.addAll(response.schedule.talks);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            activities.sort((a1, a2) -> a1.time.compareTo(a2.time));
        }

        speakers = new ArrayList<>();
        speakers.addAll(response.speakers);
    }

    public static void fillTestData(List<Activity> result) {

        result.clear();

        Activity activity = new Activity();
        activity.time = "00:00";
        activity.title = "Opening";
        result.add(activity);

        for (int i = 1; i < 25; i++) {
            String number = String.format(Locale.US,"%02d", i);
            Talk talk = new Talk();
            talk.time = "" + number + ":" + number;
            talk.title = "title of talk " + number + " : " + number;
            talk.description = "description" + number + " description" + number + " description" + number + " description" + number + "ololololo";
            //talk.speaker = new Speaker();
            talk.speakerId = "speaker '" + number + "'";
//            talk.speaker.company = "Company " + number;
//            talk.speaker.name = "Author" + number + " Name" + number;
            talk.room = (byte)i;
            talk.track = "android";

            result.add(talk);
        }

        activity = new Activity();
        activity.title = "Closing";
        activity.time = "26:26";
        result.add(activity);
    }

    public void fillActivities(List newData) {
        newData.clear();
        newData.addAll(activities);
    }

    public void provideData(DataAdapter<Activity> adapter) {
        if (activities == null)
            activities = new ArrayList();
            //activities = getData();

        this.adapter = adapter;
        adapter.updateData(activities);

        Thread thread = new Thread(this::doHardWork);
        thread.start();
    }

    public void detachAdapter() {
        adapter = null;
    }

    void doHardWork() {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //activities = getData();
        getActualData();

        if (adapter != null)
            adapter.updateData(activities);
    }

    public Activity getActivity(String time, String title) {
        Activity result = null;
        for (Activity activity : activities) {
            if (activity.time.equals(time) && activity.title.equals(title)) {
                result = activity;
                break;
            }
        }
        return result;
    }
}
