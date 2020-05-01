package hitesh.asimplegame;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class RankingDBOpenHelper extends SQLiteOpenHelper {
    private static int DATABASE_VERSION = 1;
    // Database Name
    private static final String DATABASE_NAME = "ranking";
    // tasks table name
    private static final String TABLE_RANK = "rank";
    // tasks Table Columns names
    private static final String KEY_ID = "sid";
    private static final String KEY_SCORE = "score";

    private SQLiteDatabase database;

    public RankingDBOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d("TAG", "Called!");

        database = db;
        String sql3 = "CREATE TABLE IF NOT EXISTS " + TABLE_RANK + " ( "
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + KEY_SCORE
                + " TEXT)";
        db.execSQL(sql3);
        addScore();

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldV, int newV) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_RANK);
        // Create tables again
        onCreate(db);
    }
    public void addScore(){
        Ranking r = new Ranking(String.valueOf(ResultActivity.getResult()));
        addScore(r);
    }

    public void addScore(Ranking score) {
        ContentValues values = new ContentValues();
        values.put(KEY_SCORE, score.getSCORE());

        database.insert(TABLE_RANK, null, values);
    }

    public List<Ranking> getAllQuestions() {
        List<Ranking> rankList = new ArrayList<Ranking>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_RANK;
        database = this.getReadableDatabase();
        Cursor cursor = database.rawQuery(selectQuery, null);
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Ranking rank = new Ranking();
                rank.setID(cursor.getInt(0));
                rank.setSCORE(cursor.getString(1));

                rankList.add(rank);
            } while (cursor.moveToNext());
        }
        // return quest list
        return rankList;
    }
}
