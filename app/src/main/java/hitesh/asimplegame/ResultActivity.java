package hitesh.asimplegame;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class ResultActivity extends Activity {
	public static int score;
	private static String scoreValue;
	private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
	private DatabaseReference databaseReference = firebaseDatabase.getReference();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_result);

		Button btnRestart = (Button)findViewById(R.id.btn_playagain);
		Button btnMain = (Button)findViewById(R.id.btn_mainbutton);

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
		btnMain.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if (QuestionActivity.questionActivityLevel.equals("easy")) {
					Intent intent = new Intent(getApplicationContext(), SignedActivity.class);
					startActivity(intent);
				} else if (QuestionActivity.questionActivityLevel.equals("hard")) {
					Intent intent = new Intent(getApplicationContext(), SignedActivity.class);
					startActivity(intent);
				}
			}
		});


		TextView textResult = (TextView) findViewById(R.id.textResult);
		Bundle b = getIntent().getExtras();
		score = b.getInt("score");
		if (b.getInt("score") < 10) {
			scoreValue = "0".concat(String.valueOf(b.getInt("score")));
			System.out.println(scoreValue);
		} else {
			scoreValue = String.valueOf(b.getInt("score"));
			System.out.println(scoreValue);
		}
		textResult.setText("Your score is " + " " + score + "."+"\n" + "Thanks for playing my game.");

		Integer intUnixTime = (int) System.currentTimeMillis();
		FirebaseData firebaseData = new FirebaseData(LoginData.firebaseAuth.getCurrentUser().getEmail(), LoginData.firebaseAuth.getCurrentUser().getDisplayName(), scoreValue);
		databaseReference.child(String.valueOf(QuestionActivity.questionActivityLevel)).child(String.valueOf(intUnixTime)).setValue(firebaseData);


		//		score = b.getInt("score");
//        textResult.setText("Your score is " + " " + score + "."+"\n" + "Thanks for playing my game.");
//
//		Integer intUnixTime = (int) System.currentTimeMillis();
//		FirebaseData firebaseData = new FirebaseData(LoginData.firebaseAuth.getCurrentUser().getEmail(), LoginData.firebaseAuth.getCurrentUser().getDisplayName(), String.valueOf(score));
//		databaseReference.child(String.valueOf(QuestionActivity.questionActivityLevel)).child(String.valueOf(intUnixTime)).setValue(firebaseData);
	}


}