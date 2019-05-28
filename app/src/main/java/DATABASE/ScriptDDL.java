package DATABASE;

public class ScriptDDL {

    private static StringBuilder sql;

    public static String getCreateTableUser(){

        sql = new StringBuilder();

        sql.append("CREATE TABLE users (");
        sql.append("userName TEXT UNIQUE NOT NULL, ");
        sql.append("userGender INTEGER UNIQUE NOT NULL, ");
        sql.append("userWeight INTEGER UNIQUE NOT NULL, ");
        sql.append("userHeight INTEGER UNIQUE NOT NULL, ");
        sql.append("userImage BLOB NOT NULL,");
        sql.append("userAge INTEGER UNIQUE NOT NULL, ");
        sql.append("userFat INTEGER UNIQUE NOT NULL);");

        return sql.toString();
    }

    public static String getCreateTableExercise(){

        sql = new StringBuilder();

        sql.append("CREATE TABLE exercises (");
        sql.append("exerciseID INTEGER PRIMARY KEY AUTOINCREMENT,");
        sql.append("exerciseName TEXT NOT NULL,");
        sql.append("exerciseType TEXT NOT NULL);");

        return sql.toString();
    }

   public static String getCreateTableUserTraining(){

        sql = new StringBuilder();

        sql.append("CREATE TABLE userTraining (");
        sql.append("userTrainingID INTEGER PRIMARY KEY AUTOINCREMENT,");
        sql.append("exerciseName TEXT NOT NULL,");
        sql.append("trainingWeight FLOAT NOT NULL,");
        sql.append("trainingRepetitions INT NOT NULL,");
        sql.append("trainingSets INT NOT NULL,");
        sql.append("trainingDate TEXT NOT NULL);");

        return sql.toString();
    }

}
