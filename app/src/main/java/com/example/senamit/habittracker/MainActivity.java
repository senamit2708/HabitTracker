package com.example.senamit.habittracker;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.senamit.habittracker.data.HabitTrackerDbHelper;
import com.example.senamit.habittracker.data.HabitContract.*;

public class MainActivity extends AppCompatActivity {

    HabitTrackerDbHelper habitTrackerDbHelper;
    SQLiteDatabase db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        FloatingActionButton fabButton = (FloatingActionButton) findViewById(R.id.fab_button);

        fabButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this, CreateHabit.class);
                startActivity(intent);
            }
        });
        habitTrackerDbHelper = new HabitTrackerDbHelper(MainActivity.this);







    }

    @Override
    protected void onStart() {
        super.onStart();
        displayDatabaseInfo();
    }

    private void displayDatabaseInfo() {

        db = habitTrackerDbHelper.getReadableDatabase();

        String[] projection = {Habitentry._ID,Habitentry.COLUMN_HABIT_NAME, Habitentry.COLUMN_NUMBER_OF_DAYS};

        Cursor cursor = db.query(
                Habitentry.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                null);


        TextView displayHabit = (TextView)findViewById(R.id.display_habit);

        try{


        displayHabit.setText("the number of rows is "+ cursor.getCount());

//        displayHabit.append(Habitentry._ID+" - "+
//            Habitentry.COLUMN_HABIT_NAME+" - "+
//            Habitentry.COLUMN_NUMBER_OF_DAYS + "\n");

        int idColumnIndex = cursor.getColumnIndex(Habitentry._ID);
        int habitNameColumnIndex = cursor.getColumnIndex(Habitentry.COLUMN_HABIT_NAME);
        int numberOfDaysColumnIndex = cursor.getColumnIndex(Habitentry.COLUMN_NUMBER_OF_DAYS);


        while (cursor.moveToNext()) {
            int currentID = cursor.getInt(idColumnIndex);
            String currentHabitName = cursor.getString(habitNameColumnIndex);
            String currentNumberOfDays = cursor.getString(numberOfDaysColumnIndex);

            displayHabit.append(("\n" + currentID + " - " +
                    currentHabitName + " - " +
                    currentNumberOfDays));
        }
        }finally{
            cursor.close();
        }
    }

}
