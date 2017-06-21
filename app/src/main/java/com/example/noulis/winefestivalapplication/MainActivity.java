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
    static final String[] grid_options = new String[]{
            "Πρόγραμμα",
            "Χάρτης",
            "Εισητήρια",
            "Λαχειοφόρος Αγορά",
            "Φωτογραφίες",
            "Γιορτή Κρασιού",
            "Ζίτσα",
            "Οινοποιεία",
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
                switch (((TextView) v.findViewById(R.id.grid_item_label)).getText().toString()) {
                    case "Πρόγραμμα":
                        getApplicationContext().startActivity(new Intent(getApplicationContext(), ProgramActivity.class));
                        break;
                    case "Χάρτης":
                        // todo: create map activity
                        //getApplicationContext().startActivity(new Intent(getApplicationContext(), MapActivity.class));
                        break;
                    case "Εισητήρια":
                        getApplicationContext().startActivity(new Intent(getApplicationContext(), TicketsActivity.class));
                        break;
                    case "Λαχειοφόρος Αγορά":
                        getApplicationContext().startActivity(new Intent(getApplicationContext(), LotteryActivity.class));
                        break;
                    case "Φωτογραφίες":
                        getApplicationContext().startActivity(new Intent(getApplicationContext(), PhotosActivity.class));
                        break;
                    case "Γιορτή Κρασιού":
                        getApplicationContext().startActivity(new Intent(getApplicationContext(), AboutFestActivity.class));
                        break;
                    case "Ζίτσα":
                        getApplicationContext().startActivity(new Intent(getApplicationContext(), AboutZitsaActivity.class));
                        break;
                    case "Οινοποιεία":
                        getApplicationContext().startActivity(new Intent(getApplicationContext(), AboutWineriesActivity.class));
                        break;
                    case "Facebook":
                        connectToFacebook(getApplicationContext());
                        break;
                    case "Twitter":
                        connectToTwitter(getApplicationContext());
                        break;
                    default:
                        Toast.makeText(getApplicationContext(), "Please select again", Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });
    }

    private void connectToFacebook(Context context) {
        PackageManager packageManager = context.getPackageManager();
        String correctURL = "";
        try {
            int versionCode = packageManager.getPackageInfo("com.facebook.katana", 0).versionCode;
            if (versionCode >= 3002850)
                correctURL =  "fb://facewebmodal/f?href=" + FACEBOOK_URL;
            else
                correctURL = "fb://page/" + FACEBOOK_PAGE_ID;
        } catch (PackageManager.NameNotFoundException e) {
            correctURL = FACEBOOK_URL;
        }
        Intent facebookIntent = new Intent(Intent.ACTION_VIEW);
        facebookIntent.setData(Uri.parse(correctURL));
        startActivity(facebookIntent);
    }

    private void connectToTwitter(Context context) {
        Intent twitterIntent = null;
        try {
            context.getPackageManager().getPackageInfo("com.twitter.android", 0);
            twitterIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("twitter://user?user_id=TheEllenShow"));
            twitterIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        } catch (Exception e) {
            twitterIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://twitter.com/TheEllenShow"));
        }
        this.startActivity(twitterIntent);
    }

}
