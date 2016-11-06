package com.test.formdata.common.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;


import com.test.formdata.dto.UserBean;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import utils.AppUtils;
import utils.Constants;
import utils.DataBaseConstants;
import utils.DebugUtil;

public class UserDAO {
    private static final String TAG = UserDAO.class.getSimpleName();
    Context mCtx;

    public UserDAO(Context context) {
        this.mCtx = context;
    }

    public void insertUserDetails(UserBean userBean) {
        try {

 ;

             ContentValues contentValues = new ContentValues();
            contentValues.put(DataBaseConstants.USER_DATA.ID,userBean.id);
            contentValues.put(DataBaseConstants.USER_DATA.FORMNO,userBean.formno);
            contentValues.put(DataBaseConstants.USER_DATA.MOBILE_NO, userBean.mobileno);
            contentValues.put(DataBaseConstants.USER_DATA.FNAME, userBean.fname);
            contentValues.put(DataBaseConstants.USER_DATA.LNAME, userBean.lname);
            contentValues.put(DataBaseConstants.USER_DATA.GENDER, userBean.gender);
            contentValues.put(DataBaseConstants.USER_DATA.EMAIL, userBean.email);
            contentValues.put(DataBaseConstants.USER_DATA.DOB, userBean.dob);
            contentValues.put(DataBaseConstants.USER_DATA.ADDRESS, userBean.address);


            mCtx.getContentResolver().insert(Uri.withAppendedPath(DataBaseConstants.CONTENT_URI, DataBaseConstants.USER_DATA.TABLE_NAME), contentValues);


        } catch (Exception e) {
            AppUtils.debugThrowable(TAG, e);
            e.printStackTrace();
        }
    }


   /* public void updateUserDetails(UserBean userBean) {
        try {


            DebugUtil.debugMessage(getClass().getSimpleName(), "userDAO:111:" + userBean.toString());

            ContentValues contentValues = new ContentValues();
            contentValues.put(DataBaseConstants.USER_DATA.ID, userBean.id);
            contentValues.put(DataBaseConstants.USER_DATA.DATE,userBean.id);
            contentValues.put(DataBaseConstants.USER_DATA.NAME, userBean.name);
            contentValues.put(DataBaseConstants.USER_DATA.EMAIL, userBean.email);
            contentValues.put(DataBaseConstants.USER_DATA.PH_NO, userBean.phno);
            contentValues.put(DataBaseConstants.USER_DATA.AGE, userBean.age);

            mCtx.getContentResolver().update(Uri.withAppendedPath(DataBaseConstants.CONTENT_URI, DataBaseConstants.USER_DATA.TABLE_NAME), contentValues, DataBaseConstants.USER_DATA.ID + " = ?", new String[]{userBean.id});



        } catch (Exception e) {
            AppUtils.debugThrowable(TAG, e);
            e.printStackTrace();
        }
    }
*/

    public List<UserBean> getUserDetails(String userId) {
        Cursor cursor = null;
        Cursor cursor1 = null;
        UserBean userBean = new UserBean();
        String userUniqueID= Constants.EMPTY_VAL;
        List<UserBean> userBeans = new ArrayList<>();
        try {
            String name =userId;

            if (userId.length() != 0) {

                userId = "%" + userId + "%";
            }

            final String[] projection = new String[]{"*"};
            cursor = mCtx.getContentResolver().query(Uri.withAppendedPath(DataBaseConstants.CONTENT_URI, DataBaseConstants.USER_DATA.TABLE_NAME), projection, DataBaseConstants.USER_DATA.ID + " like '"+userId+"' or "+DataBaseConstants.USER_DATA.MOBILE_NO + " like '"+userId+"' or "+DataBaseConstants.USER_DATA.FORMNO + " like '"+userId+ "'", null,null);
            if (cursor != null && cursor.moveToFirst()) {
                do {
                    userBean.id = ((cursor.getString(cursor.getColumnIndex(DataBaseConstants.USER_DATA.ID))));
                    userBean.formno = ((cursor.getString(cursor.getColumnIndex(DataBaseConstants.USER_DATA.FORMNO))));
                    userBean.mobileno = ((cursor.getString(cursor.getColumnIndex(DataBaseConstants.USER_DATA.MOBILE_NO))));
                    userBean.fname = ((cursor.getString(cursor.getColumnIndex(DataBaseConstants.USER_DATA.FNAME))));
                    userBean.lname = ((cursor.getString(cursor.getColumnIndex(DataBaseConstants.USER_DATA.LNAME))));;

                    userBean.gender = ((cursor.getString(cursor.getColumnIndex(DataBaseConstants.USER_DATA.GENDER))));
                    userBean.email = ((cursor.getString(cursor.getColumnIndex(DataBaseConstants.USER_DATA.EMAIL))));
                    userBean.dob = ((cursor.getString(cursor.getColumnIndex(DataBaseConstants.USER_DATA.DOB))));
                    userBean.address = ((cursor.getString(cursor.getColumnIndex(DataBaseConstants.USER_DATA.ADDRESS))));;
                     userUniqueID = userBean.id;
                    //apiDetailsBean.setJsonObj(cursor.getString(cursor.getColumnIndex(DataBaseConstants.API_DATA_LIST.ZCONTENT)));
                } while (cursor.moveToNext());
            }
            cursor.close();
        } catch (Exception e) {
            e.printStackTrace();
            AppUtils.debugThrowable(TAG, e);
        }
        userBeans.add(userBean);
        return userBeans;
    }


  /*  public  List<UserBean> getUserDetails(String userString) {
        Cursor cursor = null;
        Cursor cursor1 = null;
       List<UserBean> userBeanList = new ArrayList<>();
        try {
            final String[] projection = new String[]{"*"};

            //String input = "Sat Feb 17 2012";
            Date date = new SimpleDateFormat("yyyy-M-d", Locale.ENGLISH).parse(fromDate);
            long millisecondstart = date.getTime();

            Date date1 = new SimpleDateFormat("yyyy-M-d", Locale.ENGLISH).parse(endDate);
            long millisecondend = date1.getTime();


          // cursor = mCtx.getContentResolver().query(Uri.withAppendedPath(DataBaseConstants.CONTENT_URI, DataBaseConstants.USER_DATA.TABLE_NAME), projection, DataBaseConstants.USER_DATA.DATE + " > " +millisecondstart + " and "+DataBaseConstants.USER_DATA.DATE + " < " +millisecondend  , null, null);
           cursor = mCtx.getContentResolver().query(Uri.withAppendedPath(DataBaseConstants.CONTENT_URI, DataBaseConstants.USER_DATA.TABLE_NAME), projection, null  , null, null);
            if (cursor != null && cursor.moveToFirst()) {
                do {
                    UserBean userBean = new UserBean();
                    userBean.name = ((cursor.getString(cursor.getColumnIndex(DataBaseConstants.USER_DATA.NAME))));
                    userBean.phno = ((cursor.getString(cursor.getColumnIndex(DataBaseConstants.USER_DATA.PH_NO))));
                    userBean.email = ((cursor.getString(cursor.getColumnIndex(DataBaseConstants.USER_DATA.EMAIL))));
                    userBean.age = ((cursor.getString(cursor.getColumnIndex(DataBaseConstants.USER_DATA.AGE))));
                    userBean.id = ((cursor.getString(cursor.getColumnIndex(DataBaseConstants.USER_DATA.ID))));
                    userBean.date = ((cursor.getString(cursor.getColumnIndex(DataBaseConstants.USER_DATA.DATE))));
                    System.out.println("  gfgfdgfdgfdg  " + userBean.date);

                    System.out.println(millisecondstart+"  gfgfdgfdgfdg  " + millisecondend);

                    System.out.println("  gfgfdgfdgfdg  " + (Long.parseLong(userBean.date) >= millisecondstart));
                    System.out.println("  gfgfdgfdgfdg  " + (Long.parseLong(userBean.date) <= millisecondend));
                    if(Long.parseLong(userBean.date)>=millisecondstart&&Long.parseLong(userBean.date)<=millisecondend) {
                        userBeanList.add(userBean);
                    }
                    //apiDetailsBean.setJsonObj(cursor.getString(cursor.getColumnIndex(DataBaseConstants.API_DATA_LIST.ZCONTENT)));
                } while (cursor.moveToNext());
            }
            cursor.close();
        } catch (Exception e) {
            e.printStackTrace();
            AppUtils.debugThrowable(TAG, e);
        }
        return userBeanList;
    }
*/
    public void deleteRow(String id){
        try {
            mCtx.getContentResolver().delete(Uri.withAppendedPath(DataBaseConstants.CONTENT_URI, DataBaseConstants.USER_DATA.TABLE_NAME), DataBaseConstants.USER_DATA.ID + " = ?", new String[]{id});


        } catch (Exception e) {
            AppUtils.debugThrowable(TAG, e);
            e.printStackTrace();
        }
    }
}
