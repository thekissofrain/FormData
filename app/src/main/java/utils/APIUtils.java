package utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;


import com.test.formdata.R;

import org.json.JSONObject;

import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;


public class APIUtils { 
	  final static String TAG = APIUtils.class.getClass().getSimpleName();







		public static String getError(JSONObject jsonObject) {
			try
			{
		 if(!jsonObject.isNull(APIConstants.ERROR))
		 {
			 return  jsonObject.getString(APIConstants.ERROR);
		 }
			}catch(Exception e)
			{
				AppUtils.debugThrowable(TAG, e);
			}
			return null;
			 
		}
		
		public static String getErrorMessage(JSONObject jsonObject) {
			try
			{
		 if(!jsonObject.isNull(APIConstants.ERRORMESSAGE))
		 {
			 return  jsonObject.getString(APIConstants.ERRORMESSAGE);
		 }
			}catch(Exception e)
			{
				AppUtils.debugThrowable(TAG, e);
			}
			return null;
			 
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




		
		public static String getStringJson(JSONObject jsonObject, String key)
		{
			try
		{
		if(jsonObject!=null&&!jsonObject.isNull(key))
		{
			return jsonObject.getString(key);
		}
		}
		catch (Exception e) {
		AppUtils.debugThrowable(TAG, e);
		}
			return Constants.EMPTY_VAL;
		}
		
		public static  boolean getBooleanJson(JSONObject jsonObject, String key)
		{
			try
			{
				if(jsonObject!=null&&!jsonObject.isNull(key))
				{
					return jsonObject.getBoolean(key);
				}
			}
			catch (Exception e) {
				AppUtils.debugThrowable(TAG, e);
				 
			}
			return false;
			 
		}
		
		public static  String[] getStringArrayJson(JSONObject jsonObject, String key)
		{/*
			try
		{
		if(jsonObject!=null&&!jsonObject.isNull(key))
		{
			return jsonObject.getString(key);
		}
		}
		catch (Exception e) {
		AppUtils.debugThrowable(TAG, e);
		}
			return Constants.EMPTY_VAL;
			
		*/
			return null;
		}


		
		
		
		public static Date GetItemDate(final String date)
		{
		  //  final Calendar cal = Calendar.getInstance(TimeZone.getDefault());	
		    //2014-05-09T09:17:49.2744935Z
			String trimmedDate=date.replaceAll("\"","");
		    final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
		    //format.setCalendar(cal);

		    try {
		        return format.parse(trimmedDate);
		    } catch (ParseException e) {
		      AppUtils.debugThrowable(TAG, e);
		      return null;
		    }
		}
		
		public static int minutesDiff(Date earlierDate, Date laterDate)
		{
		    if( earlierDate == null || laterDate == null ) return 0;

		    return (int)((laterDate.getTime()/60000) - (earlierDate.getTime()/60000));
		}
		
		

		
  public static String getNumberFormeted(String number)
	{
		try
		{
	        return NumberFormat.getNumberInstance(Locale.US).format(Integer.parseInt(number)).toString();
		}
		catch(Exception e)
		{
			AppUtils.debugThrowable(TAG, e);	
			return number;
		}
		
	}
		
  

  
}
