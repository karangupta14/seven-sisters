package com.example.android.sevensisters.data;

import android.provider.BaseColumns;

public class hotelContract {
    public hotelContract(){}

    public static abstract class hotelEntry implements BaseColumns {
        public static final String TABLE_NAME = "hotel";
        public static final String TABLE_COLUMN_HOTEL_NAME = "hotel_name";
        public static final String TABLE_COLUMN_HOTEL_DESCRIPTION = "description";
        public static final String TABLE_COLUMN_HOTEL_CITY = "city";
    }
}
