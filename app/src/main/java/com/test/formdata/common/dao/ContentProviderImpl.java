package com.test.formdata.common.dao;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.pm.PackageManager.NameNotFoundException;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;

import utils.DataBaseConstants;
import utils.DebugUtil;

public class ContentProviderImpl extends ContentProvider {

    private String TAG = ContentProviderImpl.class.getSimpleName();


    public static String DATABASE_NAME = "users.db";

    public static int DATABASE_VERSION = 0;
    DatabaseHelper databaseHelper;

    SQLiteDatabase db;


    @Override
    public boolean onCreate() {


        try {



            DATABASE_VERSION = getContext().getPackageManager().getPackageInfo(getContext().getPackageName(), 0).versionCode;
        } catch (NameNotFoundException e) {
            DebugUtil.debugThrowable(TAG, e);//logger.e(e.getMessage());

        }
        databaseHelper = new DatabaseHelper(getContext());
        db = databaseHelper.getWritableDatabase();
        return true;
    }

    @Override
    public String getType(Uri uri) {
        if (uri.getLastPathSegment() == null) {
            return DataBaseConstants.SINGLE_RECORD_MIME_TYPE;

        } else {
            return DataBaseConstants.MULTIPLE_RECORDS_MIME_TYPE;
        }
    }

    @SuppressWarnings("static-access")
    @Override
    public Uri insert(Uri uri, ContentValues values) {
        try {
            String tableName = null;

            if (uri.getLastPathSegment() != null) {
                tableName = uri.getLastPathSegment();
            }
            long id = db.insertWithOnConflict(tableName, null, values, SQLiteDatabase.CONFLICT_IGNORE);

            if (id != -1)
                uri = uri.withAppendedPath(uri, String.valueOf(id));
        } catch (Exception e) {
            DebugUtil.debugThrowable(TAG, e);
        }
        return uri;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        int count = 0;
        try {
            String tableName = null;
            if (uri.getLastPathSegment() != null) {
                tableName = uri.getLastPathSegment();
            }
            count = db.update(tableName, values, selection, selectionArgs);
        } catch (Exception e) {
            DebugUtil.debugThrowable(TAG, e);
        }
        return count;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        int count = 0;
        try {
            String tableName = null;
            if (uri.getLastPathSegment() != null) {
                tableName = uri.getLastPathSegment();
            }
            count = db.delete(tableName, selection, selectionArgs);

        } catch (Exception e) {
            DebugUtil.debugThrowable(TAG, e);
        }
        return count;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        Cursor cursor = null;
        try {
            String tableName = null;
            if (uri.getLastPathSegment() != null) {
                tableName = uri.getLastPathSegment();
            }
            cursor = db.query(tableName, projection, selection, selectionArgs, null, null, sortOrder);
        } catch (Exception e) {
            DebugUtil.debugThrowable(TAG, e);
        }
        return cursor;
    }

    public class DatabaseHelper extends SQLiteOpenHelper {
        private String TAG = DatabaseHelper.class.getSimpleName();
        public Context mCtx;

        public DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
            mCtx = context.getApplicationContext();
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            LocalUpgradeDB localUpgradeDB = new LocalUpgradeDB(mCtx);
            localUpgradeDB.createTables(db, mCtx, 0, DATABASE_VERSION);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            DebugUtil.debugMessage(TAG, "--onUpgrade --");
            LocalUpgradeDB localUpgradeDB = new LocalUpgradeDB(mCtx);

            if (oldVersion < newVersion) {
                localUpgradeDB.createTables(db, mCtx, oldVersion, newVersion);

            }
        }

        public void openDataBase() throws SQLException {

            // Open the database
            String myPath = getContext().getDatabasePath(DATABASE_NAME).getAbsolutePath();
            db = SQLiteDatabase.openDatabase(myPath, null,
                    SQLiteDatabase.OPEN_READWRITE);
            if (db != null)
                DebugUtil.debugMessage(TAG, "openDataBase(), opened database successfully");

        }


    }

}
