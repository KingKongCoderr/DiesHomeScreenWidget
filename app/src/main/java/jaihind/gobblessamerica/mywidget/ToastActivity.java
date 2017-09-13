package jaihind.gobblessamerica.mywidget;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class ToastActivity extends AppCompatActivity {
    TextView result_tv;
    ImageView imageView;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toast);
        result_tv =(TextView) findViewById(R.id.result_tv);
        imageView = (ImageView) findViewById(R.id.result_iv);
        Bundle extras = getIntent().getExtras();
        String result = extras.getString(DiceWidget.EXTRA_TEXT,"Default string");
        int imageId = extras.getInt(DiceWidget.EXTRA_IMAGE_ID, R.drawable.die_1);
        result_tv.setText(result);
        imageView.setImageResource(imageId);
    }
}
