package gymchart.felipepereira.com.gymchart;

import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import java.io.ByteArrayInputStream;
import java.util.ArrayList;

import Controller.DatabaseController;

public class HomeActivity extends AppCompatActivity {

    private DatabaseController dbController;
    private Cursor resultUser, resultList;
    ArrayList<String> exerciseName;
    ArrayList<String> exerciseType;
    ListView listExercise;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        /////////////////--------------------------------------------------------------------
        loadListExercise();

        /////////////////////////////////////////////////////////////////////////////////////

        TextView name = findViewById(R.id.name);
        TextView age = findViewById(R.id.age);
        ImageView userProfile = findViewById(R.id.user_profile);
        final Cursor resultUser = dbController.returnData();
        if(resultUser.getCount() == 0){
            Toast.makeText(getApplicationContext(), "No data", Toast.LENGTH_LONG).show();
        }else{
            while(resultUser.moveToNext()) {
                name.setText(resultUser.getString(0));
                age.setText(resultUser.getString(5));
                //------------------------------------------------
                byte[] img = resultUser.getBlob(4);
                ByteArrayInputStream imageStream = new ByteArrayInputStream(img);
                Bitmap bitmap = BitmapFactory.decodeStream(imageStream);
                userProfile.setImageBitmap(bitmap);
                //------------------------------------------------
            }
        }
//button workOut-----------------------------------------------------------

        Button btnWorkOut = findViewById(R.id.btn_workOut);
        btnWorkOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowScreen show = new ShowScreen();
                show.load(HomeActivity.this, WorkOutActivity.class);
            }
        });

//button calendar ---------------------------------------------------------
        Button btn_calendar = findViewById(R.id.btn_calendar);
        btn_calendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowScreen show = new ShowScreen();
                show.load(HomeActivity.this, CalendarActivity.class);
            }
        });
//---------------------------------------------------------------------------

// button add new exercise -------------------------------------------------------------
        Button btn_add = findViewById(R.id.btn_add);
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowScreen show = new ShowScreen();
                        show.load(HomeActivity.this, NewExerciseActivity.class);
            }
        });
////////////////////////////////////////////////////////////////////////////////////////
    }

    public void loadListExercise(){
        dbController = new DatabaseController(this);

        exerciseName = new ArrayList<>();
        exerciseType = new ArrayList<>();

        resultList  = dbController.returnListExercise();
        if(resultList.getCount() == 0){
            Toast.makeText(getApplicationContext(), "No data", Toast.LENGTH_SHORT);
        }else{

            for(resultList.moveToFirst(); !resultList.isAfterLast(); resultList.moveToNext()) {
                // The Cursor is now set to the right position
                exerciseName.add(resultList.getString(1));
                exerciseType.add(resultList.getString(2));
            }

            ArrayAdapter<String> itemsAdapter = new ArrayAdapter<>(this, android.R.layout.simple_expandable_list_item_1, exerciseName);
            listExercise = findViewById(R.id.list_Exercise);
            listExercise.setAdapter(itemsAdapter);
            listExercise.setMinimumWidth(100);

            listExercise.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    ShowScreen show = new ShowScreen();
                    show.loadWithExtra(HomeActivity.this, GraphActivity.class, (String)parent.getItemAtPosition(position));
                }
            });
        }
    }

}
