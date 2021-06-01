package edu.sjsu.android.intentapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class ActivityLoaderActivity extends AppCompatActivity {

    private Button web;
    private Button call;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loader);

        web = (Button)findViewById(R.id.webButton);
        call = (Button)findViewById(R.id.callButton);

        web.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent webIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.amazon.com"));
                Intent chooser = Intent.createChooser(webIntent, "Load https://www.amazon.com with:");
                startActivity(chooser);
            }
        });

        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String myPhoneNumberUri = "tel:+194912344444";
                Intent myIntent2 = new Intent(Intent.ACTION_DIAL, Uri.parse(myPhoneNumberUri));

                startActivity(myIntent2);
            }
        });
    }
}