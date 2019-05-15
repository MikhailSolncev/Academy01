package com.debugg3r.android.academy01;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.debugg3r.android.academy01.data.DevfestResponse;
import com.debugg3r.android.academy01.data.InternetHelper;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("com.debugg3r.android.academy01", appContext.getPackageName());
    }

    @Test
    public void testHelperRetrofit() {
        DevfestResponse response = InternetHelper.getDataRetrofit();

        assertNotNull("result does not contains speakers", response.speakers);
        assertTrue("speakers list is empty", response.speakers.size() > 0);
        assertNotNull("Result does not contains schedule", response.schedule);
        assertNotNull("schedule is not size of two", response.schedule.activities);
        assertNotNull("schedule is not size of two", response.schedule.talks);
        assertEquals("activities is not size of three", 3, response.schedule.activities.size());
    }
}
