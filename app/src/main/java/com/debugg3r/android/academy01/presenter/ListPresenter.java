package com.debugg3r.android.academy01.presenter;

import android.hardware.camera2.CameraManager;

import com.debugg3r.android.academy01.ListActivity;
import com.debugg3r.android.academy01.data.Activity;
import com.debugg3r.android.academy01.data.DataProvider;

import java.util.ArrayList;
import java.util.List;

public class ListPresenter {
    private static ListPresenter instance;
    private IListActivity activity;
    private List<Activity> activities;

    private ListPresenter() {
    }

    public static ListPresenter getInstance(){
        if (instance == null)
            instance = new ListPresenter();
        return instance;
    }

    static void destroy() {
        instance = null;
    }

    public void attach(IListActivity activity) {
        this.activity = activity;

        if (activities == null)
            activities = new ArrayList<>();
        activity.updateData(activities);

        activity.showLoading();

        Thread thread = new Thread(this::loadData);
        thread.start();
    }

    private void loadData() {
        if (activities.size() == 0) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            List newData = new ArrayList();
            DataProvider.getInstance().fillActivities(newData);
            //DataProvider.fillTestData(newData);
            activities = newData;
        }

        if (activity == null) return;

        activity.updateData(activities);
        activity.showList();
    }

    public void detach() {activity = null;}


}
