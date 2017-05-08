package com.studioyuando.hw4;

import android.provider.BaseColumns;

/**
 * Created by kudouyuandou on 2017/05/05.
 */


public class SessionManagerContract {
    public static final class SessionManagerEntry implements BaseColumns{
        public static final String TABLE_NAME = "manager";
        public static final String COLUMN_GNAME = "guestName";
        public static final String COLUMN_GAGE = "guestAge";
        public static final String COLUMN_GGENDER = "guestGender";
        public static final String COLUMN_TIMESTAMP = "timestamp";
    }
}
