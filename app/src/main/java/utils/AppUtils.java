package utils;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.provider.Settings;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;


import com.test.formdata.R;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;

/**
 * Created by Yoganand on 11-07-2015.
 */
public class AppUtils {

    private static String TAG = AppUtils.class.getSimpleName();
    private static LinearLayout dashboardProgressBarLayout;
    private static ProgressDialog pd;
    public   final static void debugHttpResponse(String TAG, String message) {
        if(Constants.DEBUGGGABLE_MODE)
        {
            Log.d(TAG, "Response  :"+message);
        }
    }





    public static boolean isValidString(String field) {

        if (field != null && !field.trim().isEmpty()) {
            return true;
        }

        return false;

    }









    private static boolean validateAge(String age) {
        //validate phone numbers of format "1234567890"
       // if(age.matches("\\d")) return true;
            //validating phone number with -, . or spaces
        //   else
        return true;

    }


    public static String getString(byte[] myBytes) throws IOException {
        StringBuilder builder = new StringBuilder(0x10000);
        InputStream inStream = new ByteArrayInputStream(myBytes);
        BufferedReader buffReader = new BufferedReader(new InputStreamReader(
                inStream), 8192);
        String line;
        while ((line = buffReader.readLine()) != null)
            builder.append(line);
        return builder.toString();
    }

    public static boolean isRequestFailure(int statusCode) {
        // TODO Auto-generated method stub
        //BADREQUEST=400,UNAUTHORIZED=401,FORBIDDEN=403,NOTFOUND=404,INTERNALERROR=500,NOTIMPLEMENTED=501,GATEWAYTIMEOUT=503

        //400 BadRequest	InvalidParameter	If the num or page parameters are less than 1
        //	400 BadRequest	InvalidSession	SessionID is not recognized, is closed or is malformed
        //401 Unauthorized	LoginRequired
            /*400 BadRequest	AlreadyLoggedIn	The session is logged in, but this action is only valid for non-logged in users.
			400 BadRequest	BadCredentials	Invalid username/password
			400 BadRequest	InvalidSession	SessionID is not recognized, is closed or is malformed*/

        if (statusCode == ResponseStatusCodes.BADREQUEST || statusCode == ResponseStatusCodes.FORBIDDEN || statusCode == ResponseStatusCodes.NOTFOUND) {
            return true;
        }

        return false;
    }

    public static class ResponseStatusCodes {

        public static final int OK = 200, NOTMODIFIED = 304, CREATED = 201, ACCEPTED = 202, PARTIALINFORMATION = 203,
                NORESPONSE = 204, BADREQUEST = 400, UNAUTHORIZED = 401, PAYMENTREQUIRED = 402,
                FORBIDDEN = 403, NOTFOUND = 404, INTERNALERROR = 500, NOTIMPLEMENTED = 501,
                GATEWAYTIMEOUT = 503, MOVED = 301, FOUND = 302, METHOD = 303;
    }

    public static boolean isServerInvalidResponse(int statusCode) {
        // TODO Auto-generated method stub
        if (statusCode == 0 || statusCode >= 500) {
            return true;
        }
        return false;
    }

    public static boolean isOnline(Context context) {
        boolean online = false;
        ConnectivityManager connMgr = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connMgr != null) {
            NetworkInfo netInfo = connMgr.getActiveNetworkInfo();
            if (netInfo != null)
                online = netInfo.isConnected();
        }
        return online;
    }

    public final static void debugThrowable(String TAG, Throwable t) {
        if (t != null && Constants.DEBUGGGABLE_MODE) {
            Log.e(TAG, "debugThrowable returned error---------------->>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>" + t.getMessage());

        }
    }

    public final static void debugThrowable(String TAG, Throwable t, int id) {
        if (t != null && Constants.DEBUGGGABLE_MODE) {
            Log.e(TAG, id + ": debugThrowable returned error---------------->>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>" + t.getMessage());

        }
    }

    public static String getCurrentTimeStamp() {
        Timestamp tsTemp = new Timestamp(System.currentTimeMillis());
        String ts = tsTemp.toString();
        return ts;
    }

    public static long getTimeDiff(String sTimeStamp, String eTimeStamp) {
        long hours = -1;
        long timeDiff = 0;
        try {
            Timestamp startTime = null;
            Timestamp endTime = null;
            if (sTimeStamp != null && sTimeStamp != "") {
                startTime = Timestamp.valueOf(sTimeStamp);
            }

            if (eTimeStamp != null && eTimeStamp != "") {
                endTime = Timestamp.valueOf(eTimeStamp);
            }

            if (startTime != null && endTime != null) {
                timeDiff = endTime.getTime() - startTime.getTime();
                hours = TimeUnit.MILLISECONDS.toHours(timeDiff);
            }

        } catch (Exception e) {
            debugThrowable(TAG, e);
        }
        return hours;
    }

    public static String getCurrentDate(String format) {
        DateFormat dateFormat = new SimpleDateFormat(format);
        Date date = new Date();

        return dateFormat.format(date);
    }

    public Date expiringSoonDate() {
        Calendar currentDate = Calendar.getInstance();
        currentDate.add(Calendar.HOUR, 48);
        return currentDate.getTime();
    }

    public Date newOffersDate() {
        Calendar currentDate = Calendar.getInstance();
        currentDate.add(Calendar.HOUR, -48);
        return currentDate.getTime();
    }


    public static long getDiffHours(String sDate, String eDate, String format) {
        long hours = -1;
        long timeDiff = 0;
        DateFormat formatter = null;
        Date startDate = null;
        Date endDate = null;

        try {
            formatter = new SimpleDateFormat(format);

            if (sDate != null) {
                startDate = (Date) formatter.parse(sDate);
            }

            if (eDate != null) {
                endDate = (Date) formatter.parse(eDate);
            }


            if (startDate != null && endDate != null) {
                timeDiff = endDate.getTime() - startDate.getTime();
                hours = TimeUnit.MILLISECONDS.toHours(timeDiff);
            }

        } catch (Exception e) {
            debugThrowable(TAG, e);

        }
        return hours;
    }


    public static String encrypt(String value, Context context) {
        try {
            final byte[] bytes = value != null ? value.getBytes(Constants.UTF8) : new byte[0];
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(Constants.PBE_WITH_MD5_AND_DES);
            SecretKey key = keyFactory.generateSecret(new PBEKeySpec(getSecretKey(context)));
            Cipher pbeCipher = Cipher.getInstance(Constants.PBE_WITH_MD5_AND_DES);
            pbeCipher.init(Cipher.ENCRYPT_MODE, key, new PBEParameterSpec(Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID).getBytes(Constants.UTF8), 20));
            return value;
        } catch (Exception e) {
            AppUtils.debugThrowable(TAG, e);
            return Constants.EMPTY_VAL;
        }

    }


    public static String decrypt(String value, Context context) {
        try {
            final byte[] bytes = value != null ? Base64.decode(value, Base64.DEFAULT) : new byte[0];
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(Constants.PBE_WITH_MD5_AND_DES);
            SecretKey key = keyFactory.generateSecret(new PBEKeySpec(getSecretKey(context)));
            Cipher pbeCipher = Cipher.getInstance(Constants.PBE_WITH_MD5_AND_DES);
            pbeCipher.init(Cipher.DECRYPT_MODE, key, new PBEParameterSpec(Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID).getBytes(Constants.UTF8), 20));
            return value;
        } catch (Exception e) {
            return value;
        }
    }

    public static char[] getSecretKey(Context context) {
        // TODO Auto-generated method stub
        StringBuilder builder = new StringBuilder(0x10000);

        builder.append(context.getPackageName());
        builder.append(Constants.HIPHEN);

        String appendToken = getDeviceTokenID(context);
        try {
            if (appendToken.length() > 10) {
                appendToken = appendToken.substring(2, 10);
            }
        } catch (Exception e) {
            AppUtils.debugThrowable(TAG, e);
        }
        builder.append(Constants.LEFTSQUAREBRACKET);
        builder.append(appendToken);
        builder.append(Constants.RIGHTSQUAREBRACKET);

        return builder.toString().toCharArray();

    }

    public static String getDeviceTokenID(Context context) {
        try {
            return Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
        } catch (Exception e) {
            return Constants.EMPTY_VAL;
        }
        //return Constants.EMPTY_VAL;
    }




    public static void hideProgressDialog() {
        try {
            if (pd != null) {
                pd.dismiss();
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
        if (dashboardProgressBarLayout != null && dashboardProgressBarLayout.getVisibility() == View.VISIBLE) {
            try {
                dashboardProgressBarLayout.setVisibility(View.GONE);
            } catch (IllegalArgumentException e) {
                Log.w(TAG, e.getMessage());
            }

            dashboardProgressBarLayout = null;
        }

    }


    /*public static boolean isValidString(String field) {

        if (field != null && !field.trim().isEmpty()) {
            return true;
        }

        return false;

    }*/


    public static void displayProgressDailog(final Activity activity, DialogInterface.OnCancelListener listener, LinearLayout progressBarLayout) {
        if (dashboardProgressBarLayout != null && dashboardProgressBarLayout.getVisibility() == View.VISIBLE) {
            dashboardProgressBarLayout.setVisibility(View.GONE);
        }

        if (activity != null) {
            dashboardProgressBarLayout = progressBarLayout;
            try {
                if (dashboardProgressBarLayout != null)
                    dashboardProgressBarLayout.setVisibility(View.VISIBLE);

            } catch (Exception e) {
                AppUtils.debugThrowable(TAG, e);
            }
        }
    }


    public static String getErrorMessage(JSONObject jsonObject) {
        try {
            DebugUtil.debugMessage(TAG, "JSONNNNNNNNNNNNNNNNDIRECT Message " + jsonObject.toString());

            return jsonObject.getString("error");

        } catch (Exception e) {
            e.printStackTrace();
        }
        return Constants.EMPTY_VAL;
    }





private static boolean checkNullLength(String name,int length)
{
    if(name!=null&&name.length()>length)
    {
        return true;
    }
    return false;
}


    public static Bitmap getBitmapFromURL(String src) {
        try {
            Log.e("src",src);
            URL url = new URL(src);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            Bitmap myBitmap = BitmapFactory.decodeStream(input);
            Log.e("Bitmap","returned");
            return myBitmap;
        } catch (IOException e) {
            e.printStackTrace();
            Log.e("Exception",e.getMessage());
            return null;
        }
    }
}


