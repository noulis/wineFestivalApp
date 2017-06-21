package com.example.noulis.winefestivalapplication;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    public static String FACEBOOK_URL = "https://www.facebook.com/mixanitouxronou";
    public static String FACEBOOK_PAGE_ID = "GOgVVVaeFgG";

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
                switch (((TextView)v.findViewById(R.id.grid_item_label)).getText().toString()) {
                    case "Πρόγραμμα":
                        //getApplicationContext().startActivity(new Intent(getApplicationContext(), ProgramActivity.class));
                        break;
                    case "Πώς να πας":
                        //getApplicationContext().startActivity(new Intent(getApplicationContext(), MapActivity.class));
                        break;
                    case "Εισητήρια":
                        //getApplicationContext().startActivity(new Intent(getApplicationContext(), TicketsActivity.class));
                        break;
                    case "Λαχειοφόρος Αγορά":
                        //getApplicationContext().startActivity(new Intent(getApplicationContext(), LotteryActivity.class));
                        break;
                    case "Φωτογραφίες":
                        //getApplicationContext().startActivity(new Intent(getApplicationContext(), PhotosActivity.class));
                        break;
                    case "Για τη γιορτή":
                        //getApplicationContext().startActivity(new Intent(getApplicationContext(), AboutFestActivity.class));
                        break;
                    case "Για τη Ζίτσα":
                        //getApplicationContext().startActivity(new Intent(getApplicationContext(), AboutZitsaActivity.class));
                        break;
                    case "Για τα οινοποιεία":
                        //getApplicationContext().startActivity(new Intent(getApplicationContext(), AboutWineriesActivity.class));
                        break;
                    case "Facebook":
                        Intent facebookIntent = new Intent(Intent.ACTION_VIEW);
                        String facebookUrl = getFacebookPageURL(getApplicationContext());
                        facebookIntent.setData(Uri.parse(facebookUrl));
                        startActivity(facebookIntent);
                        break;
                    case "Twitter":
                        Toast.makeText(getApplicationContext(), "Go to Twitter page", Toast.LENGTH_SHORT).show();
                        break;
                    default:
                        Toast.makeText(getApplicationContext(), "Please select again", Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });
    }

    public String getFacebookPageURL(Context context) {
        PackageManager packageManager = context.getPackageManager();
        try {
            int versionCode = packageManager.getPackageInfo("com.facebook.katana", 0).versionCode;
            if (versionCode >= 3002850)
                return "fb://facewebmodal/f?href=" + FACEBOOK_URL;
            else
                return "fb://page/" + FACEBOOK_PAGE_ID;
        } catch (PackageManager.NameNotFoundException e) {
            return FACEBOOK_URL;
        }
    }

}
