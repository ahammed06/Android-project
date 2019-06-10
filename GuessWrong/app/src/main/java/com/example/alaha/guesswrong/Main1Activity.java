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

public class Main1Activity extends AppCompatActivity {

    Animation a;
    int best1,best2,best3;
    boolean sf;
    MediaPlayer me;
    String cu1,cu2,cu3;
    LinearLayout l1;
    int se = 0;

    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main1);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.drawable.logo1);
        getSupportActionBar().setDisplayUseLogoEnabled(true);

        se=0;

        a = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fade_in);

        TextView t = findViewById(R.id.hghscr);
        TextView x = findViewById(R.id.best);

        l1 = findViewById(R.id.layout1);

        SharedPreferences shared = getSharedPreferences("Background",MODE_PRIVATE);
        int se = shared.getInt("myBack",R.drawable.background);

        l1.setBackground(getDrawable(se));

        if (se==R.drawable.background1) {
            t.setTextColor(getResources().getColor(R.color.colorAccent));
        }

        t.startAnimation(a);
        x.startAnimation(a);

        SharedPreferences preferences1=getSharedPreferences("scoreBoard",MODE_PRIVATE);

        best1 = preferences1.getInt("best1",0);
        best2 = preferences1.getInt("best2",0);
        best3 = preferences1.getInt("best3",0);

        cu1 = preferences1.getString("time1","No Entry");
        cu2 = preferences1.getString("time2","No Entry");
        cu3 = preferences1.getString("time3","No Entry");

        SharedPreferences preferences=getSharedPreferences("sfxState",MODE_PRIVATE);
        sf = preferences.getBoolean("lastSfxState", true);

        t.setText("1st - "+best1+" Points.\n"+cu1+" \n\n 2nd - "+best2+" Points.\n"+cu2+" \n\n 3rd - "+best3+" Points.\n"+cu3);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(item.getItemId()==android.R.id.home){

            if(sf){
                me = MediaPlayer.create(this,R.raw.sfx);
                me.start();
            }

            this.finish();
            se=1;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {

        if(sf){
            me = MediaPlayer.create(this,R.raw.sfx);
            me.start();
        }
        se=1;

        super.onBackPressed();
    }

    @Override
    protected void onPause() {

        if (se==0){
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
