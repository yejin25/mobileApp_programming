package hitesh.asimplegame;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.telephony.mbms.MbmsErrors;
import android.util.Log;

public class QuizDBOpenHelper extends SQLiteOpenHelper {

    private static int DATABASE_VERSION = 2;
    // Database Name
    private static final String DATABASE_NAME = "mathsone";
    // tasks table name
    private static final String TABLE_QUEST = "quest";
    private static final String TABLE_HARD = "hard";
    // tasks Table Columns names
    private static final String KEY_ID = "qid";
    private static final String KEY_QUES = "question";
    private static final String KEY_ANSWER = "answer"; // correct option
    private static final String KEY_OPTA = "opta"; // option a
    private static final String KEY_OPTB = "optb"; // option b
    private static final String KEY_OPTC = "optc"; // option c

    private SQLiteDatabase database;

    public QuizDBOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d("TAG", "Called!");

        database = db;
        String sql = "CREATE TABLE IF NOT EXISTS " + TABLE_QUEST + " ( "
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + KEY_QUES
                + " TEXT, " + KEY_ANSWER + " TEXT, " + KEY_OPTA + " TEXT, "
                + KEY_OPTB + " TEXT, " + KEY_OPTC + " TEXT)";
        db.execSQL(sql);
        String sql2 = "CREATE TABLE IF NOT EXISTS " + TABLE_HARD + " ( "
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + KEY_QUES
                + " TEXT, " + KEY_ANSWER + " TEXT, " + KEY_OPTA + " TEXT, "
                + KEY_OPTB + " TEXT, " + KEY_OPTC + " TEXT)";
        db.execSQL(sql2);
        addQuestion();
        addHardQuestion();

    }

    private void HardQuestion(){
        for(int i=0; i< 20; i++) {
            int ans;
            int A = (int) Math.random() * 20 + 1;
           int B = (int) Math.random() * 20 + 1;
            int C = (int) Math.random() * 20 + 1;
            int D = (int) Math.random() * 20 + 1;
            int randomNum = (int) Math.random() * 2 + 1;
            int randomAns = (int) Math.random() * 3 + 1;
            int optA = 0;
            int optB = 0;
            int optC = 0;
            Question q;

            String stringQ;
            switch (randomNum) {
                case 1: //연산 2개
                    switch (randomNum) {
                        case 1:
                            stringQ = (String.valueOf(A) + "x" + String.valueOf(B) + "+" + String.valueOf(C) + "= ?");
                            ans = A * B + C;
                            switch (randomAns) {
                                case 1:
                                    optA = ans;
                                    optB = A * B + C - 2;
                                    optC = A * B + C + 6;
                                    break;
                                case 2:
                                    optA = A * B + C + 7;
                                    optB = ans;
                                    optC = A * B + C - 1;
                                    break;
                                case 3:
                                    optA = A * B + C + 1;
                                    optB = A * B + C - 1;
                                    optC = ans;
                                    break;

                            }
                            q = new Question(stringQ, String.valueOf(optA), String.valueOf(optB), String.valueOf(optC), String.valueOf(ans));
                            addHardQuestion(q);
                            Log.d("TAG", stringQ);
                            break;
                        case 2:
                            stringQ = (String.valueOf(A) + "+" + String.valueOf(B) + "÷" + String.valueOf(C) + "= ?");
                            ans = A + B / C;
                            switch (randomAns) {
                                case 1:
                                    optA = ans;
                                    optB = A + B / C - 2;
                                    optC = A + B / C + 6;
                                    break;
                                case 2:
                                    optA = A + B / C - 4;
                                    optB = ans;
                                    optC = A + B / C + 3;
                                    break;
                                case 3:
                                    optA = A + B / C + 1;
                                    optB = A + B / C - 1;
                                    optC = ans;
                                    break;

                            }
                            q = new Question(stringQ, String.valueOf(optA), String.valueOf(optB), String.valueOf(optC), String.valueOf(ans));
                            addHardQuestion(q);
                            Log.d("TAG", stringQ);
                            break;
                    }
                case 2://연산 3개
                    switch (randomNum) {
                        case 1:
                            stringQ = (String.valueOf(A) + "x" + "(" + String.valueOf(B) + "+" + String.valueOf(C) + ")" + "-" + String.valueOf(D) + "= ?");
                            ans = A * (B + C) - D;
                            switch (randomAns) {
                                case 1:
                                    optA = ans;
                                    optB = A * (B + C) - D + 1;
                                    optC = A * (B + C) - D - 1;
                                    break;
                                case 2:
                                    optA = A * (B + C) - D - 2;
                                    optB = ans;
                                    optC = A * (B + C) - D + 3;
                                    break;
                                case 3:
                                    optA = A * (B + C) - D - 4;
                                    optB = A * (B + C) - D + 8;
                                    optC = ans;
                                    break;

                            }
                            q = new Question(stringQ, String.valueOf(optA), String.valueOf(optB), String.valueOf(optC), String.valueOf(ans));
                            addHardQuestion(q);
                            Log.d("TAG", stringQ);
                            break;
                        case 2:
                            stringQ = (String.valueOf(A) + "+" + String.valueOf(B) + "÷" + String.valueOf(C) + "-" + String.valueOf(D) + "= ?");
                            ans = A + B / C - D;
                            switch (randomAns) {
                                case 1:
                                    optA = ans;
                                    optB = A + B / C - D - 1;
                                    optC = A + B / C - D + 1;
                                    break;
                                case 2:
                                    optA = A + B / C - D - 2;
                                    optB = ans;
                                    optC = A + B / C - D + 3;
                                    break;
                                case 3:
                                    optA = A + B / C - D + 5;
                                    optB = A + B / C - D - 4;
                                    optC = ans;
                                    break;
                            }
                            q = new Question(stringQ, String.valueOf(optA), String.valueOf(optB), String.valueOf(optC), String.valueOf(ans));
                            addHardQuestion(q);
                            Log.d("TAG", stringQ);
                            break;

                    }

                    break;

            }
        }

                }


    private void addHardQuestion() {
        for (int i = 0; i < 21; i++) {
            int questionNumA = (int) (Math.random() * 10+1);
            int questionNumB = (int) (Math.random() * 10+1);
            int questionNumC = (int) (Math.random() * 10+1);

            int questionAnswer = 0;
            int questionOption = (int) (Math.random() * 4);

            int optA = 0, optB = 0, optC = 0;
            int optSelectionRandom = 0;

            String questionString;
            Question question;

            switch (questionOption) { // +-*/
                case 0: // +
                    questionAnswer = questionNumA + questionNumB * questionNumC;
                    optSelectionRandom = (int) (Math.random() * 3);

                    questionString = (String.valueOf(questionNumA) + " + " + String.valueOf(questionNumB) + "x" + String.valueOf(questionNumC)+ " =?");

                    switch (optSelectionRandom) {
                        case 0:
                            optA = questionNumA + questionNumB * questionNumC;
                            optB = (int) (Math.random() * 10);
                            optC = (int) (Math.random() * 10);
                            break;
                        case 1:
                            optA = (int) (Math.random() * 10);
                            optB = questionNumA + questionNumB * questionNumC;
                            optC = (int) (Math.random() * 10);
                            break;
                        case 2:
                            optA = (int) (Math.random() * 10);
                            optB = (int) (Math.random() * 10);
                            optC = questionNumA + questionNumB * questionNumC;
                            break;
                        default:
                            break;
                    }

                    question = new Question(questionString, String.valueOf(optA), String.valueOf(optB), String.valueOf(optC), String.valueOf(questionAnswer));
                    addHardQuestion(question);

                    break;
                case 1: // -

                    questionAnswer = questionNumA - questionNumB / questionNumC;
                    optSelectionRandom = (int) (Math.random() * 3);

                    questionString = (String.valueOf(questionNumA) + " - " + String.valueOf(questionNumB) + "÷ "+String.valueOf(questionNumC)+ " =?");

                    switch (optSelectionRandom) {
                        case 0:
                            optA = questionNumA - questionNumB / questionNumC;
                            optB = (int) (Math.random() * 10);
                            optC = (int) (Math.random() * 10);
                            break;
                        case 1:
                            optA = (int) (Math.random() * 10);
                            optB = questionNumA - questionNumB / questionNumC;
                            optC = (int) (Math.random() * 10);
                            break;
                        case 2:
                            optA = (int) (Math.random() * 10);
                            optB = (int) (Math.random() * 10);
                            optC = questionNumA - questionNumB /questionNumC;
                            break;
                        default:
                            break;
                    }

                    question = new Question(questionString, String.valueOf(optA), String.valueOf(optB), String.valueOf(optC), String.valueOf(questionAnswer));
                    addHardQuestion(question);

                    break;
                case 2: // *
                    questionAnswer = questionNumA * questionNumB / questionNumC;
                    optSelectionRandom = (int) (Math.random() * 3);

                    questionString = (String.valueOf(questionNumA) + " * " + String.valueOf(questionNumB) + "÷ "+ String.valueOf(questionNumC) + " =?");

                    switch (optSelectionRandom) {
                        case 0:
                            optA = questionNumA * questionNumB / questionNumC;
                            optB = (int) (Math.random() * 10);
                            optC = (int) (Math.random() * 10);
                            break;
                        case 1:
                            optA = (int) (Math.random() * 10);
                            optB = questionNumA * questionNumB /questionNumC;
                            optC = (int) (Math.random() * 10);
                            break;
                        case 2:
                            optA = (int) (Math.random() * 10);
                            optB = (int) (Math.random() * 10);
                            optC = questionNumA * questionNumB /questionNumC;
                            break;
                        default:
                            break;
                    }

                    question = new Question(questionString, String.valueOf(optA), String.valueOf(optB), String.valueOf(optC), String.valueOf(questionAnswer));
                    addHardQuestion(question);

                    break;
                case 3: // /
                    questionAnswer = questionNumA - questionNumB + questionNumC;
                    optSelectionRandom = (int) (Math.random() * 3);

                    questionString = (String.valueOf(questionNumA) + " - " + String.valueOf(questionNumB) + "+" + String.valueOf(questionNumC)+" =?");

                    switch (optSelectionRandom) {
                        case 0:
                            optA = questionNumA - questionNumB + questionNumC;
                            optB = (int) (Math.random() * 10);
                            optC = (int) (Math.random() * 10);
                            break;
                        case 1:
                            optA = (int) (Math.random() * 10);
                            optB = questionNumA - questionNumB + questionNumC;
                            optC = (int) (Math.random() * 10);
                            break;
                        case 2:
                            optA = (int) (Math.random() * 10);
                            optB = (int) (Math.random() * 10);
                            optC = questionNumA - questionNumB + questionNumC;
                            break;
                        default:
                            break;
                    }

                    question = new Question(questionString, String.valueOf(optA), String.valueOf(optB), String.valueOf(optC), String.valueOf(questionAnswer));
                    addHardQuestion(question);

                    break;
            }

        }
    }

    private void addQuestion() {
        Question q1 = new Question("5+2 = ?", "7", "8", "6", "7");
        addQuestion(q1);
        Question q2 = new Question("2+18 = ?", "18", "19", "20", "20");
        addQuestion(q2);
        Question q3 = new Question("10-3 = ?", "6", "7", "8", "7");
        addQuestion(q3);
        Question q4 = new Question("5+7 = ?", "12", "13", "14", "12");
        addQuestion(q4);
        Question q5 = new Question("3-1 = ?", "1", "3", "2", "2");
        addQuestion(q5);
        Question q6 = new Question("0+1 = ?", "1", "0", "10", "1");
        addQuestion(q6);
        Question q7 = new Question("9-9 = ?", "0", "9", "1", "0");
        addQuestion(q7);
        Question q8 = new Question("3+6 = ?", "8", "7", "9", "9");
        addQuestion(q8);
        Question q9 = new Question("1+5 = ?", "6", "7", "5", "6");
        addQuestion(q9);
        Question q10 = new Question("7-5 = ?", "3", "2", "6", "2");
        addQuestion(q10);
        Question q11 = new Question("7-2 = ?", "7", "6", "5", "5");
        addQuestion(q11);
        Question q12 = new Question("3+5 = ?", "8", "7", "5", "8");
        addQuestion(q12);
        Question q13 = new Question("0+6 = ?", "7", "6", "5", "6");
        addQuestion(q13);
        Question q14 = new Question("12-10 = ?", "1", "2", "3", "2");
        addQuestion(q14);
        Question q15 = new Question("12+2 = ?", "14", "15", "16", "14");
        addQuestion(q15);
        Question q16 = new Question("2-1 = ?", "2", "1", "0", "1");
        addQuestion(q16);
        Question q17 = new Question("6-6 = ?", "6", "12", "0", "0");
        addQuestion(q17);
        Question q18 = new Question("5-1 = ?", "4", "3", "2", "4");
        addQuestion(q18);
        Question q19 = new Question("4+2 = ?", "6", "7", "5", "6");
        addQuestion(q19);
        Question q20 = new Question("5+1 = ?", "6", "7", "5", "6");
        addQuestion(q20);
        Question q21 = new Question("5-4 = ?", "5", "4", "1", "1");
        addQuestion(q21);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldV, int newV) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_QUEST);
        // Create tables again
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_HARD);
        onCreate(db);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int newV, int oldV) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_QUEST);
        // Create tables again
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_HARD);
        onCreate(db);
    }


    // Adding new question
    public void addQuestion(Question quest) {
        // SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_QUES, quest.getQUESTION());
        values.put(KEY_ANSWER, quest.getANSWER());
        values.put(KEY_OPTA, quest.getOPTA());
        values.put(KEY_OPTB, quest.getOPTB());
        values.put(KEY_OPTC, quest.getOPTC());

        // Inserting Row
        database.insert(TABLE_QUEST, null, values);
    }

    public void addHardQuestion(Question hard) {
        ContentValues values = new ContentValues();
        values.put(KEY_QUES, hard.getQUESTION());
        values.put(KEY_ANSWER, hard.getANSWER());
        values.put(KEY_OPTA, hard.getOPTA());
        values.put(KEY_OPTB, hard.getOPTB());
        values.put(KEY_OPTC, hard.getOPTC());

        // Inserting Row
        database.insert(TABLE_HARD, null, values);
    }

    public List<Question> getAllQuestions() {
        List<Question> quesList = new ArrayList<Question>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_QUEST;
        database = this.getReadableDatabase();
        Cursor cursor = database.rawQuery(selectQuery, null);
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Question quest = new Question();
                quest.setID(cursor.getInt(0));
                quest.setQUESTION(cursor.getString(1));
                quest.setANSWER(cursor.getString(2));
                quest.setOPTA(cursor.getString(3));
                quest.setOPTB(cursor.getString(4));
                quest.setOPTC(cursor.getString(5));

                quesList.add(quest);
            } while (cursor.moveToNext());
        }
        // return quest list
        return quesList;
    }

    public void oState() {
        List<hitesh.asimplegame.Question> quesList = new ArrayList<hitesh.asimplegame.Question>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_HARD;
        database = this.getReadableDatabase();
        Cursor cursor = database.rawQuery(selectQuery, null);
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                hitesh.asimplegame.Question hard = new hitesh.asimplegame.Question();
                hard.setID(cursor.getInt(0));
                hard.setQUESTION(cursor.getString(1));
                hard.setANSWER(cursor.getString(2));
                hard.setOPTA(cursor.getString(3));
                hard.setOPTB(cursor.getString(4));
                hard.setOPTC(cursor.getString(5));

                quesList.add(hard);
            } while (cursor.moveToNext());
        }
        // return quest list
        //return quesList;
    }


    public List<Question> getAllHardQuestions() {
        List<Question> quesList = new ArrayList<Question>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_HARD;
        database = this.getReadableDatabase();
        Cursor cursor = database.rawQuery(selectQuery, null);
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Question quest = new Question();
                quest.setID(cursor.getInt(0));
                quest.setQUESTION(cursor.getString(1));
                quest.setANSWER(cursor.getString(2));
                quest.setOPTA(cursor.getString(3));
                quest.setOPTB(cursor.getString(4));
                quest.setOPTC(cursor.getString(5));

                quesList.add(quest);
            } while (cursor.moveToNext());
        }
        // return quest list
        return quesList;
    }

    public static void setDatabaseRandom() {
        if (DATABASE_VERSION == 1) {
            DATABASE_VERSION = 2;
        } else {
            DATABASE_VERSION = 1;
        }
    }
}
