package DATABASE;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Connection extends SQLiteOpenHelper {

    public Connection(Context context) {
        super(context, "GymChart", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(ScriptDDL.getCreateTableUser());
        db.execSQL(ScriptDDL.getCreateTableExercise());
        db.execSQL(ScriptDDL.getCreateTableUserTraining());
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
