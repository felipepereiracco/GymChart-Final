package gymchart.felipepereira.com.gymchart;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Date;

import Controller.DatabaseController;

import static java.lang.Float.parseFloat;

public class WorkOutDetailsActivity extends AppCompatActivity {

    TextView txtExerciseNameWorkout;
    EditText editWeight, editSets, editRepetitions;
    Button btnSaveWorkout;
    long result;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_work_out_details);

        txtExerciseNameWorkout = findViewById(R.id.workOutExerciseName);
        txtExerciseNameWorkout.setText(getIntent().getStringExtra("parameter"));

        txtExerciseNameWorkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowScreen show = new ShowScreen();
                show.loadWithExtra(WorkOutDetailsActivity.this, WebSearchActivity.class, txtExerciseNameWorkout.getText().toString());
            }
        });

        editWeight = findViewById(R.id.edit_enter_weight);
        editSets = findViewById(R.id.edit_enter_sets);
        editRepetitions = findViewById(R.id.edit_enter_repetitions);

        btnSaveWorkout = findViewById(R.id.btn_save_workout);
        btnSaveWorkout.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                DatabaseController dbController = new DatabaseController(getBaseContext());
                result = dbController.createExerciseRecord(txtExerciseNameWorkout.getText().toString(), parseFloat(editWeight.getText().toString()), Integer.valueOf(editSets.getText().toString()), Integer.valueOf(editRepetitions.getText().toString()), getTodayDate().trim());

                if ((result != -1) && (result > 0)){
                    Toast.makeText(getApplicationContext(), "Saved", Toast.LENGTH_SHORT).show();
                    ShowScreen showScreen = new ShowScreen();
                    showScreen.load(WorkOutDetailsActivity.this, WorkOutActivity.class);

                }else{
                    Toast.makeText(getApplicationContext(), "NotSaved", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public String getTodayDate(){
        Date date = new Date();
        int day = date.getDate();
        int month = date.getMonth()+1;
        int year = date.getYear()+1900;
        
        String dateString = day+"/"+month+"/"+year;
        System.out.println();
        return  dateString;
    }

}
