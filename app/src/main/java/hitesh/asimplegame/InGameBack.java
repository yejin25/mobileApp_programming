package hitesh.asimplegame;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.Button;


public class InGameBack extends Activity {
    private Button btn_yes;
    private Button btn_no;
    @Override
    protected void onCreate(Bundle saveInstanceStatus){
        super.onCreate(saveInstanceStatus);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_ingameback);

        btn_yes = (Button) findViewById(R.id.btnyes);
        btn_no = (Button) findViewById(R.id.btnno);

        btn_yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SignedActivity.class);
                startActivity(intent);
            }
        });

        btn_no.setOnClickListener(new View.OnClickListener() {
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
