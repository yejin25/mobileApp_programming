package hitesh.asimplegame;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class ResultActivity extends Activity {
	public static int score;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_result);

		Button btnRestart = (Button)findViewById(R.id.btn_playagain);

		btnRestart.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if (QuestionActivity.questionActivityLevel.equals("easy")) {
					QuizDBOpenHelper.setDatabaseRandom();
					Intent intent = new Intent(getApplicationContext(), QuestionActivity.class);
					intent.putExtra("level", "easy");
					startActivity(intent);
				} else if (QuestionActivity.questionActivityLevel.equals("hard")) {
					QuizDBOpenHelper.setDatabaseRandom();
					Intent intent = new Intent(getApplicationContext(), QuestionActivity.class);
					intent.putExtra("level", "hard");
					startActivity(intent);
				}
			}
		});

		TextView textResult = (TextView) findViewById(R.id.textResult);
		Bundle b = getIntent().getExtras();
		score = b.getInt("score");
        textResult.setText("Your score is " + " " + score + "."+"\n" + "Thanks for playing my game.");
	}

	public static int getResult(){
		return score;
	}

	/*public void onClick(View o) {
		QuizDBOpenHelper.setDatabaseRandom();
		Intent intent = new Intent(this, QuestionActivity.class);
		intent.putExtra("level", "easy");
		startActivity(intent);
	}*/

}