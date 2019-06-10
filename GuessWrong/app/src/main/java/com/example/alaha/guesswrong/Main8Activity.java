package com.example.alaha.guesswrong;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Random;

public class Main8Activity extends AppCompatActivity {

    private TextView a,b,c,y;
    private ProgressBar d;
    private Button e,f;
    public int level,lives,score,mp=0;
    Animation k,l,t,s;

    LinearLayout l8;

    boolean sf;
    MediaPlayer me;

    int best1,best2,best3;

    public int counter,u;

    DateFormat df = new SimpleDateFormat("EEE, d MMM yyyy, hh : mm a");
    String currentTime = df.format(Calendar.getInstance().getTime());
    String cu1,cu2,cu3;

    private CountDownTimer time;
    private boolean trun;
    private long tleft = 5000;
    private long newtleft = 5000;

    private Questions mQuestions = new Questions();
    private String mAnswer;

    private int mQuestionsLenght = mQuestions.mQuestions.length;

    Random r;


    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_main8);

        k = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.slide_up);
        l = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.slide_down);
        t = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fade_out);
        s = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fade_in);

        mp=0;

        r = new Random();

        a = findViewById(R.id.score);
        b = findViewById(R.id.lives);
        y = findViewById(R.id.level);
        c = findViewById(R.id.question);
        d = findViewById(R.id.timer);
        e = findViewById(R.id.button6);
        f = findViewById(R.id.button7);

        l8 = findViewById(R.id.layout8);

        SharedPreferences shared = getSharedPreferences("Background",MODE_PRIVATE);
        int se = shared.getInt("myBack",R.drawable.background);

        l8.setBackground(getDrawable(se));

        if (se==R.drawable.background1) {
            y.setTextColor(getResources().getColor(R.color.colorPrimary));
            c.setTextColor(getResources().getColor(R.color.colorAccent));
        }

        SharedPreferences preferences=getSharedPreferences("sfxState",MODE_PRIVATE);
        sf = preferences.getBoolean("lastSfxState", true);

        lives=3;
        score=0;
        level=1;

        a.setText("Score : "+score);
        b.setText("Chances : "+lives);
        y.setText("Level : "+level);

        updateQuestion(r.nextInt(mQuestionsLenght));

        e.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mp=0;

                resetTimer();

               if(e.getText()== mAnswer)
               {
                   sfx(R.raw.correct);

                   LayoutInflater inflater = getLayoutInflater();

                   View cus = inflater.inflate(R.layout.right, (ViewGroup) findViewById(R.id.rtoast));

                   final Toast toast = new Toast(Main8Activity.this);
                   toast.setDuration(Toast.LENGTH_SHORT);
                   toast.setGravity(Gravity.CENTER,0,0);
                   toast.setView(cus);
                   toast.show();

                   Handler handler = new Handler();
                   handler.postDelayed(new Runnable() {
                       @Override
                       public void run() {
                           toast.cancel();
                       }
                   }, 500);


                   score++;
                   a.setText("Score: "+score);
                   if (score%3==0){
                       level++;
                       y.setText("Level : "+level);
                       if (newtleft>1000){
                           newtleft-=500;
                       }
                   }
                   a.startAnimation(k);
                   b.startAnimation(k);
                   c.startAnimation(k);
                   d.startAnimation(k);
                   e.startAnimation(k);
                   f.startAnimation(k);
                   y.startAnimation(k);
                   updateQuestion(r.nextInt(mQuestionsLenght));


               }
               else
               {
                   sfx(R.raw.wrong);

                   LayoutInflater inflater = getLayoutInflater();

                   View cus = inflater.inflate(R.layout.wrong, (ViewGroup) findViewById(R.id.wtoast));

                   final Toast toast = new Toast(Main8Activity.this);
                   toast.setDuration(Toast.LENGTH_SHORT);
                   toast.setGravity(Gravity.CENTER,0,0);
                   toast.setView(cus);
                   toast.show();

                   Handler handler = new Handler();
                   handler.postDelayed(new Runnable() {
                       @Override
                       public void run() {
                           toast.cancel();
                       }
                   }, 500);

                   if(lives>1){
                       lives--;
                       b.setText("Chances : "+lives);
                       a.startAnimation(k);
                       b.startAnimation(k);
                       c.startAnimation(k);
                       d.startAnimation(k);
                       e.startAnimation(k);
                       f.startAnimation(k);
                       y.startAnimation(k);
                       updateQuestion(r.nextInt(mQuestionsLenght));
                   }
                   else {
                       time.cancel();
                       trun = false;
                       gameOver();
                   }
               }


            }
        });

        f.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mp=0;

                resetTimer();

                if(f.getText()== mAnswer)
                {
                    sfx(R.raw.correct);

                    LayoutInflater inflater = getLayoutInflater();

                    View cus = inflater.inflate(R.layout.right, (ViewGroup) findViewById(R.id.rtoast));

                    final Toast toast = new Toast(Main8Activity.this);
                    toast.setDuration(Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER,0,0);
                    toast.setView(cus);
                    toast.show();

                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            toast.cancel();
                        }
                    }, 500);

                    score++;
                    a.setText("Score: "+score);
                    if (score%3==0){
                        level++;
                        y.setText("Level : "+level);
                        if (newtleft>1000){
                            newtleft-=500;
                        }
                    }
                    a.startAnimation(k);
                    b.startAnimation(k);
                    c.startAnimation(k);
                    d.startAnimation(k);
                    e.startAnimation(k);
                    f.startAnimation(k);
                    y.startAnimation(k);
                    updateQuestion(r.nextInt(mQuestionsLenght));


                }

                else
                {
                    sfx(R.raw.wrong);

                    LayoutInflater inflater = getLayoutInflater();

                    View cus = inflater.inflate(R.layout.wrong, (ViewGroup) findViewById(R.id.wtoast));

                    final Toast toast = new Toast(Main8Activity.this);
                    toast.setDuration(Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER,0,0);
                    toast.setView(cus);
                    toast.show();

                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            toast.cancel();
                        }
                    }, 500);

                    if(lives>1){
                        lives--;
                        b.setText("Chances : "+lives);
                        a.startAnimation(k);
                        b.startAnimation(k);
                        c.startAnimation(k);
                        d.startAnimation(k);
                        e.startAnimation(k);
                        f.startAnimation(k);
                        y.startAnimation(k);
                        updateQuestion(r.nextInt(mQuestionsLenght));
                    }
                    else {
                        time.cancel();
                        trun = false;
                        gameOver();
                    }
                }

            }
        });

    }

    private void updateQuestion(int num)
    {
    c.setText(mQuestions.getQuestion(num));
    e.setText(mQuestions.getChoice1(num));
    f.setText(mQuestions.getChoice2(num));

    mAnswer = mQuestions.getCorrectAnswer(num);

    startTimer();

        a.startAnimation(l);
        b.startAnimation(l);
        c.startAnimation(l);
        d.startAnimation(l);
        e.startAnimation(l);
        f.startAnimation(l);
        y.startAnimation(l);
    }

    private void gameOver()
    {
        mp=1;

        time.cancel();
        trun = false;

        u = loadScore();
        if (u>score) {
            saveScore(u);
        }
        else{
            saveScore(score);
            u=score;
        }

        sfx(R.raw.gameover);

        finish();
        Intent z = new Intent(Main8Activity.this,Main9Activity.class);
        z.putExtra("tag1",score);
        z.putExtra("tag2",level);
        z.putExtra("tag3",u);
        startActivity(z);

        time.cancel();
        trun = false;

    }

    private void startTimer(){

        time = new CountDownTimer(tleft,250) {
            @Override
            public void onTick(long millisUntilFinished) {

                tleft = millisUntilFinished;

                d.setProgress(counter);
                counter+=5+(level-1);

            }

            @Override
            public void onFinish() {

                trun = false;
                counter = 0;

                LayoutInflater inflater = getLayoutInflater();

                View cus = inflater.inflate(R.layout.timeout, (ViewGroup) findViewById(R.id.ttoast));

                final Toast toast = new Toast(Main8Activity.this);
                toast.setDuration(Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER,0,0);
                toast.setView(cus);
                toast.show();

                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        toast.cancel();
                    }
                }, 500);

                if(lives>1){
                    sfx(R.raw.wrong);

                    lives--;
                    b.setText("Chances : "+lives);
                    a.startAnimation(k);
                    b.startAnimation(k);
                    c.startAnimation(k);
                    d.startAnimation(k);
                    e.startAnimation(k);
                    f.startAnimation(k);
                    y.startAnimation(k);
                    resetTimer();
                    updateQuestion(r.nextInt(mQuestionsLenght));
                }
                else {
                    time.cancel();
                    gameOver();
                }


            }
        }.start();

        trun = true;

    }

    private void resetTimer(){

        time.cancel();
        trun = false;
        tleft = newtleft;
        counter=0;

    }

    @Override
    public void onBackPressed() {

        time.cancel();
        trun = false;

        mp=0;

        sfx(R.raw.sfx);

        c.startAnimation(t);
        e.startAnimation(t);
        f.startAnimation(t);

        AlertDialog.Builder g = new AlertDialog.Builder(Main8Activity.this);

        g.setIcon(R.drawable.question);
        g.setTitle(R.string.title1);
        g.setMessage(R.string.msg1);
        g.setCancelable(false);

        g.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                mp=1;

                sfx(R.raw.sfx);

                finish();

            }
        });

        g.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                mp=0;

                sfx(R.raw.sfx);

                startTimer();
                dialog.cancel();

                c.startAnimation(s);
                e.startAnimation(s);
                f.startAnimation(s);

            }
        });

        AlertDialog h =g.create();
        h.show();

    }

    private void saveScore(int k){

        SharedPreferences s = getSharedPreferences("highScore",MODE_PRIVATE);
        SharedPreferences.Editor e = s.edit();
        e.putInt("topScore",k);
        e.commit();

    }

    private void saveScoreBoard(int i,String currentTime){

        SharedPreferences sharedPreferences = getSharedPreferences("scoreBoard",MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("lastScore",i);
        editor.putString("currentTime", currentTime);

        best1 = sharedPreferences.getInt("best1",0);
        best2 = sharedPreferences.getInt("best2",0);
        best3 = sharedPreferences.getInt("best3",0);

        cu1 = sharedPreferences.getString("time1","No Entry");
        cu2 = sharedPreferences.getString("time2","No Entry");
        cu3 = sharedPreferences.getString("time3","No Entry");

        if (i>best3){
            best3 = i;
            cu3 = currentTime;
            editor.putInt("best3",best3);
            editor.putString("time3",cu3);
            editor.commit();
        }

        if (i>best2){
            int temp = best2;
            best2 = i;
            best3 = temp;
            String te = cu2;
            cu2 = currentTime;
            cu3 = te;
            editor.putInt("best3",best3);
            editor.putInt("best2",best2);
            editor.putString("time3",cu3);
            editor.putString("time2",cu2);
            editor.commit();
        }

        if (i>best1){
            int temp = best1;
            best1 = i;
            best2 = temp;
            String te = cu1;
            cu1 = currentTime;
            cu2 = te;
            editor.putInt("best2",best2);
            editor.putInt("best1",best1);
            editor.putString("time1",cu1);
            editor.putString("time2",cu2);
            editor.commit();
        }
        editor.commit();

    }

    private int loadScore(){

        SharedPreferences s = getSharedPreferences("highScore",MODE_PRIVATE);
        int k = s.getInt("topScore",0);
        return k;

    }

    private void sfx(int id){
        if(sf){
            me = MediaPlayer.create(this,id);
            me.start();
        }
    }

    @Override
    protected void onDestroy() {

        saveScoreBoard(score,currentTime);

        super.onDestroy();
    }

    @Override
    protected void onPause() {

        if (mp==0){
            media.player.pause();

            time.cancel();
        }

        super.onPause();
    }

    @Override
    protected void onRestart() {

        SharedPreferences sh = getSharedPreferences("musicState",MODE_PRIVATE);
        boolean k = sh.getBoolean("lastState",true);

        if (trun){
            startTimer();
        }

        if (k && !media.player.isPlaying()){
            media.player.start();
        }

        super.onRestart();
    }
}
