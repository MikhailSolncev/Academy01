package com.debugg3r.android.academy01.data;

import com.debugg3r.android.academy01.DataAdapter;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

public class DataProvider implements Runnable{
    private static DataProvider instance;
    private List<Lecture> data;
    private DataAdapter<Lecture> adapter;

    private DataProvider(){
    }

    public static synchronized DataProvider getInstance() {
        if (instance == null)
            instance = new DataProvider();
        return instance;
    }

    public static List<Lecture> getData(){
        List<Lecture> result = new LinkedList<>();

        fillTestData(result);

        return result;
    }

    private static void fillTestData(List<Lecture> result) {
        for (int i = 1; i < 25; i++) {
            String number = String.format(Locale.US,"%02d", i);
            Lecture lecture = new Lecture();
            lecture.time = "" + number + ":" + number;
            lecture.theme = "theme of lecture " + number + " : " + number;
            lecture.description = "description" + number + " description" + number + " description" + number + " description" + number + "ololololo";
            lecture.author = new Author();
            lecture.author.company = "Company " + number;
            lecture.author.name = "Author" + number + " Name" + number;

            result.add(lecture);
        }
    }

    public void provideData(DataAdapter<Lecture> adapter) {
        if (data == null)
            data = new ArrayList<Lecture>();
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
            Thread.sleep(5000);
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
