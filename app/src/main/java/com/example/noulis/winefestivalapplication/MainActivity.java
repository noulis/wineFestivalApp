package com.example.noulis.winefestivalapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    GridView gridView;
    static final String[] grid_options = new String[] {
            "Πρόγραμμα",
            "Πώς να πας",
            "Εισητήρια",
            "Λαχειοφόρος Αγορά",
            "Φωτογραφίες",
            "Για τη γιορτή",
            "Για τη Ζίτσα",
            "Για τα οινοποιεία",
            "Facebook",
            "Twitter"};

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gridView = (GridView) findViewById(R.id.gridView1);
        gridView.setAdapter(new ImageAdapter(this, grid_options));

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                Toast.makeText(getApplicationContext(), ((TextView) v.findViewById(R.id.grid_item_label))
                        .getText(), Toast.LENGTH_SHORT).show();
            }
        });
    }

}
