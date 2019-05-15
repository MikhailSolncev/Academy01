package com.debugg3r.android.academy01.data;

import java.util.List;

public class DevfestResponse {
    public List<Speaker> speakers;
    public Schedule schedule;

    public class Schedule {
        public List<Talk> talks;
        public List<Activity> activities;
    }
}
