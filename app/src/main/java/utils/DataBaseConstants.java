
package utils;

import android.net.Uri;

import com.test.formdata.BuildConfig;


public class DataBaseConstants {

    public static final String AUTHORITY1 = BuildConfig.APPLICATION_ID + ".common.dao.ContentProviderImpl";


    public final static String AUTHORITY = "content://" + AUTHORITY1;
    public static final Uri CONTENT_URI = Uri.parse(AUTHORITY);

    public static final String SINGLE_RECORD_MIME_TYPE = "com.test.formdata.cursor.item/" + AUTHORITY1;
    public static final String MULTIPLE_RECORDS_MIME_TYPE = "com.test.formdata.cursor.dir/" + AUTHORITY1;




    //<property id="subtitle">CREATE TABLE user_details_table ("id" VARCHAR,"name" VARCHAR,"age" VARCHAR,
// "email_id" VARCHAR, "phone_no" VARCHAR)</property>
// VARCHAR,"state" VARCHAR,"post_code" INTEGER, "country" VARCHAR)</property>
    public static class USER_DATA {
        public static final String TABLE_NAME = "user_details_table";
        public static final String ID = "id";
        public static final String EMAIL = "email";
        public static final String FORMNO = "formno";
        public static final String MOBILE_NO = "mobileno";
        public static final String FNAME = "fname";
        public static final String LNAME = "lname";
        public static final String GENDER = "gender";
        public static final String DOB = "dob";
        public static final String ADDRESS = "address";


    }

}
