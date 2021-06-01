package edu.sjsu.android.mybrowser;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class MyBrowser extends AppCompatActivity {

    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_browser);
        textView = findViewById(R.id.text);
        String url = getIntent().getDataString();
        textView.setText(url);
    }
}