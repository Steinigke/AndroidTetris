package com.juliansteinigke.tetris;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.Group;
import androidx.core.view.GestureDetectorCompat;


import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import java.util.Random;


public class MainTetrisActivity extends AppCompatActivity {

    drawableCalc draw = new drawableCalc();

    ConstraintLayout mainLayout;
    RelativeLayout tetrisLayout;
    ImageView tetrisImage;
    ImageView count;
    ImageView imageHold;
    ImageView[] imageScore = new ImageView[9];
    ImageView[] gameOverScore = new ImageView[9];
    ImageView[] hightScore = new ImageView[9];
    ImageView[] imageLines = new ImageView[9];
    ImageView[] gameOverLines = new ImageView[9];
    ImageView[] hightLines = new ImageView[9];
    ImageView[] imageLevel = new ImageView[2];
    ImageView[] gameOverLevel = new ImageView[2];
    ImageView[] hightLevel = new ImageView[2];
    ImageView[] imagenext = new ImageView[3];
    RelativeLayout leftscreen;
    RelativeLayout rightscreen;
    Group gameOver;

    MediaPlayer gameTheme;
    MediaPlayer resultTheme;
    MediaPlayer eSet;
    MediaPlayer eMove;
    MediaPlayer eHarddrop;
    MediaPlayer eCount;
    MediaPlayer vReady;
    MediaPlayer vGo;

    private GestureDetectorCompat dect;
    private boolean leftside;
    private boolean enabelCon;

    private int[] nextBlock = new int[3];
    public Block currentBlock;
    private int currentId; //Block ID

    private int holdId;
    private boolean hadhold;

    static Graphics[][] graph = new Graphics[10][23]; //Unmovable Graphics

    static boolean[][] con = new boolean[10][24]; // Control field
    final static double[] Xgraphics = new double[10];
    final static double[] Ygraphics = new double[23];

    private double fieldWidth;
    private double fieldHeight;

    private double fieldBlockHeight;

    private int level;
    private int score;
    private int lines;
    private int speed;
    private int period;

    private Random spawnId;
    Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_tetris);

        dect = new GestureDetectorCompat(this, new Gestures());

        spawnId = new Random();

        gameTheme = MediaPlayer.create(MainTetrisActivity.this,R.raw.gametheme);
        resultTheme = MediaPlayer.create(MainTetrisActivity.this,R.raw.results);
        eSet = MediaPlayer.create(MainTetrisActivity.this,R.raw.putdown);
        eMove = MediaPlayer.create(MainTetrisActivity.this,R.raw.softdrop);
        eCount = MediaPlayer.create(MainTetrisActivity.this,R.raw.count);
        eHarddrop = MediaPlayer.create(MainTetrisActivity.this,R.raw.harddrop);
        vGo = MediaPlayer.create(MainTetrisActivity.this,R.raw.go_vo);
        vReady = MediaPlayer.create(MainTetrisActivity.this,R.raw.ready_vo);

        gameTheme.setLooping(true);
        resultTheme.setLooping(true);
        eSet.setLooping(false);
        eMove.setLooping(false);
        eHarddrop.setLooping(false);
        eCount.setLooping(false);
        vGo.setLooping(false);
        vReady.setLooping(false);

        speed = 1000;
        period = 0;

        handler.post(runnable);

    }//End of onCreate

    //Game Timer
    Runnable runnable = new Runnable() {

        @Override
        public void run() {

            switch (period) {

                case 0://created
                    eCount.start();
                    System.out.println(3);
                    eCount.start();

                    gameOver = findViewById(R.id.gameover);
                    gameOver.setVisibility(View.GONE);

                    count = findViewById(R.id.count);
                    count.setVisibility(View.VISIBLE);

                    mainLayout = findViewById(R.id.mainLayout);
                    tetrisLayout = findViewById(R.id.tetrisLayout);
                    tetrisImage = findViewById(R.id.tetrisPlayground);

                    imagenext[0] = findViewById(R.id.nextpice);
                    imagenext[1] = findViewById(R.id.nextpice1);
                    imagenext[2] = findViewById(R.id.nextpice2);

                    for(int i=0;i<nextBlock.length;i++){

                        nextBlock[i] = spawnId.nextInt(7);

                    }
                    updateNextb();

                    period++;
                    handler.postDelayed(this,speed);
                    break;

                case 1://count one
                    eCount.start();
                    System.out.println(2);
                    count.setImageResource(R.drawable.counttwo);
                    fieldHeight = tetrisImage.getHeight();
                    fieldWidth = tetrisImage.getWidth();
                    fieldBlockHeight = fieldHeight / 20;
                    resetGame();
                    period++;
                    handler.postDelayed(this,speed);
                    break;

                case 2: //count two
                    vReady.start();
                    System.out.println(1);

                    count.setImageResource(R.drawable.countone);

                    imageScore[0] = findViewById(R.id.scoreDigit0);
                    imageScore[1] = findViewById(R.id.scoreDigit1);
                    imageScore[2] = findViewById(R.id.scoreDigit2);
                    imageScore[3] = findViewById(R.id.scoreDigit3);
                    imageScore[4] = findViewById(R.id.scoreDigit4);
                    imageScore[5] = findViewById(R.id.scoreDigit5);
                    imageScore[6] = findViewById(R.id.scoreDigit6);
                    imageScore[7] = findViewById(R.id.scoreDigit7);
                    imageScore[8] = findViewById(R.id.scoreDigit8);

                    gameOverScore[0] = findViewById(R.id.overScoreDigit0);
                    gameOverScore[1] = findViewById(R.id.overScoreDigit1);
                    gameOverScore[2] = findViewById(R.id.overScoreDigit2);
                    gameOverScore[3] = findViewById(R.id.overScoreDigit3);
                    gameOverScore[4] = findViewById(R.id.overScoreDigit4);
                    gameOverScore[5] = findViewById(R.id.overScoreDigit5);
                    gameOverScore[6] = findViewById(R.id.overScoreDigit6);
                    gameOverScore[7] = findViewById(R.id.overScoreDigit7);
                    gameOverScore[8] = findViewById(R.id.overScoreDigit8);

                    hightScore[0] = findViewById(R.id.highscore0);
                    hightScore[1] = findViewById(R.id.highscore1);
                    hightScore[2] = findViewById(R.id.highscore2);
                    hightScore[3] = findViewById(R.id.highscore3);
                    hightScore[4] = findViewById(R.id.highscore4);
                    hightScore[5] = findViewById(R.id.highscore5);
                    hightScore[6] = findViewById(R.id.highscore6);
                    hightScore[7] = findViewById(R.id.highscore7);
                    hightScore[8] = findViewById(R.id.highscore8);

                    imageLines[0] = findViewById(R.id.linedigit0);
                    imageLines[1] = findViewById(R.id.linedigit1);
                    imageLines[2] = findViewById(R.id.linedigit2);
                    imageLines[3] = findViewById(R.id.linedigit3);
                    imageLines[4] = findViewById(R.id.linedigit4);
                    imageLines[5] = findViewById(R.id.linedigit5);
                    imageLines[6] = findViewById(R.id.linedigit6);
                    imageLines[7] = findViewById(R.id.linedigit7);
                    imageLines[8] = findViewById(R.id.linedigit8);

                    gameOverLines[0] = findViewById(R.id.overLineDigit0);
                    gameOverLines[1] = findViewById(R.id.overLineDigit1);
                    gameOverLines[2] = findViewById(R.id.overLineDigit2);
                    gameOverLines[3] = findViewById(R.id.overLineDigit3);
                    gameOverLines[4] = findViewById(R.id.overLineDigit4);
                    gameOverLines[5] = findViewById(R.id.overLineDigit5);
                    gameOverLines[6] = findViewById(R.id.overLineDigit6);
                    gameOverLines[7] = findViewById(R.id.overLineDigit7);
                    gameOverLines[8] = findViewById(R.id.overLineDigit8);

                    hightLines[0] = findViewById(R.id.highLine0);
                    hightLines[1] = findViewById(R.id.highLine1);
                    hightLines[2] = findViewById(R.id.highLine2);
                    hightLines[3] = findViewById(R.id.highLine3);
                    hightLines[4] = findViewById(R.id.highLine4);
                    hightLines[5] = findViewById(R.id.highLine5);
                    hightLines[6] = findViewById(R.id.highLine6);
                    hightLines[7] = findViewById(R.id.highLine7);
                    hightLines[8] = findViewById(R.id.highLine8);


                    imageLevel[0] = findViewById(R.id.levelDigit);
                    imageLevel[1] = findViewById(R.id.levelDigit1);

                    gameOverLevel[0] = findViewById(R.id.overLevelDigit0);
                    gameOverLevel[1] = findViewById(R.id.overLevelDigit1);

                    hightLevel[0] = findViewById(R.id.hightlevel0);
                    hightLevel[1] = findViewById(R.id.hightlevel1);

                    updateScore();
                    updateLines();

                    imageHold = findViewById(R.id.holdpice);

                    period++;
                    handler.postDelayed(this,speed);
                    break;

                case 3:
                    gameTheme.start();
                    vGo.start();
                    period++;
                default://Game
                    gameTheme.start();
                    count.setVisibility(View.GONE);

                    if(currentBlock == null){// spawn Block


                        enabelCon = true;

                        updateNextb();


                        switch(currentId){

                            case 0: currentBlock = new IShape(MainTetrisActivity.this);
//                                System.out.println("currentBlock = IShape");
                                break;

                            case 1:
                            case 2:
                            case 3:
                            case 4:
                            case 5: currentBlock = new LJSTZShape(MainTetrisActivity.this, currentId);
                                break;

                            case 6: currentBlock = new OShape(MainTetrisActivity.this);
                                break;

                            default: currentBlock = null;

                        }

                        tetrisLayout.addView(currentBlock.graph1);
                        tetrisLayout.addView(currentBlock.graph2);
                        tetrisLayout.addView(currentBlock.graph3);
                        tetrisLayout.addView(currentBlock.graph4);


                        tetrisLayout.addView(currentBlock.pregraph1);
                        tetrisLayout.addView(currentBlock.pregraph2);
                        tetrisLayout.addView(currentBlock.pregraph3);
                        tetrisLayout.addView(currentBlock.pregraph4);

                        handler.postDelayed(this,speed);

                    }else{//Playable

                        //Move down
                        if(currentBlock.down()){//touch down
                            eSet.start();
                            //set / remove graphic components

                            graph[currentBlock.x1][currentBlock.y1] = new Graphics(MainTetrisActivity.this, (float) MainTetrisActivity.Xgraphics[currentBlock.x1], (float) MainTetrisActivity.Ygraphics[currentBlock.y1], currentId);
                            graph[currentBlock.x2][currentBlock.y2] = new Graphics(MainTetrisActivity.this, (float) MainTetrisActivity.Xgraphics[currentBlock.x2], (float) MainTetrisActivity.Ygraphics[currentBlock.y2], currentId);
                            graph[currentBlock.x3][currentBlock.y3] = new Graphics(MainTetrisActivity.this, (float) MainTetrisActivity.Xgraphics[currentBlock.x3], (float) MainTetrisActivity.Ygraphics[currentBlock.y3], currentId);
                            graph[currentBlock.x4][currentBlock.y4] = new Graphics(MainTetrisActivity.this, (float) MainTetrisActivity.Xgraphics[currentBlock.x4], (float) MainTetrisActivity.Ygraphics[currentBlock.y4], currentId);

                            tetrisLayout.addView(graph[currentBlock.x1][currentBlock.y1]);
                            tetrisLayout.addView(graph[currentBlock.x2][currentBlock.y2]);
                            tetrisLayout.addView(graph[currentBlock.x3][currentBlock.y3]);
                            tetrisLayout.addView(graph[currentBlock.x4][currentBlock.y4]);

                            tetrisLayout.removeView(currentBlock.graph1);
                            tetrisLayout.removeView(currentBlock.graph2);
                            tetrisLayout.removeView(currentBlock.graph3);
                            tetrisLayout.removeView(currentBlock.graph4);

                            tetrisLayout.removeView(currentBlock.pregraph1);
                            tetrisLayout.removeView(currentBlock.pregraph2);
                            tetrisLayout.removeView(currentBlock.pregraph3);
                            tetrisLayout.removeView(currentBlock.pregraph4);
//
//                              Line Clear/Score
                            int l = 0;

                            if(lineClear(currentBlock.y1)){
                                l++;
                            }

                            if(lineClear(currentBlock.y2)){
                                l++;
                            }

                            if(lineClear(currentBlock.y3)){
                                l++;
                            }

                            if(lineClear(currentBlock.y4)){
                                l++;
                            }

                            switch(l){

                                case 1: score += 40*level;
                                    break;

                                case 2: score += 100*level;
                                    break;

                                case 3: score += 300*level;
                                    break;

                                case 4: score += 1200*level;
                                    break;
                            }

                            lines += l;

                            updateLines();
                            updateScore();

                            if(topCon()){//Defeat control
                                gameTheme.stop();
                                resultTheme.start();

                                System.out.println(". GAME OVER");

                                if(MainActivity.highscore[2]<score){

                                    MainActivity.highscore[0] = level;
                                    MainActivity.highscore[1] = score;
                                    MainActivity.highscore[2] = lines;

                                }

                                for (int d=0; d< gameOverLines.length;d++){

                                    gameOverLines[d].setImageResource(draw.getdrawable(d,lines));
                                    gameOverScore[d].setImageResource(draw.getdrawable(d,score));

                                    hightLines[d].setImageResource(draw.getdrawable(d,MainActivity.highscore[2]));
                                    hightScore[d].setImageResource(draw.getdrawable(d,MainActivity.highscore[1]));


                                    try{
                                        gameOverLevel[d].setImageResource(draw.getdrawable(d,level));
                                        hightLevel[d].setImageResource(draw.getdrawable(d,MainActivity.highscore[0]));

                                    }catch (ArrayIndexOutOfBoundsException e){}

                                }
                                gameOver.setVisibility(View.VISIBLE);

//                                handler.removeCallbacks(this);
                                stop();

                            }else{//rest for next pice

                                hadhold = false;
                                enabelCon = false;
                                currentBlock = null;
                                handler.postDelayed(this,speed);

                            }
                        }else{
                            handler.postDelayed(this,speed);
                        }
                    }
//                    handler.postDelayed(this,speed);
            }
        }};

    public static Intent makeIntent(Context context) {
        return new Intent(context, MainTetrisActivity.class);

    }

//Controls//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(currentBlock != null && enabelCon){
            dect.onTouchEvent(event);
            return super.onTouchEvent(event);
        }
        return super.onTouchEvent(event);
    }


   private class Gestures extends GestureDetector.SimpleOnGestureListener{

       @Override
       public boolean onSingleTapUp(MotionEvent e) {

           System.out.println("Single Tap");
           if (leftside) {
               System.out.println("LeftTurn");
               currentBlock.turnLeft();
           } else {
               System.out.println("RightTurn");
               currentBlock.turnRight();
           }
           return false;
       }

       @Override
       public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {



                System.out.println("onScroll");


           if (distanceX > 20) {System.out.println("left");

               currentBlock.left();
               eMove.start();
               return false;

           } else if (distanceX < -20) {System.out.println("right");

               currentBlock.right();
               eMove.start();
               return false;

           } else if (distanceY > 40) {System.out.println("Harddrop");

               eHarddrop.start();
               score += (currentBlock.harddrop() * 2)*level;
               handler.removeCallbacks(runnable);
               handler.post(runnable);
               updateScore();
               return false;

           } else if (distanceY < -10) {System.out.println("down");

               currentBlock.down();
               eMove.start();
               score+=level;
               updateScore();
               return false;
           }

           return false;
       }

       @Override
       public void onLongPress(MotionEvent e) {

           System.out.println("LongPress");

       }

       @Override
       public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
           System.out.println("On Fling");
           return false;
       }

       @Override
       public boolean onDoubleTap(MotionEvent e) {

               System.out.println("DoubleTap");
               hold();

           return super.onDoubleTap(e);
       }
   }//End of detct

    public void left(View view){

        leftside = true;

    }

    public void right(View view){

        leftside = false;

    }

    public void againButton(View view){

        speed = 1000;
        period = 0;

        tetrisLayout.removeAllViews();
        handler.postDelayed(runnable,0);
        gameOver.setVisibility(View.GONE);
        count.setVisibility(View.VISIBLE);
        count.setImageResource(R.drawable.countthree);

        resultTheme.stop();

    }

    public void returnButton(View view){

        System.out.println("MENU BUTTON THAT SHOULD WORK");
        resultTheme.stop();
        Intent intent = MainActivity.makeIntent(MainTetrisActivity.this);
        startActivity(intent);
        finish();

    }


//Process///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public void resetGame(){

        setupGraphics();
        currentBlock = null;

        holdId = 42;

        lines = 0;
        level = 1;
        score = 0;

        for(int l=0; l < 10; l++){

            for(int c=0; c<22;c++){

                con[l][c] = false;
                
            }
        }
    }

    public void updateScore(){

        for(int d = 0;d < imageScore.length;d++){

            imageScore[d].setImageResource(draw.getdrawable(d,score));

        }

    }

    public  void updateLines(){

        for(int d = 0;d < imageLines.length;d++){

            imageLines[d].setImageResource(draw.getdrawable(d,lines));

        }
        updateLevel();
    }

    public void updateLevel(){

        if(lines >= (level*25)&&level<=30){

            speed -= 30;
            level++;

        }


        for(int d = 0;d < imageLevel.length;d++){

            imageLevel[d].setImageResource(draw.getdrawable(d,level));

        }

    }

    public void updateNextb(){

        currentId = nextBlock[0];
        nextBlock[0] = nextBlock[1];
        nextBlock[1] = nextBlock[2];
        nextBlock[2] = spawnId.nextInt(7);

        for(int i=0;i < imagenext.length;i++){

            imagenext[i].setImageResource(draw.getNextpice(nextBlock[i]));

        }
    }

    public void setupGraphics(){

        Graphics.scale = (int) fieldBlockHeight;

        for(int w=0;w<=9;w++){

            Xgraphics[w] = fieldBlockHeight *(w);

        }

        int dh = 0;
        for(int h=19;h>=0;h--){

            Ygraphics[dh] = fieldBlockHeight *(h);
            dh++;

        }

        Ygraphics[21] = -42069;
        Ygraphics[20] = -42069;

    }

    public boolean topCon(){

        for(int l = 0; l<10;l++){

            if(con[l][19]){

                return true;

            }
        }

        return false;
    }

    public boolean lineFull(int line){

        for(int l = 0; l<10;l++){

            if(!con[l][line]){

                return false;

            }
        }
        return true;
    }

    public boolean lineClear(int line){

        if(lineFull(line)){

            for(int l =0; l<10;l++){

                for(int h=line;h<19;h++){

                    if(h==line){
                        tetrisLayout.removeView(graph[l][h]);
                    }

                    con[l][h] = con[l][h+1];
                    graph[l][h] = graph[l][h+1];

                    try {
                        graph[l][h].setX((float) Xgraphics[l]);
                        graph[l][h].setY((float) Ygraphics[h]);
                    }catch(java.lang.NullPointerException ignored){}
                }
            }

            return true;

        }else{
            return false;
        }
    }


    public void hold(){

        if(!hadhold){
            int h;
            h = currentId;

            tetrisLayout.removeView(currentBlock.graph1);
            tetrisLayout.removeView(currentBlock.graph2);
            tetrisLayout.removeView(currentBlock.graph3);
            tetrisLayout.removeView(currentBlock.graph4);

            tetrisLayout.removeView(currentBlock.pregraph1);
            tetrisLayout.removeView(currentBlock.pregraph2);
            tetrisLayout.removeView(currentBlock.pregraph3);
            tetrisLayout.removeView(currentBlock.pregraph4);

            switch (holdId) {

                case 0:
                    currentBlock = new IShape(MainTetrisActivity.this);
                    break;

                case 1:
                case 2:
                case 3:
                case 4:
                case 5:
                    currentBlock = new LJSTZShape(MainTetrisActivity.this, holdId);
                    break;

                case 6:
                    currentBlock = new OShape(MainTetrisActivity.this);
                    break;

                default:
                    currentBlock = null;
            }

            try {
                tetrisLayout.addView(currentBlock.graph1);
                tetrisLayout.addView(currentBlock.graph2);
                tetrisLayout.addView(currentBlock.graph3);
                tetrisLayout.addView(currentBlock.graph4);

                tetrisLayout.addView(currentBlock.pregraph1);
                tetrisLayout.addView(currentBlock.pregraph2);
                tetrisLayout.addView(currentBlock.pregraph3);
                tetrisLayout.addView(currentBlock.pregraph4);
            }catch (java.lang.NullPointerException e){

                imageHold.setVisibility(View.VISIBLE);
            }
            imageHold.setImageResource(draw.getNextpice(currentId));

            currentId = holdId;
            holdId = h;
            hadhold = true;
        }
    }

    public void stop(){

        handler.removeCallbacks(runnable);

    }


//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

}