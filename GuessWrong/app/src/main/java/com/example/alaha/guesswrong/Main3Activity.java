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
import android.widget.TextView;

public class Main3Activity extends AppCompatActivity implements View.OnClickListener{

    Button d,c,x,y,li,da;
    Animation e,f;
    TextView i,j,th;
    public MediaPlayer me;
    boolean sf;
    int se,mp=0;
    LinearLayout l3;

    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.drawable.logo1);
        getSupportActionBar().setDisplayUseLogoEnabled(true);

        sf=loadSFX();

        mp=0;

        c = findViewById(R.id.on);
        d = findViewById(R.id.off);
        i = findViewById(R.id.music);
        x = findViewById(R.id.on1);
        y = findViewById(R.id.off1);
        j = findViewById(R.id.sfx);

        li = findViewById(R.id.light);
        da = findViewById(R.id.dark);
        th = findViewById(R.id.theme);
        l3 = findViewById(R.id.layout3);

        SharedPreferences shared = getSharedPreferences("Background",MODE_PRIVATE);
        se = shared.getInt("myBack",R.drawable.background);

        l3.setBackground(getDrawable(se));

        Boolean bo = loadMusic();
        if (!bo){
            media.player.stop();
        }

        if (se==R.drawable.background1) {
            li.setText(R.string.def);
                i.setTextColor(getResources().getColor(R.color.red));
                j.setTextColor(getResources().getColor(R.color.red));
                th.setTextColor(getResources().getColor(R.color.red));
        }
        if (se==R.drawable.background2){
            da.setText(R.string.def);
        }
        if (se==R.drawable.background){
            li.setText(R.string.light);
            da.setText(R.string.dark);
        }

        c.setOnClickListener(this);
        d.setOnClickListener(this);
        x.setOnClickListener(this);
        y.setOnClickListener(this);

        li.setOnClickListener(this);
        da.setOnClickListener(this);

        e = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fade_in);
        f = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fade_out);

        da.startAnimation(e);
        th.startAnimation(e);
        li.startAnimation(e);

        i.startAnimation(e);
        j.startAnimation(e);
        c.startAnimation(e);
        d.startAnimation(e);
        x.startAnimation(e);
        y.startAnimation(e);

        if(loadMusic()){
            c.setEnabled(false);
            d.setEnabled(true);
        }
        else{
            c.setEnabled(true);
            d.setEnabled(false);
        }

        if(sf){
            x.setEnabled(false);
            y.setEnabled(true);
        }
        else{
            x.setEnabled(true);
            y.setEnabled(false);
        }

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


    @SuppressLint("NewApi")
    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.on){

            mp=0;

            if(sf){
                me = MediaPlayer.create(this,R.raw.sfx);
                me.start();
            }

            if (!media.player.isPlaying()) {
                media.SoundPlayer(this,R.raw.wander);
            }

            c.setEnabled(false);
            d.setEnabled(true);

        }
        if(v.getId()==R.id.off){

            mp=0;

            if(sf){
                me = MediaPlayer.create(this,R.raw.sfx);
                me.start();
            }

            if (media.player.isPlaying()) {
                media.player.stop();
            }

            c.setEnabled(true);
            d.setEnabled(false);

        }
        if(v.getId()==R.id.on1){
            mp=0;
            me = MediaPlayer.create(this,R.raw.sfx);
            me.start();
            sf=true;
            saveSFX(true);

            x.setEnabled(false);
            y.setEnabled(true);

        }
        if (v.getId()==R.id.off1){
            mp=0;
            saveSFX(false);
            sf=false;
            x.setEnabled(true);
            y.setEnabled(false);

        }

        if (v.getId()==R.id.light){

            mp=0;

            if (loadBackground()==R.drawable.background) {
                l3.setBackground(getDrawable(R.drawable.background1));
                storeBackground(R.drawable.background1);
                li.setText(R.string.def);
                da.setText(R.string.dark);
            }

            else if (loadBackground()==R.drawable.background2) {
                li.setText(R.string.def);
                da.setText(R.string.dark);
                l3.setBackground(getDrawable(R.drawable.background1));
                storeBackground(R.drawable.background1);
            }

            else{
                li.setText(R.string.light);
                l3.setBackground(getDrawable(R.drawable.background));
                storeBackground(R.drawable.background);
            }

        }
        if (v.getId()==R.id.dark){

            mp=0;

            if (loadBackground()==R.drawable.background) {
                da.setText(R.string.def);
                li.setText(R.string.light);
                l3.setBackground(getDrawable(R.drawable.background2));
                storeBackground(R.drawable.background2);
            }

            else if (loadBackground()==R.drawable.background1) {
                da.setText(R.string.def);
                li.setText(R.string.light);
                l3.setBackground(getDrawable(R.drawable.background2));
                storeBackground(R.drawable.background2);
            }

            else{
                da.setText(R.string.dark);
                l3.setBackground(getDrawable(R.drawable.background));
                storeBackground(R.drawable.background);
            }
        }

        saveMusic(media.player.isPlaying());

    }

    private void saveMusic(boolean k){

        SharedPreferences sh = getSharedPreferences("musicState",MODE_PRIVATE);
        SharedPreferences.Editor ed = sh.edit();
        ed.putBoolean("lastState",k);
        ed.commit();

    }

    private boolean loadMusic(){

        SharedPreferences sh = getSharedPreferences("musicState",MODE_PRIVATE);
        boolean k = sh.getBoolean("lastState",true);
        return k;

    }

    private void saveSFX(boolean k){

        SharedPreferences share = getSharedPreferences("sfxState",MODE_PRIVATE);
        SharedPreferences.Editor edito = share.edit();
        edito.putBoolean("lastSfxState",k);
        edito.commit();

    }

    private boolean loadSFX(){

        SharedPreferences share = getSharedPreferences("sfxState",MODE_PRIVATE);
        boolean k = share.getBoolean("lastSfxState",true);
        return k;

    }

    private void storeBackground(int back){
        SharedPreferences shared = getSharedPreferences("Background",MODE_PRIVATE);
        SharedPreferences.Editor editor = shared.edit();
        editor.putInt("myBack",back);
        editor.commit();
    }

    private int loadBackground(){
        SharedPreferences shared = getSharedPreferences("Background",MODE_PRIVATE);
        int se = shared.getInt("myBack",R.drawable.background);
        return se;
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
