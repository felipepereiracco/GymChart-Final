package gymchart.felipepereira.com.gymchart;

import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.BarGraphSeries;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.ArrayList;

import Controller.DatabaseController;

public class GraphActivity extends AppCompatActivity {

    Cursor cursor;
    ArrayList<Integer> graphData;
    //LineGraphSeries<DataPoint> series;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph);

        GraphView graph = findViewById(R.id.graph);

        DatabaseController dbController = new DatabaseController(getApplicationContext());
        cursor = dbController.returnExerciseDetails("'"+getIntent().getStringExtra("parameter")+"'");
        graphData = new ArrayList<>();

        if((cursor.getCount() != -1) && (cursor.getCount() > 0)){
            for(cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()){
                int exerciseWeight = cursor.getInt(2);
                System.out.println(exerciseWeight);
                graphData.add(exerciseWeight);
            }

            BarGraphSeries<DataPoint> series = new BarGraphSeries<>();
            for (int i = 0; i < graphData.size(); i++) {
                DataPoint point = new DataPoint(i , graphData.get(i));
                series.appendData(point, true, graphData.size());
                series.setDrawValuesOnTop(true);
                series.setValuesOnTopColor(Color.YELLOW);

            }
            graph.addSeries(series);
            graph.getViewport().setScalable(true);
            graph.getViewport().setScalableY(true);

        }else{
            Toast.makeText(getApplicationContext(), "No data", Toast.LENGTH_LONG).show();
        }


    }
}