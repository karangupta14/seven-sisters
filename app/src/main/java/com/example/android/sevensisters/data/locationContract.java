package com.example.android.sevensisters.data;

import android.provider.BaseColumns;

public class locationContract {
    public locationContract(){}

    public static abstract class locationEntry implements BaseColumns {
        public static final String TABLE_NAME = "location";
        public static final String TABLE_COLUMN_LOCATION_NAME = "location_name";
        public static final String TABLE_COLUMN_LOCATION_DESCRIPTION = "description";
        public static final String TABLE_COLUMN_LOCATION_CITY = "city";
    }
}
