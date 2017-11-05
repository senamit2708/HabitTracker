package com.example.senamit.habittracker.data;

import android.provider.BaseColumns;

/**
 * Created by senamit on 4/11/17.
 */

public class HabitContract {

    //its private so that no one can use it outside
    private HabitContract() {
    }


    //inner class for each table... that defines the table contents
    public static class Habitentry implements BaseColumns {

        public static final String TABLE_NAME = "Entry";
        public static final String COLUMN_HABIT_NAME = "HabitName";
        public static final String COLUMN_NUMBER_OF_DAYS = "NoOfDays";


    }
}
