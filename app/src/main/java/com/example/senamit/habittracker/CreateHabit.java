package com.example.senamit.habittracker;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.senamit.habittracker.data.HabitContract.*;
import com.example.senamit.habittracker.data.HabitTrackerDbHelper;

public class CreateHabit extends AppCompatActivity {

    HabitTrackerDbHelper habitTrackerDbHelper;
    SQLiteDatabase db;

    String habitName= null;
    ContentValues contentValues = new ContentValues();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_habit);


        final EditText edtHabitName = (EditText)findViewById(R.id.edt_habit_name);
        Button btnSubmit = (Button)findViewById(R.id.btn_submit);



         habitTrackerDbHelper = new HabitTrackerDbHelper(CreateHabit.this);
         db = habitTrackerDbHelper.getWritableDatabase();





        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                insertData();


             Toast.makeText(CreateHabit.this, "The habit name is "+ habitName, Toast.LENGTH_LONG).show();

                finish();

            }

            private void insertData() {

                habitName =   edtHabitName.getText().toString().trim();
                contentValues.put(Habitentry.COLUMN_HABIT_NAME, habitName);
                contentValues.put(Habitentry.COLUMN_NUMBER_OF_DAYS, "20 ");
             long RowId =    db.insert(Habitentry.TABLE_NAME, null, contentValues);

                if (RowId == -1){
                    Toast.makeText(CreateHabit.this, "unsuccessful", Toast.LENGTH_SHORT).show();

                }
                else {
                    Toast.makeText(CreateHabit.this, "successful", Toast.LENGTH_SHORT).show();

                }
            }
        });
    }
}
