package hitesh.asimplegame;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;

public class RankingActivity extends Activity {
    TextView title;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ranking);

        title = (TextView)findViewById(R.id.test);

        title.setText("Ranking");
    }
}
