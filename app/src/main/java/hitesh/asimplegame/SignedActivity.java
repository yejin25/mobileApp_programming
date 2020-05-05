package hitesh.asimplegame;



import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.Nullable;

public class SignedActivity extends Activity {
    Button btnLevel;
    Button btnRanking;
    ImageButton btnSetting;
    Button btnMyPage;
    boolean isStart = true;
    private static String userName;
    private static String userID;
    TextView txtResult;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signed);

        userName = LoginData.firebaseAuth.getCurrentUser().getDisplayName();
        userID = LoginData.firebaseAuth.getCurrentUser().getEmail();

        btnLevel = (Button)findViewById(R.id.btn_level);
        btnRanking = (Button)findViewById(R.id.btn_ranking);
        btnSetting = (ImageButton)findViewById(R.id.btn_setting);
        btnMyPage = (Button)findViewById(R.id.btn_mypage);

//        if(isStart && SettingFragment.getBgmStatus()) {
//            Intent bgmintent = new Intent(this, Bgm.class);
//            bgmintent.putExtra(Bgm.MESSAGE_KEY, true);
//            startService(bgmintent);
//            isStart=false;
//        }


        btnLevel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SelectLevel.class);
                startActivity(intent);
            }
        });

        btnRanking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), RankingActivity.class);
                startActivity(intent);
            }
        });

        btnSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("SignedActivity", "==== Setting button Clicked");
                Intent intent = new Intent(getApplicationContext(), Settings.class);
                startActivity(intent);
            }
        });

        btnMyPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("SignedActivity", "==== mypage button Clicked");
                Intent intent = new Intent(getApplicationContext(), MyPageActivity.class);
                startActivity(intent);
            }
        });


}

    public static String getUserName(){
        return userName;
    }

    public static  String getUserID(){
        return userID;
    }


//    @Override
//    public void onBackPressed(){
//        super.onBackPressed();
//        stopService(new Intent("hitesh.asimplegame.Bgm"));
//    }
@Override
public boolean onKeyDown(int keyCode, KeyEvent event) {
    if ((keyCode == KeyEvent.KEYCODE_BACK)) {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        return true;
    }
    return super.onKeyDown(keyCode, event);
}

}
