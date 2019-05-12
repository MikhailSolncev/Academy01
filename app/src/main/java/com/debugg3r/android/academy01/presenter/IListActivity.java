package com.debugg3r.android.academy01.presenter;

import com.debugg3r.android.academy01.data.Activity;

import java.util.List;

public interface IListActivity {
    void updateData(List<Activity> data);

    void showLoading();
    void showList();
    void showMessage(String text);
}
