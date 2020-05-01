/*
package hitesh.asimplegame;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.net.Inet4Address;

public class ResultHardActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_result);

        TextView textResult = (TextView) findViewById(R.id.textResult);
        Bundle b = getIntent().getExtras();
        int score = b.getInt("score");
        textResult.setText("Your score is " + " " + score + ". Thanks for playing my game.");
    }
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(this,SelectLevel.class);
        startActivity(intent);
    }

    public void playagain(View o) {
        QuizDBOpenHelper.setDatabaseRandom();
        Intent intent = new Intent(this, QuestionActivity.class);
        intent.putExtra("level", "hard");
        startActivity(intent);
    }
}
*/
