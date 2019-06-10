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
import android.widget.ScrollView;
import android.widget.TextView;

public class Main4Activity extends AppCompatActivity {

    Animation k;
    LinearLayout i;
    boolean sf;
    MediaPlayer me;
    ScrollView l4;
    int mp=0;
    TextView a,b,c;

    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.drawable.logo1);
        getSupportActionBar().setDisplayUseLogoEnabled(true);

        mp=0;

        k = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fade_in);

        i = findViewById(R.id.abt);
        i.startAnimation(k);

        a = findViewById(R.id.dev);
        b = findViewById(R.id.dev1);
        c = findViewById(R.id.dev2);

        l4 = findViewById(R.id.layout4);

        SharedPreferences shared = getSharedPreferences("Background",MODE_PRIVATE);
        int se = shared.getInt("myBack",R.drawable.background);

        l4.setBackground(getDrawable(se));

        if (se==R.drawable.background1) {
            a.setTextColor(getResources().getColor(R.color.red));
            b.setTextColor(getResources().getColor(R.color.ghiya));
            c.setTextColor(getResources().getColor(R.color.colorAccent));
        }

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
