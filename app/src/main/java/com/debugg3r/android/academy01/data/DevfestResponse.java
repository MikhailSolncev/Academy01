package com.debugg3r.android.academy01.data;

import java.util.List;

public class DevfestResponse {
    public List<Speaker> speakers;
    public Schedule schedule;

    public void processSpeakers() {
        for (Talk talk : schedule.talks)
            for (Speaker speaker : speakers)
                if (talk.speakerId.equals(speaker.id)) {
                    talk.setSpeaker(speaker);
                    break;
                }
    }

    public class Schedule {
        public List<Talk> talks;
        public List<Activity> activities;
    }
}
