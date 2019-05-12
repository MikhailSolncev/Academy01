package com.debugg3r.android.academy01.data;

import com.debugg3r.android.academy01.DataAdapter;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

public class DataProvider implements Runnable{
    private static DataProvider instance;
    private List<Activity> data;
    private DataAdapter<Activity> adapter;

    private DataProvider(){
    }

    public static synchronized DataProvider getInstance() {
        if (instance == null)
            instance = new DataProvider();
        return instance;
    }

    public static List<Activity> getData(){
        List<Activity> result = new LinkedList<>();

        fillTestData(result);

        return result;
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
            talk.speaker = "speaker '" + number + "'";
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

    public void provideData(DataAdapter<Activity> adapter) {
        if (data == null)
            data = new ArrayList<>();
            //data = getData();

        this.adapter = adapter;
        adapter.updateData(data);

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

        data = getData();

        if (adapter != null)
            adapter.updateData(data);
    }

    @Override
    public void run() {

    }

}
