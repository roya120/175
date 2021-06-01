package edu.sjsu.android.zoodirectoryy;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class AnimalDetailActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animal_detail);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        int title = extras.getInt("title");
        int description = extras.getInt("description");
        int image = extras.getInt("image");

        ImageView animalPic = findViewById(R.id.animalImage);
        animalPic.setImageResource(image);

        TextView animalTitle = findViewById(R.id.animalText);
        animalTitle.setText(title);

        TextView text = findViewById(R.id.animalDescription);
        text.setText(description);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.information:
                Intent myIntent = new Intent(this, ZooInformationActivity.class);
                startActivity(myIntent);
                return true;
            case R.id.uninstall:
                Uri packageURI = Uri.parse("package:" + getApplicationContext().getPackageName());
                Intent uninstallIntent = new Intent(Intent.ACTION_DELETE, packageURI);
                startActivity(uninstallIntent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
