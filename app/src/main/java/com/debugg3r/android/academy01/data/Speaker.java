package com.debugg3r.android.academy01.data;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Speaker implements Serializable {
    public String id = "";
    public String firstName = "";
    public String lastName = "";
    public String location = "";
    public String jobTitle = "";
    public String company = "";
    public String about = "";
    public String photo = "";
    public String flagImage = "";
    public Map<String, String> links = new HashMap<>();

    public String name;
}
