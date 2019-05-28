package gymchart.felipepereira.com.gymchart;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Debug;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.CalendarView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayInputStream;
import java.util.Date;

import Controller.DatabaseController;

public class CalendarActivity extends AppCompatActivity {
    DatabaseController dbController;
    Cursor resultUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);
        
        CalendarView calendarView = findViewById(R.id.calendar);
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                Intent intent = new Intent(CalendarActivity.this, CalendarDetailedActivity.class);
                String message = (dayOfMonth+"/"+(month+1)+"/"+year);
                intent.putExtra("dayOfMonth", message);
                startActivity(intent);

            }
        });
    }
}
