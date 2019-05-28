package com.debugg3r.android.academy01.data;

import com.google.gson.annotations.SerializedName;

public class Talk extends Activity {
    public String description;
    public Byte room;
    @SerializedName("speaker")
    public String speakerId;
    @SerializedName("doNotSerialize")
    private Speaker speaker;

    public String track;

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    public void setSpeaker(Speaker speaker) {
        this.speaker = speaker;
    }

    public String getSpeakerName() {
        if (speaker == null) return "";
        return speaker.firstName + " " + speaker.lastName;
    }
    public String getSpeakerCountry() {
        if (speaker == null) return "";
        return speaker.flagImage;
    }
    public String getSpeakerCompany() {
        if (speaker == null) return "";
        return speaker.company;
    }
}
