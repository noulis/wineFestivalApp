package com.example.noulis.winefestivalapplication;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.Calendar;
import java.util.concurrent.TimeUnit;

public class WelcomeActivity extends AppCompatActivity {
    private final int dateOfFest = 19; // TODO: Put here the actual date of wine festival added by one

    private ProgressBar progressBar;
    private TextView countDownMsg;
    private int count = 1;
    private proceedToMainActivityWithDelay proceedToMainActivityWithDelay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        getSupportActionBar().hide();

        progressBar = (ProgressBar) findViewById(R.id.progressBar2);
        progressBar.setVisibility(View.GONE);
        countDownMsg = (TextView) findViewById(R.id.daysTillFestival);

        proceedToMainActivityWithDelay = new proceedToMainActivityWithDelay();

        buildCountDownInformation(progressBar, countDownMsg);
    }

    // TODO: if app goes in background when async task is running, doInBackground pops main activity by itself

    private void buildCountDownInformation(ProgressBar progressBar, TextView countDownMsg) {
        Calendar.getInstance().clear();
        Calendar wineFestivalStartDate = Calendar.getInstance();
        wineFestivalStartDate.set(Calendar.MINUTE, 1);
        wineFestivalStartDate.set(Calendar.HOUR, 0);
        wineFestivalStartDate.set(Calendar.DAY_OF_MONTH, dateOfFest);
        wineFestivalStartDate.set(Calendar.MONTH, Calendar.AUGUST);
        wineFestivalStartDate.set(Calendar.YEAR, 2017);

        Calendar wineFestivalEndDate = (Calendar) wineFestivalStartDate.clone();
        wineFestivalEndDate.set(Calendar.HOUR, 4);
        wineFestivalEndDate.set(Calendar.DAY_OF_MONTH, 21);

        if (Calendar.getInstance().before(wineFestivalStartDate)) {
            progressBar.setVisibility(View.VISIBLE);

            int timeDiffInDays = (int) Math.ceil(TimeUnit.MILLISECONDS.toDays(wineFestivalStartDate.getTimeInMillis() - Calendar.getInstance().getTimeInMillis() - 1));
            int timeDiffInHours = (int) Math.ceil(TimeUnit.MILLISECONDS.toHours(wineFestivalStartDate.getTimeInMillis() - Calendar.getInstance().getTimeInMillis() - 1)) - 2;

            if (timeDiffInDays > 1)
                countDownMsg.setText(timeDiffInDays + " μέρες ακόμα για τη Γιορτή Κρασιού!");
            else if (timeDiffInDays == 1)
                countDownMsg.setText(timeDiffInDays + " μέρα ακόμα για τη Γιορτή Κρασιού!");
            else
                if (timeDiffInHours <= 0) {
                    progressBar.setVisibility(View.GONE);
                    countDownMsg.setText("Η Γιορτή έχει αρχίσει! Τρέξτε!");
                }
                else if (timeDiffInHours == 1) {
                    countDownMsg.setText("Σε " + timeDiffInHours + " ώρα ξεκινάμε!");
                }
                else {
                    countDownMsg.setText("Σε " + timeDiffInHours + " ώρες ξεκινάμε!");
                }
        }
        else if (Calendar.getInstance().before(wineFestivalEndDate)) {
            countDownMsg.setText("Η Γιορτή έχει αρχίσει! Τρέξτε!");
        }
        else {
            countDownMsg.setText("Τέλος το κρασί για φέτος! Του χρόνου πάλι!");
        }

        progressBar.setMax(5);
        progressBar.setProgress(0);
        proceedToMainActivityWithDelay.execute(5);
    }

    private class proceedToMainActivityWithDelay extends AsyncTask<Integer, Integer, String> {
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
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN)
                finishAffinity();
            else
                System.exit(0);

        }
        @Override
        protected void onPreExecute() {
        }
        @Override
        protected void onProgressUpdate(Integer... values) {
            progressBar.setProgress(values[0]);
        }
    }

}
