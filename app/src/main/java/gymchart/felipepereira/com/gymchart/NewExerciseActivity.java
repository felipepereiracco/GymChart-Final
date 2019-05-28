package gymchart.felipepereira.com.gymchart;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;
import java.util.ArrayList;

import Controller.DatabaseController;

public class NewExerciseActivity extends AppCompatActivity {

    Spinner spinner_Category, spinner_name;
    Button btn_save, btn_cancel;
    ArrayAdapter arrayAdapterCategory, arrayAdapterName;
    SQLiteDatabase db;
    long result;

    ShowScreen screen = new ShowScreen();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_exercise);

        loadSpinners();

        btn_cancel = findViewById(R.id.cancel_add_exercise);
        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                screen.load(NewExerciseActivity.this, HomeActivity.class);
            }
        });


        btn_save = findViewById(R.id.save_exercise);
        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DatabaseController dbController = new DatabaseController(getBaseContext());

                spinner_name = findViewById(R.id.spinner_exercise_name);
                spinner_Category = findViewById(R.id.spinner_category);

                String exerciseName = spinner_name.getSelectedItem().toString();
                String exerciseCategory = spinner_Category.getSelectedItem().toString();

                result = dbController.createExercise(exerciseName, exerciseCategory);

                if (result != 0){
                    Toast.makeText(getApplicationContext(), "Saved", Toast.LENGTH_SHORT).show();
                    ShowScreen showScreen = new ShowScreen();
                    showScreen.load(NewExerciseActivity.this, HomeActivity.class);

                }else{
                    Toast.makeText(getApplicationContext(), "NotSaved", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    private void loadSpinners() {

        spinner_Category = findViewById(R.id.spinner_category);

        arrayAdapterCategory = ArrayAdapter.createFromResource(this, R.array.exercise_type, android.R.layout.simple_spinner_dropdown_item);
        spinner_Category.setAdapter(arrayAdapterCategory);

        spinner_Category.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                updateSpinner(spinner_Category);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void updateSpinner(Spinner spinner_Category){

            if(spinner_Category.getSelectedItem().toString().equalsIgnoreCase("Abdominals")) {
                spinner_name = findViewById(R.id.spinner_exercise_name);
                arrayAdapterName = ArrayAdapter.createFromResource(this, R.array.exercise_abdominals, android.R.layout.simple_spinner_dropdown_item);
                spinner_name.setAdapter(arrayAdapterName);
            } else if(spinner_Category.getSelectedItem().toString().equalsIgnoreCase("Back")) {
                spinner_name = findViewById(R.id.spinner_exercise_name);
                arrayAdapterName = ArrayAdapter.createFromResource(this, R.array.exercise_back, android.R.layout.simple_spinner_dropdown_item);
                spinner_name.setAdapter(arrayAdapterName);
            }else if(spinner_Category.getSelectedItem().toString().equalsIgnoreCase("Chest")) {
                spinner_name = findViewById(R.id.spinner_exercise_name);
                arrayAdapterName = ArrayAdapter.createFromResource(this, R.array.exercise_chest, android.R.layout.simple_spinner_dropdown_item);
                spinner_name.setAdapter(arrayAdapterName);
            }else if(spinner_Category.getSelectedItem().toString().equalsIgnoreCase("Legs")) {
                spinner_name = findViewById(R.id.spinner_exercise_name);
                arrayAdapterName = ArrayAdapter.createFromResource(this, R.array.exercise_legs, android.R.layout.simple_spinner_dropdown_item);
                spinner_name.setAdapter(arrayAdapterName);
            }else if(spinner_Category.getSelectedItem().toString().equalsIgnoreCase("Shoulders")) {
                spinner_name = findViewById(R.id.spinner_exercise_name);
                arrayAdapterName = ArrayAdapter.createFromResource(this, R.array.exercise_shoulder, android.R.layout.simple_spinner_dropdown_item);
                spinner_name.setAdapter(arrayAdapterName);
            }else if (spinner_Category.getSelectedItem().toString().equalsIgnoreCase("Biceps")) {
                spinner_name = findViewById(R.id.spinner_exercise_name);
                arrayAdapterName = ArrayAdapter.createFromResource(this, R.array.exercise_biceps, android.R.layout.simple_spinner_dropdown_item);
                spinner_name.setAdapter(arrayAdapterName);
            }else if (spinner_Category.getSelectedItem().toString().equalsIgnoreCase("Triceps")) {
                spinner_name = findViewById(R.id.spinner_exercise_name);
                arrayAdapterName = ArrayAdapter.createFromResource(this, R.array.exercise_triceps, android.R.layout.simple_spinner_dropdown_item);
                spinner_name.setAdapter(arrayAdapterName);
            }

    }

    public ArrayList<String> fillListView(){

        ArrayList<String> userExercises = new ArrayList<String>();
        userExercises.add(spinner_name.getSelectedItem().toString());
        return userExercises;
    }

}
