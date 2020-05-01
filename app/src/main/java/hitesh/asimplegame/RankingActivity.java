package hitesh.asimplegame;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;

import java.util.List;

public class RankingActivity extends Activity {

    private List<Ranking> rankingList;
    private int rankingID = 0;

    private Ranking currentR;
    private TextView rank;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ranking);

        QuizDBOpenHelper db = new QuizDBOpenHelper(this);
        currentR = rankingList.get(rankingID);
        rank = (TextView) findViewById(R.id.rank1);
        setQuestionView();
    }

    private void setQuestionView() {
        // the method which will put all things together
        rank.setText(currentR.getSCORE());
        rankingID++;
    }


}
