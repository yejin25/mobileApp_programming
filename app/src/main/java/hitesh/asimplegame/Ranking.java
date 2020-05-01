package hitesh.asimplegame;

import android.app.Activity;

public class Ranking extends Activity {
    private int ID;
    private String SCORE;

    public Ranking() {
        ID = 0;
        SCORE = "";
    }

    public Ranking(String score) {
        SCORE = score;
    }

    public int getID() {
        return ID;
    }

    public String getSCORE() {
        return SCORE;
    }

    public void setID(int id) {
        ID = id;
    }

    public void setSCORE(String score) {
        SCORE = score;
    }


}
