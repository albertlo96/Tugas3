import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Albert on 20/11/2016.
 */

public class WeatherDB extends SQLiteOpenHelper {
    //database values
    private static final String DATABASE_NAME      = "App.db";
    private static final int DATABASE_VERSION      = 3;
    private static final String TABLE_WEATHER = "weather";
    private static final String COLUMN_TEMP = "temperature";
    private static final String COLUMN_LOC = "location";
    private static final String DB_TABLE_CREATE = "CREATE TABLE " + TABLE_WEATHER +
            " (id INTEGER PRIMARY KEY, " + COLUMN_TEMP +" REAL, "+
            COLUMN_LOC + " TEXT)";

    public WeatherDB(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(DB_TABLE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldver, int newver) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_WEATHER);
        onCreate(sqLiteDatabase);
    }

    public void insertCuaca(double suhu, String lokasi){
        ContentValues CV = new ContentValues();
        CV.put(COLUMN_TEMP, suhu);
        CV.put(COLUMN_LOC, lokasi);
        this.getWritableDatabase().insert(TABLE_WEATHER, null, CV);
    }

    public Cursor getCuaca(){
        String query = "SELECT * FROM "+TABLE_WEATHER;
        return this.getReadableDatabase().rawQuery(query, null);
    }
}
