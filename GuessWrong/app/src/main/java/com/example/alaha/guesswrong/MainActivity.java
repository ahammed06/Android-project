package com.example.alaha.guesswrong;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

public class MainActivity extends AppCompatActivity {

    private ProgressBar a;
    int b;
    LinearLayout l0;
    Animation k;

    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_main);

        a = findViewById(R.id.progress);
        l0 = findViewById(R.id.layout0);

        SharedPreferences shared = getSharedPreferences("Background",MODE_PRIVATE);
        int se = shared.getInt("myBack",R.drawable.background);


            l0.setBackground(getDrawable(se));


        k = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fade_in);

        l0 = findViewById(R.id.layout0);
        l0.startAnimation(k);

        Thread b = new Thread(new Runnable() {
            @Override
            public void run() {
                work();
                next();
            }
        });
        b.start();
    }

    public void work() {
        for (b = 10; b <= 100; b = b + 10) {
            try {
                Thread.sleep(200);
                a.setProgress(b);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void next(){
        Intent c = new Intent(MainActivity.this,Main2Activity.class);
        startActivity(c);
        finish();
    }
}
