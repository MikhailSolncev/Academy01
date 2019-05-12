package com.debugg3r.android.academy01.data;

import java.io.Serializable;

public class Activity implements Serializable {
    public String time;
    public String title;

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Activity)
            return time.equals(((Activity) obj).time) && title.equals(((Activity) obj).title);
        return super.equals(obj);
    }
}
