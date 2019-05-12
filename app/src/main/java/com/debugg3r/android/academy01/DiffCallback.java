package com.debugg3r.android.academy01;

import android.support.v7.util.DiffUtil;

import com.debugg3r.android.academy01.data.Activity;
import com.debugg3r.android.academy01.data.Talk;

import java.util.List;

public class DiffCallback extends DiffUtil.Callback {
    private final List<Activity> oldList;
    private final List<Activity> newList;

    public DiffCallback(List<Activity> oldList, List<Activity> newList) {
        this.oldList = oldList;
        this.newList = newList;
    }

    @Override
    public int getOldListSize() {
        return oldList.size();
    }

    @Override
    public int getNewListSize() {
        return newList.size();
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        return oldList.get(oldItemPosition).equals(newList.get(newItemPosition));
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        return oldList.get(oldItemPosition).equals(newList.get(newItemPosition));
    }
}
