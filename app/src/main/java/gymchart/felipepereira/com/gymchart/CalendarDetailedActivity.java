package gymchart.felipepereira.com.gymchart;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import Controller.DatabaseController;

public class CalendarDetailedActivity extends AppCompatActivity {

    private DatabaseController dbController;
    private Cursor resultList;
    private ArrayList<String> exerciseList;
    private ListView list_ExercisePerDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar_detailed);

        TextView txt_onDate = findViewById(R.id.onDate);
        txt_onDate.setText(getIntent().getStringExtra("dayOfMonth"));

        String dateString = "'" +txt_onDate.getText().toString()+ "'";

        System.out.println(dateString);

        dbController = new DatabaseController(getApplicationContext());

        resultList = dbController.returnExerciseDate(dateString);
        exerciseList = new ArrayList<>();

        if ((resultList.getCount()> 0)) {
            for(resultList.moveToFirst(); !resultList.isAfterLast(); resultList.moveToNext()){
                String exerciseName = resultList.getString(1);
                float weight = resultList.getFloat(2);
                int repetitions = resultList.getInt(3);
                int sets = resultList.getInt(4);
                String date = resultList.getString(5);

                exerciseList.add("Exercise: "+exerciseName+ "\nWeight: "+ weight + "\nRepetitions: " + repetitions + "\nSets: " + sets + " \nDate: "+date);
            }
            /*do{
                String exerciseName = resultList.getString(1);
                float weight = resultList.getFloat(2);
                int repetitions = resultList.getInt(3);
                int sets = resultList.getInt(4);

                exerciseList.add(exerciseName+ "/" + weight + "/" + repetitions + "/" + sets + "/" +resultList.getString(5));
            }while(resultList.isLast());*/

            ArrayAdapter<String> itemsAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, exerciseList);
            list_ExercisePerDate = findViewById(R.id.list_exercisePerDate);
            list_ExercisePerDate.setAdapter(itemsAdapter);
            list_ExercisePerDate.setMinimumWidth(100);
        }else{
            Toast.makeText(getApplicationContext(), "No Data", Toast.LENGTH_SHORT).show();
        }
    }

}
