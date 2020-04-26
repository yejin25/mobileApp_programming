package hitesh.asimplegame;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.auth.FirebaseAuth;

public class AfterActivity extends Activity implements View.OnClickListener {
    Button btnRevoke, btnLogout, btnStart;
    private FirebaseAuth mAuth ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_after);

        btnLogout = (Button)findViewById(R.id.btn_logout);
        btnRevoke = (Button)findViewById(R.id.btn_revoke);
        btnStart = (Button)findViewById(R.id.btn_start);

        mAuth = FirebaseAuth.getInstance();

        btnLogout.setOnClickListener(this);
        btnRevoke.setOnClickListener(this);
        btnStart.setOnClickListener(this);
    }

    private void signOut() {
        FirebaseAuth.getInstance().signOut();
        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
        startActivity(intent);
    }

    private void revokeAccess() {
        mAuth.getCurrentUser().delete();
        FirebaseAuth.getInstance().signOut();
        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
        startActivity(intent);
    }

    private void start(){
        Intent intent = new Intent(getApplicationContext(), QuestionActivity.class);
        startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_logout:
                Log.d("AfterActivity", "===== logout Called!");
                signOut();
                finishAffinity();
                break;
            case R.id.btn_revoke:
                Log.d("AfterActivity", "===== revoke Called!");
                revokeAccess();
                finishAffinity();
                break;
            case R.id.btn_start:
                Log.d("AfterActivity", "===== start Called!");
                start();
        }
    }
}
