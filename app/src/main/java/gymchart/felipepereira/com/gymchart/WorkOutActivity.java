package gymchart.felipepereira.com.gymchart;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import Controller.DatabaseController;

public class WorkOutActivity extends AppCompatActivity {

    private DatabaseController dbController;
    private Cursor resultUser, resultList;
    ArrayList<String> exerciseName;
    ArrayList<String> exerciseType;
    ListView listViewExercise;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_work_out);

        loadListExercise();

        listViewExercise.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ShowScreen show = new ShowScreen();
                show.loadWithExtra(WorkOutActivity.this, WorkOutDetailsActivity.class, (String)parent.getItemAtPosition(position));
            }
        });
    }



    public void loadListExercise(){
        dbController = new DatabaseController(this);

        exerciseName = new ArrayList<>();
        exerciseType = new ArrayList<>();

        resultList  = dbController.returnListExercise();
        if(resultList.getCount() == 0){
            Toast.makeText(getApplicationContext(), "FUDEU", Toast.LENGTH_SHORT);
        }else{

            for(resultList.moveToFirst(); !resultList.isAfterLast(); resultList.moveToNext()) {
                // The Cursor is now set to the right position
                exerciseName.add(resultList.getString(1));
                //exerciseType.add(resultList.getString(2));
            }

            ArrayAdapter<String> itemsAdapter = new ArrayAdapter<>(this, android.R.layout.simple_expandable_list_item_1, exerciseName);

            listViewExercise = findViewById(R.id.listToWorkout);
            listViewExercise.setAdapter(itemsAdapter);
            listViewExercise.setMinimumWidth(100);
        }
    }
}
