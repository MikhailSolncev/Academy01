package com.debugg3r.android.academy01;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;

import com.debugg3r.android.academy01.data.Lecture;

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
            Lecture lecture = (Lecture) bundle.get("lecture");
            if (lecture != null) {
                theme.setText(lecture.theme);
                author.setText(lecture.author.name);
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
