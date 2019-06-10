package com.example.alaha.guesswrong;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class Main2Activity extends AppCompatActivity implements View.OnClickListener{

    private Button a,b,c,u;
    Animation e;
    public MediaPlayer me;

    int sessionDepth = 0;

    LinearLayout l2;

    Boolean bo,k;
    int se;

    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.drawable.logo1);
        getSupportActionBar().setDisplayUseLogoEnabled(true);

        media.SoundPlayer(this,R.raw.wander);

        a = findViewById(R.id.button1);
        b = findViewById(R.id.button2);
        c = findViewById(R.id.button3);
        u = findViewById(R.id.high);

        l2 = findViewById(R.id.layout2);

        SharedPreferences shared = getSharedPreferences("Background",MODE_PRIVATE);
        se = shared.getInt("myBack",R.drawable.background);

            l2.setBackground(getDrawable(se));

        e = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fade_in);

        a.startAnimation(e);
        b.startAnimation(e);
        c.startAnimation(e);
        u.startAnimation(e);

        a.setOnClickListener(this);
        b.setOnClickListener(this);
        c.setOnClickListener(this);
        u.setOnClickListener(this);

        SharedPreferences sh = getSharedPreferences("musicState",MODE_PRIVATE);
        bo = sh.getBoolean("lastState",true);

        if (!bo){
            media.player.stop();
        }
        if (bo && !media.player.isPlaying()){
            media.SoundPlayer(this,R.raw.wander);
        }

        Boolean bool = loadAlert();
        if (bool){
            howTo();
        }

        sessionDepth = 0;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater g = getMenuInflater();
        g.inflate(R.menu.menu_layout,menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(item.getItemId()==R.id.setting){

            sfx();

            sessionDepth = 1;

            Intent d = new Intent(Main2Activity.this,Main3Activity.class);
            startActivity(d);
        }
        if(item.getItemId()==R.id.share){

            sfx();

            sessionDepth = 0;

            shareApplication();
        }
        if(item.getItemId()==R.id.about){

            sfx();

            sessionDepth = 1;

            Intent d = new Intent(Main2Activity.this,Main4Activity.class);
            startActivity(d);
        }
        if(item.getItemId()==R.id.feedback){

            sfx();

            sessionDepth = 1;

            Intent d = new Intent(Main2Activity.this,Main5Activity.class);
            startActivity(d);
        }
        if(item.getItemId()==R.id.rate){

            sfx();

            sessionDepth = 1;

            Intent d = new Intent(Main2Activity.this,Main6Activity.class);
            startActivity(d);
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressLint("NewApi")
    @Override
    protected void onRestart() {

        try {
            a.startAnimation(e);
            b.startAnimation(e);
            c.startAnimation(e);
            u.startAnimation(e);

            if (bo && !media.player.isPlaying()){
                media.player.start();
            }

            SharedPreferences shared = getSharedPreferences("Background",MODE_PRIVATE);
            se = shared.getInt("myBack",R.drawable.background);

                l2.setBackground(getDrawable(se));


        }catch(Exception ignored){

        }

        super.onRestart();
    }

    @Override
    protected void onStart() {
        super.onStart();

        sessionDepth = 0;

        SharedPreferences share = getSharedPreferences("sfxState",MODE_PRIVATE);
        k = share.getBoolean("lastSfxState",true);

            if (bo&&!media.player.isPlaying()) {
                media.player.start();
            }

            if (se==R.drawable.background1) {
                a.setTextColor(getResources().getColor(R.color.colorAccent));
                b.setTextColor(getResources().getColor(R.color.colorAccent));
                c.setTextColor(getResources().getColor(R.color.colorAccent));
                u.setTextColor(getResources().getColor(R.color.colorAccent));
            }
            else{
                a.setTextColor(getResources().getColor(R.color.white));
                b.setTextColor(getResources().getColor(R.color.white));
                c.setTextColor(getResources().getColor(R.color.white));
                u.setTextColor(getResources().getColor(R.color.white));
            }

    }

    @Override
    protected void onStop() {

        if (sessionDepth == 0) {
            if (media.player.isPlaying()) {
                media.player.pause();
            }
        }

        super.onStop();
    }

    @Override
    public void onBackPressed() {

        sfx();

        AlertDialog.Builder f = new AlertDialog.Builder(Main2Activity.this);

        f.setIcon(R.drawable.question);
        f.setTitle(R.string.title);
        f.setMessage(R.string.msg);
        f.setCancelable(false);

        f.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                sfx();
                finish();

            }
        });

        f.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                sessionDepth = 0;
                sfx();
                dialog.cancel();

            }
        });

        AlertDialog g =f.create();
        g.show();

    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.button1){

            sfx();

            sessionDepth = 1;

            Intent d = new Intent(Main2Activity.this,Main8Activity.class);
            startActivity(d);
        }
        if(v.getId()==R.id.high){

            sfx();

            sessionDepth = 1;

            Intent d = new Intent(Main2Activity.this,Main1Activity.class);
            startActivity(d);
        }
        if(v.getId()==R.id.button2){

            sfx();

            sessionDepth = 1;

            Intent d = new Intent(Main2Activity.this,Main7Activity.class);
            startActivity(d);
        }
        if(v.getId()==R.id.button3){

            sfx();

            sessionDepth = 0;

            AlertDialog.Builder f = new AlertDialog.Builder(Main2Activity.this);

            f.setIcon(R.drawable.question);
            f.setTitle(R.string.title);
            f.setMessage(R.string.msg);
            f.setCancelable(false);

            f.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                    sfx();
                    finish();

                }
            });

            f.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                    sessionDepth = 0;
                    sfx();
                    dialog.cancel();

                }
            });

            AlertDialog g =f.create();
            g.show();
        }
    }

    @Override
    protected void onDestroy() {

        if(media.player!=null && media.player.isPlaying()){
            media.player.stop();
            media.player.release();
            media.player=null;
        }

        super.onDestroy();
    }

    public void howTo(){

        AlertDialog.Builder ab = new AlertDialog.Builder(Main2Activity.this);

        ab.setTitle("How to play?");
        ab.setMessage(R.string.help);
        ab.setCancelable(true);

        ab.setPositiveButton("Ok",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        sfx();

                        dialog.cancel();

                        e = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fade_in);

                        a.startAnimation(e);
                        b.startAnimation(e);
                        c.startAnimation(e);
                        u.startAnimation(e);
                    }
                }
        );

        ab.setNeutralButton("Never Show Again",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        sfx();

                        dialog.cancel();

                        e = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fade_in);

                        a.startAnimation(e);
                        b.startAnimation(e);
                        c.startAnimation(e);
                        u.startAnimation(e);

                        saveAlert(false);
                    }
                }
        );

        ab.create();
        ab.show();

    }

    private void saveAlert(boolean k){

        SharedPreferences sh = getSharedPreferences("alertState",MODE_PRIVATE);
        SharedPreferences.Editor ed = sh.edit();
        ed.putBoolean("lastAlertState",k);
        ed.commit();

    }

    private boolean loadAlert(){

        SharedPreferences sh = getSharedPreferences("alertState",MODE_PRIVATE);
        boolean k = sh.getBoolean("lastAlertState",true);
        return k;

    }

    private void sfx(){
        if(k){
            me = MediaPlayer.create(this,R.raw.sfx);
            me.start();
        }
    }

    private void shareApplication() {
        ApplicationInfo app = getApplicationContext().getApplicationInfo();
        String filePath = app.sourceDir;

        Intent intent = new Intent(Intent.ACTION_SEND);

        intent.setType("application/vnd.android.package-archive");

        File originalApk = new File(filePath);

        try {
            File tempFile = new File(getExternalCacheDir() + "/ExtractedApk");

            if (!tempFile.isDirectory()) {
                if (!tempFile.mkdirs()) {
                    return;
                }
            }

            tempFile = new File(tempFile.getPath() + "/" + getString(app.labelRes) + ".apk");

            if (!tempFile.exists()) {
                if (!tempFile.createNewFile()) {
                    return;
                }
            }

            InputStream in = new FileInputStream(originalApk);
            OutputStream out = new FileOutputStream(tempFile);

            byte[] buf = new byte[1024];
            int len;
            while ((len = in.read(buf)) > 0) {
                out.write(buf, 0, len);
            }
            in.close();
            out.close();

            intent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(tempFile));
            startActivity(Intent.createChooser(intent, "Share app via"));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
