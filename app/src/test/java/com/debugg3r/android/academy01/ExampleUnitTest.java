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

    @Test
    public void testHelperOkhttp() {
        String result = InternetHelper.getDataOk();

        assertNotEquals("String is empty \"\"", "", result);
        assertNotEquals("String is empty \"[]\"", "[]", result);
        assertNotEquals("String is empty \"{}\"", "{}", result);
    }

    @Test
    public void testJsonDeserializeJson() {
        String json = "{\"speakers\":[], \"schedule\": {\"talks\": [], \"activities\": []}}";
        Map result = InternetHelper.readJson(json);

        assertEquals("Map size not two", 2, result.size());
        assertTrue("Map does not contains speakers", result.containsKey("speakers"));
        assertFalse("Map contains talks", result.containsKey("talks"));
    }

    @Test
    public void testJsonRalData() {
        String json = InternetHelper.getDataHttp();
        Map result = InternetHelper.readJson(json);

        assertTrue("Result does not contains speakers", result.containsKey("speakers"));
        assertTrue("speakers list is empty", ((List)result.get("speakers")).size() > 0);
        assertTrue("Result does not contains schedule", result.containsKey("schedule"));
        assertEquals("schedule is not size of two", 2, ((Map)result.get("schedule")).size());
        assertEquals("activities is not size of three", 3, ((List)((Map)result.get("schedule")).get("activities")).size());
    }
}
