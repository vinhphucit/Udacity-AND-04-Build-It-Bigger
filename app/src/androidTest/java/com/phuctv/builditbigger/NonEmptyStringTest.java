package com.phuctv.builditbigger;

import android.test.AndroidTestCase;
import android.util.Log;

import com.phuctv.builditbigger.tasks.ApiTask;

import junit.framework.Assert;

public class NonEmptyStringTest extends AndroidTestCase {

    private static final String TAG = NonEmptyStringTest.class.getSimpleName();

    public void test() {
        Log.d(TAG, "START TESTING");
        String result = null;
        ApiTask endpointsAsyncTask = new ApiTask(null);
        endpointsAsyncTask.execute();
        try {
            result = endpointsAsyncTask.get();
            Log.d(TAG, "result: " + result);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Assert.assertNotNull(result);
        Log.d(TAG, "FINISH TESTING");
    }

}
