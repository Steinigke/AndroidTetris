package com.juliansteinigke.tetris;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;



public class MainActivity extends AppCompatActivity {

    public static int[] highscore = {-1, -42, -69};
    MediaPlayer menuTheme;

//    SharedPreferences prefs = this.getPreferences(Context.MODE_PRIVATE);
//    SharedPreferences.Editor editor = prefs.edit();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        menuTheme = MediaPlayer.create(MainActivity.this, R.raw.menu);
        menuTheme.start();
        menuTheme.setLooping(true);

//        highscore[0] = prefs.getInt("high0",0);
//        highscore[1] = prefs.getInt("high1",0);
//        highscore[2] = prefs.getInt("high2",0);

    }

    public static Intent makeIntent(Context context) {
        return new Intent(context, MainActivity.class);

    }


    public void startClick(View view) {

        Intent tetrisActivity = MainTetrisActivity.makeIntent(this);
        startActivity(tetrisActivity);
        finish();
        menuTheme.stop();

    }

//    public void setHighscore(int h) {
//
//        switch (h) {
//
//            case 0:
//                editor.putInt("high0", highscore[h]);
//                break;
//            case 1:
//                editor.putInt("high1", highscore[h]);
//                break;
//            case 2:
//                editor.putInt("high2", highscore[h]);
//                break;
//        }
//
//        editor.apply();
//
//    }


}





























