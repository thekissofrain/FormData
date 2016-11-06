package utils;


import android.util.Log;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Locale;


public class DebugUtil {

    public static String throwableToString(Throwable t) {
        if (t == null)
            return null;

        StringWriter sw = new StringWriter();
        t.printStackTrace(new PrintWriter(sw));
        return sw.toString();
    }

    public final static void debugThrowable(String TAG, Throwable t) {
        if (t != null) {
            Log.e(TAG, "debugThrowable returned error---------------->>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>" + t.getMessage());

        }
    }

    public final static void debugThrowable(String TAG, Throwable t, int id) {
        if (t != null) {
            Log.e(TAG, id + ": debugThrowable returned error---------------->>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>" + t.getMessage());

        }
    }


    public final static void debugResponse(String TAG, String response) {
        if (response != null) {
            Log.d(TAG, "Response data:" + response);

        }
    }

    public final static void debugStatusCode(String TAG, int statusCode) {
        String msg = String.format(Locale.US, "Return Status Code: %d", statusCode);
        Log.d(TAG, msg);
    }

    public final static void debugMessage(String TAG, String message) {
        if (Constants.DEBUGGABLE) {
            Log.d(TAG, "Message :" + message);
        }
    }


    public final static void debugHttpRequest(String TAG, String message) {
        if (Constants.DEBUGGABLE) {
            Log.d(TAG, "Request  :" + message);
        }
    }

    public final static void debugHttpResponse(String TAG, String message) {
        if (Constants.DEBUGGABLE) {
            Log.d(TAG, "Response  :" + message);
        }
    }

}
