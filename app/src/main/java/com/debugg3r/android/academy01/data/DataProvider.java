package com.debugg3r.android.academy01.data;

import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

public class DataProvider {
    public static List<Lecture> getData(){
        List<Lecture> result = new LinkedList<>();

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

        return result;
    }
}
