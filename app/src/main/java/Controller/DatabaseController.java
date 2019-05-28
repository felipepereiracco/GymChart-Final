package Controller;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import DATABASE.Connection;

public class DatabaseController {

    private Connection conn;
    private SQLiteDatabase db;
    long result;
    ContentValues values;
    Cursor cursor;
    String sql;

    public DatabaseController(Context context){
        conn = new Connection(context);
    }

    public long createUser(String name, int age, String gender, float weight, int height, float bodyFat, byte[] userImg){

        db = conn.getWritableDatabase();
        values = new ContentValues();
        values.put("userName", name);
        values.put("userGender", gender);
        values.put("userWeight", weight);
        values.put("userHeight", height);
        values.put("userImage", userImg);
        values.put("userAge", age);
        values.put("userFat", bodyFat);

        result = db.insert("users", null, values);
        return result;
    }

    public long createExercise(String exerciseName, String type){

        db = conn.getWritableDatabase();
        values = new ContentValues();
        values.put("exerciseName", exerciseName);
        values.put("exerciseType", type);
        result = db.insert("exercises", null, values);
        return result;
    }

    public long createExerciseRecord(String exerciseName, float weight, int sets, int reps, String date){

        db = conn.getWritableDatabase();
        values =  new ContentValues();
        values.put("exerciseName", exerciseName);
        values.put("trainingWeight", weight);
        values.put("trainingRepetitions", reps);
        values.put("trainingSets", sets);
        values.put("trainingDate", date);

        result = db.insert("userTraining", null, values);
        return result;
    }

    public Cursor returnData(){

        sql = "Select * from users";

        db = conn.getWritableDatabase();
        cursor = db.rawQuery(sql, null);
        return cursor;
    }

    public Cursor returnListExercise(){

        sql = "Select * from exercises";

        db = conn.getWritableDatabase();
        cursor =  db.rawQuery(sql, null);
        return cursor;
    }

    public Cursor returnExerciseDate(String date){
        db = conn.getWritableDatabase();
        cursor =  db.rawQuery("SELECT * FROM userTraining WHERE trainingDate = "+ date, null);
        System.out.println(cursor.getCount());
        cursor.moveToFirst();
        return cursor;
    }

    public Cursor returnExerciseDetails(String name){

        db = conn.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM userTraining WHERE exerciseName = "+name, null);
        System.out.println(cursor.getCount());
        return cursor;
    }

}
