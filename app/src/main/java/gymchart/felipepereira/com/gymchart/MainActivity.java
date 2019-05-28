package gymchart.felipepereira.com.gymchart;

import android.content.ContextWrapper;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import java.io.File;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(doesDatabaseExist(this, "GymChart")){
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    showHome();
                }
            }, 2000);

        }else {
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    showHome2();
                }
            }, 2000);


        }
    }

    private void showHome(){
        Intent intent = new Intent(MainActivity.this, HomeActivity.class);
        startActivity(intent);
        finish();
    }

    private void showHome2(){
        Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
        startActivity(intent);
        finish();
    }

    public static boolean doesDatabaseExist(ContextWrapper context, String dbName) {
        File dbFile = context.getDatabasePath(dbName);
        //if(dbFile.exists()) dbFile.delete();
        return dbFile.exists();
    }

}
