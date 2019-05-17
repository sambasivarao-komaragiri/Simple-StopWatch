package com.siva.sivakomaragiri.stopwatch;


import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView textViewHour = null;
    TextView textViewMinute = null;
    TextView textViewSecond = null;
    TextView textViewMSecond = null;
    Button buttonStart = null;
    Button buttonStop = null;
    Button butttonReset = null;

    Handler handler = null;
    int mSeconds=0;
    int seconds=0;
    int minutes=0;
    int hours=0;
    Runnable runnable = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewHour = findViewById(R.id.textView_hours);
        textViewMinute = findViewById(R.id.textView_minute);
        textViewSecond = findViewById(R.id.textView_seconds);
        textViewMSecond=findViewById(R.id.textView_mSec);
        buttonStart = findViewById(R.id.button_startStop);
        buttonStop = findViewById(R.id.button_stop);
        butttonReset = findViewById(R.id.button_reset);


        butttonReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // flush variables
                textViewHour.setText("0");
                textViewMinute.setText("0");
                textViewSecond.setText("0");
                textViewMSecond.setText("0");
                handler.removeCallbacks(runnable);
                mSeconds = 0;
                seconds=0;
                minutes=0;
                hours=0;


            }
        });

        buttonStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handler.removeCallbacks(runnable);
            }
        });

        buttonStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handler = new Handler(new Handler.Callback() {
                    @Override
                    public boolean handleMessage(Message message) {
                        updateTime();
                        return false;
                    }
                });
                handler.postDelayed(runnable, 1000);
            }
        });

        runnable = new Runnable() {
            @Override
            public void run() {
                seconds = seconds + 1;
                handler.postDelayed(this, 1000);
                handler.sendEmptyMessage(100);
            }
        };
    }

    public void updateTime(){
        if(seconds==60) {
            seconds = 0;
            minutes=minutes+1;
        }
        textViewSecond.setText(String.valueOf(seconds));
        if(minutes==60) {
            minutes=0;
            hours = hours + 1;
        }
        textViewMinute.setText(String.valueOf(minutes));
        textViewHour.setText(String.valueOf(hours));
    }


}

