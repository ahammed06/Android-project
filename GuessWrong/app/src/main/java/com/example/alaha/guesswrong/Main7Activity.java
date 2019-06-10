package com.example.alaha.guesswrong;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Main7Activity extends AppCompatActivity {

    Animation k;
    TextView a,b;

    LinearLayout l7;

    boolean sf;
    MediaPlayer me;

    int mp=0;

    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main7);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.drawable.logo1);
        getSupportActionBar().setDisplayUseLogoEnabled(true);

        mp=0;

        k = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fade_in);

        a = findViewById(R.id.text4);
        b = findViewById(R.id.text5);

        l7 = findViewById(R.id.layout7);

        SharedPreferences shared = getSharedPreferences("Background",MODE_PRIVATE);
        int se = shared.getInt("myBack",R.drawable.background);

        l7.setBackground(getDrawable(se));

        if (se==R.drawable.background1) {
            a.setTextColor(getResources().getColor(R.color.red));
            b.setTextColor(getResources().getColor(R.color.colorAccent));
        }

        a.startAnimation(k);
        b.startAnimation(k);

        SharedPreferences preferences=getSharedPreferences("sfxState",MODE_PRIVATE);
        sf = preferences.getBoolean("lastSfxState", true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(item.getItemId()==android.R.id.home){

            if(sf){
                me = MediaPlayer.create(this,R.raw.sfx);
                me.start();
            }

            this.finish();

            mp=1;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {

        if(sf){
            me = MediaPlayer.create(this,R.raw.sfx);
            me.start();
        }

        super.onBackPressed();

        mp=1;
    }

    @Override
    protected void onPause() {

        if (mp==0){
            media.player.pause();
        }

        super.onPause();
    }

    @Override
    protected void onRestart() {

        SharedPreferences sh = getSharedPreferences("musicState",MODE_PRIVATE);
        boolean k = sh.getBoolean("lastState",true);

        if (k && !media.player.isPlaying()){
            media.player.start();
        }

        super.onRestart();
    }
}
