package com.example.bai1c7;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    Button btnDraw;
    EditText edtNumber;
    LinearLayout llButton;
    int n = 0;
    Random random = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addControls();
        addEvetns();
    }

    private void addEvetns() {
        btnDraw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int n = Integer.parseInt(edtNumber.getText().toString());
                ButtonTask task = new ButtonTask();
                task.execute(n);
            }
        });
    }

    private void addControls() {
        btnDraw = findViewById(R.id.btnDraw);
        edtNumber = findViewById(R.id.edtNumber);
        llButton = findViewById(R.id.llButton);
    }

    class ButtonTask extends AsyncTask<Integer,Double,Void>
    {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            llButton.removeAllViews();
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
        }

        @Override
        protected void onProgressUpdate(Double... values) {
            super.onProgressUpdate(values);
            double value = values[0];
            double width = values[1];
            double total = values[2];
            Button btn = new Button(MainActivity.this);
            btn.setText(value+"");
            DisplayMetrics displayMetrics = new DisplayMetrics();
            getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
            int width1 = displayMetrics.widthPixels;
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                    (int) (width1*width/total),
                    LinearLayout.LayoutParams.WRAP_CONTENT);
            btn.setLayoutParams(layoutParams);
            llButton.addView(btn);

        }

        @Override
        protected Void doInBackground(Integer... integers) {
            int n = integers[0];
            for(int i=1;i<=n;i++)
            {
                double value = random.nextInt(100);
                double width = i;
                double total = n;
                publishProgress(value,width,total);
                SystemClock.sleep(100);
            }
            return null;
        }
    }

}