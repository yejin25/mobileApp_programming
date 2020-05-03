package hitesh.asimplegame;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

public class MyPageActivity extends Activity {
    private TextView high;
    private TextView hardhigh;
    private TextView avgtxt;
    private TextView avghard;

    private static int easyAvg = 0;
    private static int hardAvg = 0;
    private static int easyHighest = 0;
    private static int hardHighest = 0;
    private static int sum = 0;
    private static int hardSum = 0;
    private static int avg = 0;
    private static int count = 0;
    private static int hardCount = 0;
    private static int total = 0;

    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference easyDatabaseRef = firebaseDatabase.getReference("easy");
    private DatabaseReference hardDatabaseRef = firebaseDatabase.getReference("hard");

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_mypage);
        getEasyScores();
        getHardScores();

        high = (TextView)findViewById(R.id.highscore);
        hardhigh = (TextView)findViewById(R.id.hardhighscore);
        avgtxt = (TextView)findViewById(R.id.avgscore);
        avghard = (TextView)findViewById(R.id.hardavgscore);


    }

    public void mOnClose(View v){
        finish();
    }


    private void getEasyScores() {
        easyDatabaseRef.orderByChild("userEmail").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot pos : dataSnapshot.getChildren()) {
                    if (String.valueOf(pos.getValue()).contains(LoginData.firebaseAuth.getCurrentUser().getEmail())) {
                        int a = Integer.parseInt(String.valueOf(pos.child("userScore").getValue()));
                        Log.d("TAG", String.valueOf(a));
                        if (easyHighest < a) {
                            easyHighest = a;
                        }
                        sum = sum + a;
                        count++;
                    }
                }
                easyAvg = sum/count;
                high.setText(String.valueOf(easyHighest));
                avgtxt.setText(String.valueOf(easyAvg));
                Log.d("TAGG", String.valueOf(easyHighest));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void getHardScores() {
        hardDatabaseRef.orderByChild("userEmail").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot pos : dataSnapshot.getChildren()) {
                    if (String.valueOf(pos.getValue()).contains(LoginData.firebaseAuth.getCurrentUser().getEmail())) {
                        int a = Integer.parseInt(String.valueOf(pos.child("userScore").getValue()));
                        Log.d("TAG", String.valueOf(a));
                        if (hardHighest < a) {
                            hardHighest = a;
                        }
                        hardSum = hardSum + a;
                        hardCount++;
                    }
                }
                hardAvg = hardSum/hardCount;
                hardhigh.setText(String.valueOf(hardHighest));
                avghard.setText(String.valueOf(hardAvg));
                Log.d("TAGG", String.valueOf(hardHighest));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }


    @Override
    public boolean onTouchEvent(MotionEvent event){
        if(event.getAction()==MotionEvent.ACTION_OUTSIDE){
            return false;
        }
        return true;
    }
}
