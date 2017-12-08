package applicaton.android.com.sehonmin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import applicaton.android.com.sehonmin.R;

public class ResultActivity extends AppCompatActivity {
    TextView txt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        Intent intent=getIntent();
        txt=(TextView)findViewById(R.id.textsome);
        txt.setText(intent.getStringExtra("data"));
    }
}
