package com.example.alaha.guesswrong;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

public class Main9Activity extends AppCompatActivity {

    int i,j,l;

    boolean sf;
    MediaPlayer me;

    int mp=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);


        setContentView(R.layout.activity_main9);

        mp=0;

        SharedPreferences preferences=getSharedPreferences("sfxState",MODE_PRIVATE);
        sf = preferences.getBoolean("lastSfxState", true);

        Bundle f = getIntent().getExtras();

        if(f!=null){
            i = f.getInt("tag1");
            j = f.getInt("tag2");
            l = f.getInt("tag3");
        }

        AlertDialog.Builder ab = new AlertDialog.Builder(Main9Activity.this);

        ab.setTitle("Game Over");
        ab.setMessage("You reached level "+j+ "\nYour Score is "+i+ " points\nHighest Score : "+l+" points");
        ab.setCancelable(false);
        ab.setPositiveButton("New Game",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        startActivity(new Intent(getApplicationContext(),Main8Activity.class));
                        sfx();
                        finish();
                        mp=1;
                    }
                }
        );
        ab.setNeutralButton("Quit",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        sfx();
                        finish();
                        mp=1;
                    }
                }
        );
        ab.create();
        ab.show();
    }

    private void sfx(){
        if(sf){
            me = MediaPlayer.create(this,R.raw.sfx);
            me.start();
        }
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
