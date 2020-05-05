package hitesh.asimplegame;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.Button;

public class Back extends Activity {
    private Button btnYes;
    private Button btnNo;
    @Override
    protected void onCreate(Bundle saveInstanceStatus){
        super.onCreate(saveInstanceStatus);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_back);

        btnYes = (Button) findViewById(R.id.yes);
        btnNo = (Button) findViewById(R.id.no);

        btnYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SignedActivity.class);
                startActivity(intent);
            }
        });

        btnNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
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
