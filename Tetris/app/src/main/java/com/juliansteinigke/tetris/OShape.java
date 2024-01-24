package com.juliansteinigke.tetris;

import android.content.Context;

public class OShape extends Block{

    public OShape(Context context){
//        System.out.println("______________________OShape[constructor]______________________");

        x1 = 4;
        x2 = 4;
        x3 = 5;
        x4 = 5;

        y1 = 19 + spawnLocation();
        y2 = 18 + spawnLocation();
        y3 = 19 + spawnLocation();
        y4 = 18 + spawnLocation();


        spin = 0;
        id = 6;


        graph1 = new Graphics(context,(float)MainTetrisActivity.Xgraphics[x1],(float)MainTetrisActivity.Ygraphics[y1],id);
        graph2 = new Graphics(context,(float)MainTetrisActivity.Xgraphics[x2],(float)MainTetrisActivity.Ygraphics[y2],id);
        graph3 = new Graphics(context,(float)MainTetrisActivity.Xgraphics[x2],(float)MainTetrisActivity.Ygraphics[y3],id);
        graph4 = new Graphics(context,(float)MainTetrisActivity.Xgraphics[x4],(float)MainTetrisActivity.Ygraphics[y4],id);

        pregraph1 = new Graphics(context,id);
        pregraph2 = new Graphics(context,id);
        pregraph3 = new Graphics(context,id);
        pregraph4 = new Graphics(context,id);
        blockUpdate();

    }

    @Override
    public void turnRight() {
        super.turnRight();
        //Test 1
        switch (spin){

            case 0: y1++;y2++;y3++;y4++;
                break;

            case 1: x1++;x2++;x3++;x4++;
                break;

            case 2: y1--;y2--;y3--;y4--;
                break;

            case 3: x1--;x2--;x3--;x4--;
                break;
        }
        if(trySet()){
            reset();
        }else{
            if(spin < 3){
            spin++;}else{
                spin = 0;
            }
        }
        blockUpdate();
    }

    @Override
    public void turnLeft(){

        //Test 1
        switch (spin){

            case 0: x1--;x2--;x3--;x4--;
                break;

            case 1: y1++;y2++;y3++;y4++;
                break;

            case 2: x1++;x2++;x3++;x4++;
                break;

            case 3: y1--;y2--;y3--;y4--;
                break;
        }

        if(trySet()){
            reset();
        }else {
            if (spin > 0) {
                spin--;
            } else {
                spin = 3;
            }
        }
        blockUpdate();

    }
}
