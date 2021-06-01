package edu.sjsu.android.zoodirectoryy;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;


import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new MyAdapter(createList(), this);
        recyclerView.setAdapter(adapter);
    }

    public List<int[]> createList() {
        final List<int[]> input = new ArrayList<>();
        int[] elephant = {R.string.elephant, R.string.elephantDescription, R.drawable.elephant};
        input.add(elephant);
        int[] bear = {R.string.bear, R.string.bearDescription, R.drawable.bear};
        input.add(bear);
        int[] giraffe = {R.string.girafee, R.string.giraffeDescription, R.drawable.giraffe};
        input.add(giraffe);
        int[] koala = {R.string.koala, R.string.koalaDescription, R.drawable.koala};
        input.add(koala);
        int[] tiger = {R.string.tiger, R.string.tigerDescription, R.drawable.tiger};
        input.add(tiger);
        return input;
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