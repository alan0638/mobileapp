package com.studioyuando.hw4;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


/**
 * Created by kudouyuandou on 2017/05/06.
 */

public class SessionDatabase extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "manager.db";
    public static final int DATABASE_VERSION = 2;

    public SessionDatabase(Context context) {
        super(context,DATABASE_NAME ,null ,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        final String CREATE_WAITLIST_TABLE = "CREATE TABLE " + SessionManagerContract.SessionManagerEntry.TABLE_NAME + " (" +
                SessionManagerContract.SessionManagerEntry._ID + "   INTEGER PRIMARY KEY AUTOINCREMENT,  " +
                SessionManagerContract.SessionManagerEntry.COLUMN_GNAME + " TEXT NOT NULL, " +
                SessionManagerContract.SessionManagerEntry.COLUMN_GAGE + "  INTEGER NOT NULL, "+
                SessionManagerContract.SessionManagerEntry.COLUMN_GGENDER + " INTEGER NOT NULL, " +
                SessionManagerContract.SessionManagerEntry.COLUMN_TIMESTAMP + " TIMESTAMP DEFAULT CURRENT_TIMESTAMP" +
                "); ";

        db.execSQL(CREATE_WAITLIST_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
        final String DROP_TABLE = "DROP TABLE IF EXISTS " + SessionManagerContract.SessionManagerEntry.TABLE_NAME;
        database.execSQL(DROP_TABLE);
        onCreate(database);
    }

}
