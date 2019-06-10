package com.example.alaha.guesswrong;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

public class Main5Activity extends AppCompatActivity implements View.OnClickListener{

    private EditText c,d,e;
    Animation k;

    LinearLayout l5;

    boolean sf;
    MediaPlayer me;

    int mp=0;

    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.drawable.logo1);
        getSupportActionBar().setDisplayUseLogoEnabled(true);

        mp=0;

        Button a = findViewById(R.id.button4);
        Button b = findViewById(R.id.button5);
        c = findViewById(R.id.text1);
        d = findViewById(R.id.text2);
        e = findViewById(R.id.text3);

        l5 = findViewById(R.id.layout5);

        SharedPreferences shared = getSharedPreferences("Background",MODE_PRIVATE);
        int se = shared.getInt("myBack",R.drawable.background);

        l5.setBackground(getDrawable(se));

        if (se==R.drawable.background1) {
            a.setTextColor(getResources().getColor(R.color.colorAccent));
            b.setTextColor(getResources().getColor(R.color.colorAccent));
            c.setTextColor(getResources().getColor(R.color.colorAccent));
            d.setTextColor(getResources().getColor(R.color.colorAccent));
            e.setTextColor(getResources().getColor(R.color.colorAccent));
        }

        k = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fade_in);

        a.startAnimation(k);
        b.startAnimation(k);
        c.startAnimation(k);
        d.startAnimation(k);
        e.startAnimation(k);

        a.setOnClickListener(this);
        b.setOnClickListener(this);

        SharedPreferences preferences=getSharedPreferences("sfxState",MODE_PRIVATE);
        sf = preferences.getBoolean("lastSfxState", true);

    }

    @Override
    public void onClick(View v) {

        try{

            String f = c.getText().toString();
            String g = d.getText().toString();
            String h = e.getText().toString();

            if(v.getId()==R.id.button4){

                mp=1;

                if(sf){
                    me = MediaPlayer.create(this,R.raw.sfx);
                    me.start();
                }

                Intent i = new Intent(Intent.ACTION_SEND);
                i.setType("text/email");

                i.putExtra(Intent.EXTRA_EMAIL,new String[]{"sabbir1121@gmail.com"});
                i.putExtra(Intent.EXTRA_SUBJECT,"Feedback from App");
                i.putExtra(Intent.EXTRA_TEXT,"Name : "+f+"\nEmail : "+g+"\nMessage: "+h);

                startActivity(Intent.createChooser(i,"Send feedback with"));

            }
            else if(v.getId()==R.id.button5){

                mp=1;

                if(sf){
                    me = MediaPlayer.create(this,R.raw.sfx);
                    me.start();
                }

                c.setText("");
                d.setText("");
                e.setText("");
            }

        }catch (Exception j){
            Toast.makeText(getApplicationContext(),"Exception : "+j,Toast.LENGTH_SHORT).show();
        }

        mp=0;

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
