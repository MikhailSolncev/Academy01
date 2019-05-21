package com.debugg3r.android.academy01;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.debugg3r.android.academy01.data.Activity;
import com.debugg3r.android.academy01.data.Talk;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView author = findViewById(R.id.text_view_author);
        TextView theme = findViewById(R.id.text_view_title);

        Intent intent = getIntent();
        if (intent != null) {
            Bundle bundle = intent.getExtras();
            Activity activity = (Activity) bundle.get("activity");
            if (activity != null) {
                theme.setText(activity.title);
                if (activity instanceof Talk) {
                    //speaker.setText(activity.speaker.name);
                    author.setText(((Talk)activity).getSpeakerName());
                }
            }
        }

//        TextView view = findViewById(R.id.text_vew_description);
//        view.setMovementMethod(new ScrollingMovementMethod());
//
//        view = findViewById(R.id.text_view_author);
//        view.setOnClickListener(v -> {
//            Intent intent = new Intent(this, AboutActivity.class);
//            startActivity(intent);
//        });
    }
}
