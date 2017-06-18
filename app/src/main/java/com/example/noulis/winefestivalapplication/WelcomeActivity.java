package com.example.noulis.winefestivalapplication;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.Calendar;
import java.util.concurrent.TimeUnit;

public class WelcomeActivity extends AppCompatActivity {
    private ProgressBar progressBar;
    int count = 1;
    boolean beforeFestival;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        getSupportActionBar().hide();

        // customize count down progress bar
        progressBar = (ProgressBar) findViewById(R.id.progressBar2);
        progressBar.setVisibility(View.VISIBLE);
        progressBar.setMax(5);
        progressBar.setProgress(0);
        new proceedToMainActivityWithDelay().execute(5);

        // countdown days to the festival
        TextView countDownMsg = (TextView) findViewById(R.id.daysTillFestival);
        int daysTillFestival = getDaysTillFestival();
        if (daysTillFestival == -1) {
            beforeFestival = false;
            countDownMsg.setText("Τέλος το κρασί για φέτος! Του χρόνου πάλι!");
        }
        else if (daysTillFestival == 0) {
            beforeFestival = false;
            countDownMsg.setText("Η Γιορτή έχει αρχίσει! Τρέξτε!");
        }
        else {
            beforeFestival = true;
            countDownMsg.setText(daysTillFestival + " μέρες ακόμα για τη Γιορτή Κρασιού!");
        }
    }

    class proceedToMainActivityWithDelay extends AsyncTask<Integer, Integer, String> {
        @Override
        protected String doInBackground(Integer... params) {
            for (; count <= params[0]; count++) {
                try {
                    Thread.sleep(1000);
                    publishProgress(count);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }
        @Override
        protected void onPostExecute(String result) {
            getApplicationContext().startActivity(new Intent(getApplicationContext(), MainActivity.class));
        }
        @Override
        protected void onPreExecute() {
        }
        @Override
        protected void onProgressUpdate(Integer... values) {
            progressBar.setProgress(values[0]);
        }
    }

    private int getDaysTillFestival() {
        // TODO: set actual date and time of the wine festival
        Calendar wineFestivalStartDate = Calendar.getInstance();
        //wineFestivalStartDate.set(Calendar.MINUTE, 0);
        //wineFestivalStartDate.set(Calendar.HOUR, 21);
        wineFestivalStartDate.set(Calendar.DAY_OF_MONTH, 18);
        wineFestivalStartDate.set(Calendar.MONTH, Calendar.AUGUST);
        wineFestivalStartDate.set(Calendar.YEAR, 2017);

        Calendar wineFestivalEndDate = wineFestivalStartDate;
        //wineFestivalEndDate.set(Calendar.HOUR, 2);
        wineFestivalEndDate.set(Calendar.DAY_OF_MONTH, 21);

        if (Calendar.getInstance().compareTo(wineFestivalStartDate) < 0) {
            // before fest
            // todo: have to return remaining hours in case app is run at the day of the fest
            return wineFestivalStartDate.get(Calendar.DAY_OF_YEAR) - Calendar.getInstance().get(Calendar.DAY_OF_YEAR);
        }
        else if (Calendar.getInstance().compareTo(wineFestivalEndDate) < 0){
            // during fest
            return 0;
        }
        else {
            // after fest
            return -1;
        }
    }

}
