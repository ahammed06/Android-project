package com.example.alaha.guesswrong;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

public class Main6Activity extends AppCompatActivity {

    private RatingBar a;
    private TextView b;
    Animation e;
    int x;

    LinearLayout l6;

    boolean sf;
    MediaPlayer me;

    int mp=0;

    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main6);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.drawable.logo1);
        getSupportActionBar().setDisplayUseLogoEnabled(true);

        mp=0;

        a = findViewById(R.id.rating);
        b = findViewById(R.id.text);
        Button y = findViewById(R.id.set);

        l6 = findViewById(R.id.layout6);

        SharedPreferences shared = getSharedPreferences("Background",MODE_PRIVATE);
        int se = shared.getInt("myBack",R.drawable.background);

        l6.setBackground(getDrawable(se));

        if (se==R.drawable.background1) {
            b.setTextColor(getResources().getColor(R.color.colorAccent));
        }

        e = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fade_in);

        x=loadRate();

        a.startAnimation(e);
        b.startAnimation(e);
        y.startAnimation(e);

        SharedPreferences preferences=getSharedPreferences("sfxState",MODE_PRIVATE);
        sf = preferences.getBoolean("lastSfxState", true);

        b.setText("Your Rating : "+x);
        a.setProgress(x);

        a.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                b.setText("Your Rating : "+(int)rating);
                sfx();
            }
        });

        y.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mp=1;
                saveRate(a.getProgress());
                sfx();
                finish();
            }
        });
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

    private void saveRate(int k){

        SharedPreferences s = getSharedPreferences("rate",MODE_PRIVATE);
        SharedPreferences.Editor edi = s.edit();
        edi.putInt("lastRate",k);
        edi.commit();

    }

    private int loadRate(){

        SharedPreferences sha = getSharedPreferences("rate",MODE_PRIVATE);
        int k = sha.getInt("lastRate",0);
        return k;

    }

    private void sfx(){
        if(sf){
            me = MediaPlayer.create(this,R.raw.sfx);
            me.start();
        }
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
