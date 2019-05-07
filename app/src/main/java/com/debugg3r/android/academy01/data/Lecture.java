package com.debugg3r.android.academy01.data;

import java.io.Serializable;

public class Lecture implements Serializable {
    public String time;
    public String theme;
    public String description;
    public Author author;

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Lecture)
            return time.equals(((Lecture) obj).time) && theme.equals(((Lecture) obj).theme);
        return super.equals(obj);
    }
}
