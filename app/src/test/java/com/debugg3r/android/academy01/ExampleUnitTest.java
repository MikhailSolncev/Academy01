package com.debugg3r.android.academy01;

import com.debugg3r.android.academy01.data.InternetHelper;
import com.google.gson.Gson;

import org.junit.Test;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void testHelperConnection(){
        String data = InternetHelper.getDataHttp();
        assertNotEquals("[]", data);
        //assertEquals("[]", data);

        Gson gson = new Gson();
        Map result = gson.fromJson(data, Map.class);
    }
}
